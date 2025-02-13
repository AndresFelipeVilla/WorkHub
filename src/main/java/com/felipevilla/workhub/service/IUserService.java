package com.felipevilla.workhub.service;

import java.util.List;

import com.felipevilla.workhub.error.UserNotFoundException;
import com.felipevilla.workhub.model.dto.UserDTO;

public interface IUserService {

    public List<UserDTO> findAll();
    
    public UserDTO save(UserDTO user);

    public void deleteUser(Long id) throws UserNotFoundException;

    boolean existsByEmail(String email);
}
