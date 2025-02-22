package com.felipevilla.Workhub20.repository;

import java.util.List;
import java.util.Optional;



import org.springframework.data.repository.CrudRepository;

import com.felipevilla.Workhub20.model.RoleModel;
import com.felipevilla.Workhub20.model.Enum.RoleEnum;


public interface RoleRepository extends CrudRepository<RoleModel, Long> {
   
    List<RoleModel> findRoleModelsByRoleEnumIn (List<String> names);

    Optional<RoleModel> findByRoleEnum (RoleEnum USER);

}
