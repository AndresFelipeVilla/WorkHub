package com.felipevilla.Workhub20.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipevilla.Workhub20.dto.AuthCreateUserRequest;
import com.felipevilla.Workhub20.dto.AuthLoginRequest;
import com.felipevilla.Workhub20.dto.AuthResponse;
import com.felipevilla.Workhub20.dto.AuthResponseUpdate;
import com.felipevilla.Workhub20.dto.AuthUpdateUserRolesRequest;
import com.felipevilla.Workhub20.model.RoleModel;
import com.felipevilla.Workhub20.model.UserModel;
import com.felipevilla.Workhub20.model.Enum.RoleEnum;
import com.felipevilla.Workhub20.repository.RoleRepository;
import com.felipevilla.Workhub20.repository.UserRepository;
import com.felipevilla.Workhub20.security.Util.JwtUtils;



@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;   
    private final RoleRepository roleRepository; 

    public UserDetailsService(UserRepository userRepository, JwtUtils jwtUtils,PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = userRepository.findUserModelByUserName(username)
                    .orElseThrow(()-> new UsernameNotFoundException("El usuario " + username + "no existe"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userModel.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userModel.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));
                    
        return new User(userModel.getUserName(), 
                        userModel.getPassword(),
                        userModel.isEnabled(),
                        userModel.isAccountNoExpired(), 
                        userModel.isCredentialNoExpired(), 
                        userModel.isAccountNoLocked(), 
                        authorityList );
    }

    public AuthResponse loginUser (AuthLoginRequest authLoginRequest){
        String userName = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication  = this.authenticate(userName, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(userName, "Login exitoso", accessToken, true);
        return authResponse; 
    }

    public Authentication authenticate(String userName, String password){
        UserDetails userDetails = this.loadUserByUsername(userName);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userName, userDetails.getPassword(), userDetails.getAuthorities());

    }

    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest) {
        String username = authCreateUserRequest.username();
        String password = authCreateUserRequest.password();
    
        // Obtener el rol "ROLE_USER" de la base de datos
        RoleModel userRole = roleRepository.findByRoleEnum(RoleEnum.USER)
            .orElseThrow(() -> new BadCredentialsException("Default role USER not found"));
    
        Set<RoleModel> roleModelSet = Set.of(userRole);
    
        UserModel userModel = UserModel.builder()
                .userName(username)
                .password(passwordEncoder.encode(password))
                .roles(roleModelSet) // Solo asignamos ROLE_USER
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .build();
    
        UserModel userCreated = userRepository.save(userModel);
    
        String accessToken = jwtUtils.createToken(
                new UsernamePasswordAuthenticationToken(userCreated.getUserName(), userCreated.getPassword(), 
                    List.of(new SimpleGrantedAuthority("ROLE_USER")))
        );
    
        return new AuthResponse(userCreated.getUserName(), "User created successfully", accessToken, true);
    }
    
    
    public AuthResponseUpdate updateUserRoles(AuthUpdateUserRolesRequest authUpdateUserRolesRequest) {
            
        UserModel user = userRepository.findUserModelByUserName(authUpdateUserRolesRequest.username())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Set<RoleModel> newRoles = roleRepository.findRoleModelsByRoleEnumIn(authUpdateUserRolesRequest.roleListName()).stream()
            .collect(Collectors.toSet());

        if (newRoles.isEmpty()) {
            throw new IllegalArgumentException("Los roles especificados no existen");
        }

        user.setRoles(newRoles);
        userRepository.save(user);

        return new AuthResponseUpdate("Roles actualizados correctamente", authUpdateUserRolesRequest.roleListName());

    }

    
    

    


}
