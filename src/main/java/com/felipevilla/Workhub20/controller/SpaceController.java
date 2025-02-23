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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/workhub/spaces")
public class SpaceController {

    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService){
        this.spaceService = spaceService;
    }

    @PostMapping("/save")
    public ResponseEntity<SpaceDTO> saveSpace(@Valid @RequestBody SpaceDTO spaceDTO) {
        SpaceDTO space = spaceService.save(spaceDTO);
        return ResponseEntity.ok(space);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<SpaceDTO> findSpace(@PathVariable Long id){
        SpaceDTO space = spaceService.findById(id);
        return ResponseEntity.ok(space);
    }

    @GetMapping()
    public ResponseEntity<List<SpaceDTO>> findAllSpace(){
        List<SpaceDTO> spaces = spaceService.findAll();
        return ResponseEntity.ok(spaces); 
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<SpaceDTO> updateSpace(@PathVariable Long id,@Valid @RequestBody SpaceDTO spaceDTO) {
        SpaceDTO space = spaceService.updateSpace(id, spaceDTO);
        return ResponseEntity.ok(space); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable Long id){
        spaceService.deleteSpace(id);
        return ResponseEntity.ok("The space was successfully deleted");
    }
}
