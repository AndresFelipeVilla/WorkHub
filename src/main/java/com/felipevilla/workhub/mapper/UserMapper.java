package com.felipevilla.workhub.mapper;

import org.mapstruct.Mapper;

import com.felipevilla.workhub.model.User;
import com.felipevilla.workhub.model.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
