package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.dto.ResourceDTO;
import com.devworks.cloudcommerce.module.account.mapper.ResourceMapper;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
  private final ResourceRepository resourceRepository;

  public ResourceService(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }

  public Resource create(ResourceDTO request) {
    var existsResource = resourceRepository.findByName(request.getName());

    if (existsResource.isPresent())
      throw new BadRequestException("Resource already exists");

    return resourceRepository.save(ResourceMapper.toEntity(request));
  }

  public Resource findByName(String name) {
    return resourceRepository.findByName(name)
        .orElseThrow(() -> new NotFoundException("Resource not found"));
  }

  public List<Resource> findAll() {
    return resourceRepository.findAll();
  }
}
