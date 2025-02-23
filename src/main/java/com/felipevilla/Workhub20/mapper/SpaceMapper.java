package com.felipevilla.Workhub20.mapper;

import org.mapstruct.Mapper;

import com.felipevilla.Workhub20.dto.SpaceDTO;
import com.felipevilla.Workhub20.model.SpaceModel;

@Mapper(componentModel = "spring")
public interface SpaceMapper {
    SpaceDTO spaceToSpaceDTO(SpaceModel space); 
    SpaceModel spaceDTOToSpace(SpaceDTO spaceDTO); 
}
