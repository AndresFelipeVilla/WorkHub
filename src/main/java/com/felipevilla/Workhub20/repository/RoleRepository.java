package com.felipevilla.Workhub20.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.felipevilla.Workhub20.model.RoleModel;

public interface RoleRepository extends CrudRepository<RoleModel, Long> {
   
    List<RoleModel> findRoleModelsByRoleEnumIn (List<String> names);

}
