package com.felipevilla.workhub.service;




import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipevilla.workhub.error.UserNotFoundException;
import com.felipevilla.workhub.mapper.UserMapper;
import com.felipevilla.workhub.model.Role;
import com.felipevilla.workhub.model.User;
import com.felipevilla.workhub.model.dto.UserDTO;
import com.felipevilla.workhub.repository.RoleRepository;
import com.felipevilla.workhub.repository.UserRepository;



@Service
public class UserService implements IUserService {

    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder, UserMapper userMapper){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;

    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
    
        List<Role> roles = new ArrayList<>();
        roleRepository.findByName("ROLE_USER").ifPresentOrElse(
            roles::add,
            () -> { throw new RuntimeException("The role 'ROLE_USER' does not exist in the database."); }
        );

        if (user.isAdmin()) {
            roleRepository.findByName("ROLE_ADMIN").ifPresentOrElse(
                roles::add,
                () -> { throw new RuntimeException("The role 'ROLE_ADMIN' does not exist in the database."); }
        );
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);

        return userMapper.userToUserDTO(savedUser);
    }   


    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(userMapper::userToUserDTO)
                    .toList();
                    
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

       
}
    

