package com.felipevilla.Workhub20.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipevilla.Workhub20.model.SpaceModel;

@Repository
public interface SpaceRepository extends JpaRepository<SpaceModel, Long> {

}
