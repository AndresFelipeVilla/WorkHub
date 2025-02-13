package com.felipevilla.workhub.service;

import java.util.List;

import com.felipevilla.workhub.error.SpaceNotFoundException;
import com.felipevilla.workhub.model.dto.SpaceDTO;

public interface ISpaceService {

    public SpaceDTO save(SpaceDTO spaceDTO);

    public SpaceDTO findById(Long id) throws SpaceNotFoundException;

    public List<SpaceDTO> findAll();

    public SpaceDTO updateSpace(Long id, SpaceDTO spaceDTO) throws SpaceNotFoundException;

    public void deleteSpace(Long id) throws SpaceNotFoundException;

 }
