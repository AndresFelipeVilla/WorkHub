package com.felipevilla.workhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipevilla.workhub.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

}
