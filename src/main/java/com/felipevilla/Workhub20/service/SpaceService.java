package com.felipevilla.Workhub20.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.felipevilla.Workhub20.dto.SpaceDTO;
import com.felipevilla.Workhub20.error.ResourceNotFoundException;
import com.felipevilla.Workhub20.mapper.SpaceMapper;
import com.felipevilla.Workhub20.model.SpaceModel;
import com.felipevilla.Workhub20.repository.SpaceRepository;

@Service
public class SpaceService implements ISpaceService {

    private final SpaceRepository spaceRepository;
    private final SpaceMapper spaceMapper;

    public SpaceService(SpaceRepository spaceRepository, SpaceMapper spaceMapper) {
        this.spaceRepository = spaceRepository;
        this.spaceMapper = spaceMapper;
    }

    @Override
    public SpaceDTO save(SpaceDTO spaceDTO) {
        SpaceModel space = spaceMapper.spaceDTOToSpace(spaceDTO);
        SpaceModel savedSpace = spaceRepository.save(space);
        return spaceMapper.spaceToSpaceDTO(savedSpace);
    }

    @Override
    public SpaceDTO findById(Long id) throws ResourceNotFoundException {
        SpaceModel space = spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Space not found for this id: " + id));
        return spaceMapper.spaceToSpaceDTO(space);
    }

    @Override
    public List<SpaceDTO> findAll() {
        List<SpaceModel> spaces = spaceRepository.findAll();
        return spaces.stream().map(spaceMapper::spaceToSpaceDTO).toList();
    }

    @Override
    public SpaceDTO updateSpace(Long id, SpaceDTO spaceDTO) throws ResourceNotFoundException {
        SpaceModel exisSpaceModel = spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Space not found for this id: " + id));

        exisSpaceModel.setName(spaceDTO.getName());
        exisSpaceModel.setCapacity(spaceDTO.getCapacity());
        exisSpaceModel.setAvailable(spaceDTO.getAvailable());
        exisSpaceModel.setSpacetype(spaceDTO.getSpacetype());
        exisSpaceModel.setBookings(spaceDTO.getBookings());

        SpaceModel updatedSpaceModel = spaceRepository.save(exisSpaceModel);

        return spaceMapper.spaceToSpaceDTO(updatedSpaceModel);
    }

    @Override
    public void deleteSpace(Long id) throws ResourceNotFoundException {
        SpaceModel space = spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Space not found for this id: " + id));
        spaceRepository.delete(space);
    }


}
