package com.felipevilla.Workhub20.service;

import java.util.List;

import com.felipevilla.Workhub20.dto.SpaceDTO;
import com.felipevilla.Workhub20.error.ResourceNotFoundException;

public interface ISpaceService {
    
    SpaceDTO save(SpaceDTO spaceDTO);

    SpaceDTO findById(Long id) throws ResourceNotFoundException;

    List<SpaceDTO> findAll();

    SpaceDTO updateSpace(Long id, SpaceDTO spaceDTO) throws ResourceNotFoundException;

    void deleteSpace(Long id) throws ResourceNotFoundException;
}
