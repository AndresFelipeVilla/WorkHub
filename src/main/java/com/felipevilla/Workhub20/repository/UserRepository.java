package com.felipevilla.Workhub20.repository;

import org.springframework.data.repository.CrudRepository;

import com.felipevilla.Workhub20.model.UserModel;

import java.util.Optional;


public interface UserRepository extends CrudRepository<UserModel, Long> {

    Optional<UserModel> findUserModelByUserName(String userName);
}
