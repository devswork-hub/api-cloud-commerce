package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.dto.ResourceDTO;
import com.devworks.cloudcommerce.module.account.dto.input.UpdateResourceInput;
import com.devworks.cloudcommerce.module.account.mapper.ResourceMapper;
import com.devworks.cloudcommerce.module.account.model.Department;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ResourceService {
  private final ActionService actionService;
  private final ResourceRepository resourceRepository;

  public ResourceService(ActionService actionService, ResourceRepository resourceRepository) {
    this.actionService = actionService;
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

    return resourceRepository.save(resource);
  }

  @Transactional
  public Resource update(UUID resourceId, UpdateResourceInput input) {
    var findedResource = findById(resourceId);

    if (input.name() != null && !input.name().isEmpty())
      findedResource.setName(input.name());

    findedResource.setActive(input.active());

    if (input.actions() != null && !input.actions().isEmpty())
      findedResource.setActions(actionService.getValidActions(input.actions()));

    findedResource.setUpdatedAt(LocalDateTime.now());

    return resourceRepository.save(findedResource);
  }

  public Resource findById(UUID id) {
      var resource = resourceRepository.findById(id);

      if(resource.isEmpty())
        throw new NotFoundException("Not found resource with id " + id);

      return resource.get();
  }

  public List<Resource> findAll() {
    return resourceRepository.findAll();
  }

  public void deleteById(UUID id) {
    findById(id);
    resourceRepository.deleteById(id);
  }

  public Set<Resource> getValidResourcesIds(Set<UUID> resources) {
    var validResources = new HashSet<Resource>();
    for (UUID resource : resources) {
      var existsResource = resourceRepository.findById(resource)
              .orElseThrow(() -> new NotFoundException("Resource not found " + resource));
      validResources.add(existsResource);
    }
    return validResources;
  }
}
