package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.ResourceDTO;
import com.example.conference_manager.models.dto.ResourceTypeDTO;
import com.example.conference_manager.models.request.ResourceRequest;
import com.example.conference_manager.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resources")
@CrossOrigin
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public List<ResourceDTO> findAll(){
        return resourceService.findAllByOrdered();
    }

    @GetMapping("/{id}")
    public ResourceDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return resourceService.findById(id);
    }

    @GetMapping("/resourcetypes")
    public List<ResourceTypeDTO> findResourceTypes(){
        return resourceService.findResourceTypes();
    }

    @PostMapping
    public ResourceDTO insert(@RequestBody ResourceRequest resourceRequest) throws NotFoundException {
        return resourceService.insert(resourceRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        resourceService.delete(id);
    }

    @PutMapping("/{id}")
    public ResourceDTO update(@PathVariable("id") UUID id, @RequestBody ResourceRequest resourceRequest) throws NotFoundException {
        return resourceService.update(id, resourceRequest);
    }
}
