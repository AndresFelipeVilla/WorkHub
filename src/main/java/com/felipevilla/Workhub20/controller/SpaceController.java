package com.felipevilla.Workhub20.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipevilla.Workhub20.dto.SpaceDTO;
import com.felipevilla.Workhub20.service.SpaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/workhub/spaces")
@Tag(name = "Space Management", description = "API for workspace management")
public class SpaceController {

    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService){
        this.spaceService = spaceService;
    }

     @Operation(summary = "Create a new space", 
               description = "Saves a new workspace with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Space created successfully",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = SpaceDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid space data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/save")
    public ResponseEntity<SpaceDTO> saveSpace(@Valid @RequestBody SpaceDTO spaceDTO) {
        SpaceDTO space = spaceService.save(spaceDTO);
        return ResponseEntity.ok(space);
    } 

    @Operation(summary = "Find space by ID", 
               description = "Retrieves the details of a specific space by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Space found",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = SpaceDTO.class))),
        @ApiResponse(responseCode = "404", description = "Space not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SpaceDTO> findSpace(@PathVariable Long id){
        SpaceDTO space = spaceService.findById(id);
        return ResponseEntity.ok(space);
    }

    @Operation(summary = "List all spaces", 
               description = "Retrieves a list of all available spaces")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of spaces retrieved successfully",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    public ResponseEntity<List<SpaceDTO>> findAllSpace(){
        List<SpaceDTO> spaces = spaceService.findAll();
        return ResponseEntity.ok(spaces); 
    }

    @Operation(summary = "Update a space", 
               description = "Updates an existing space with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Space updated successfully",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = SpaceDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid update data"),
        @ApiResponse(responseCode = "404", description = "Space not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<SpaceDTO> updateSpace(@PathVariable Long id,@Valid @RequestBody SpaceDTO spaceDTO) {
        SpaceDTO space = spaceService.updateSpace(id, spaceDTO);
        return ResponseEntity.ok(space); 
    }

    @Operation(summary = "Delete a space", 
               description = "Deletes an existing space by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Space deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Space not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable Long id){
        spaceService.deleteSpace(id);
        return ResponseEntity.ok("The space was successfully deleted");
    }
}
