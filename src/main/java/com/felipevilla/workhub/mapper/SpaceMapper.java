package com.felipevilla.workhub.mapper;

import org.mapstruct.Mapper;


import com.felipevilla.workhub.model.Space;
import com.felipevilla.workhub.model.dto.SpaceDTO;

@Mapper(componentModel = "spring")
public interface SpaceMapper {
    SpaceDTO spaceToSpaceDTO(Space space);
    Space spaceDTOToSpace(SpaceDTO spaceDTO);
    
}
