package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.dto.ResourceDTO;
import com.devworks.cloudcommerce.module.account.dto.input.UpdateResourceInput;
import com.devworks.cloudcommerce.module.account.mapper.ResourceMapper;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ResourceService {
  private final ActionService actionService;
  private final DepartmentService departmentService;
  private final ResourceRepository resourceRepository;

  public ResourceService(ActionService actionService, DepartmentService departmentService, ResourceRepository resourceRepository) {
    this.actionService = actionService;
    this.departmentService = departmentService;
    this.resourceRepository = resourceRepository;
  }

  public Resource create(ResourceDTO request) {
    var existsResource = resourceRepository.findByName(request.getName());

    if (existsResource.isPresent())
      throw new BadRequestException("Resource already exists");

    var resource = ResourceMapper.toEntity(request);

    if(!request.getActions().isEmpty()) {
      resource.setActions(actionService.getValidActions(request.getActions()));
    }

    if(request.getDepartments() != null) {
      resource.setDepartments(departmentService.getValidDepartments(request.getDepartments()));
    }

    return resourceRepository.save(resource);
  }

  public Resource update(UUID resourceId, UpdateResourceInput input) {
    var findedResource = findById(resourceId);

    findedResource.setActions(actionService.getValidActions(input.actions()));
    findedResource.setUpdatedAt(LocalDateTime.now());
    return resourceRepository.save(findedResource);
  }

  public Resource findById(UUID id) {
      return resourceRepository.findById(id)
          .orElseThrow(() -> new NotFoundException("Not found resource with id " + id));
  }

  public Resource findByName(String name) {
    var resource = resourceRepository.findByName(name);

    if(resource.isEmpty())
      throw new NotFoundException("Resource not found");

    return resource.get();
  }

  public List<Resource> findAll() {
    return resourceRepository.findAll();
  }
}
