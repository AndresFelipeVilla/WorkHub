package com.felipevilla.workhub.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.felipevilla.workhub.error.SpaceNotFoundException;
import com.felipevilla.workhub.mapper.SpaceMapper;
import com.felipevilla.workhub.model.Space;
import com.felipevilla.workhub.model.dto.SpaceDTO;
import com.felipevilla.workhub.repository.SpaceRepository;

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
        Space space = spaceMapper.spaceDTOToSpace(spaceDTO);
        Space savedSpace = spaceRepository.save(space);
        return spaceMapper.spaceToSpaceDTO(savedSpace);
    }

    @Override
    public SpaceDTO findById(Long id) throws SpaceNotFoundException {
        Space space = spaceRepository.findById(id)
                        .orElseThrow(()-> new SpaceNotFoundException("Space not found with id: " + id));
        return spaceMapper.spaceToSpaceDTO(space);
    }

    @Override
    public List<SpaceDTO> findAll() {
        List<Space> spaces = spaceRepository.findAll();

        return spaces.stream()
                    .map(spaceMapper::spaceToSpaceDTO)
                    .toList();
    }

    @Override
    public SpaceDTO updateSpace(Long id, SpaceDTO spaceDTO) throws SpaceNotFoundException {
        Space existingSpace = spaceRepository.findById(id)
                .orElseThrow(() -> new SpaceNotFoundException("Space not found with id: " + id));
        
        existingSpace.setName(spaceDTO.getName());
        existingSpace.setCapacity(spaceDTO.getCapacity());
        existingSpace.setAvailable(spaceDTO.getAvailable());
        existingSpace.setSpacetype(spaceDTO.getSpacetype());
        existingSpace.setBookings(spaceDTO.getBookings());

        Space updateSpace = spaceRepository.save(existingSpace);

        return spaceMapper.spaceToSpaceDTO(updateSpace);
        
    }

    @Override
    public void deleteSpace(Long id) throws SpaceNotFoundException {
        if (!spaceRepository.existsById(id)) {
            throw new SpaceNotFoundException("Space not found with id: " + id);
        }
        spaceRepository.deleteById(id);
    }

    
}

