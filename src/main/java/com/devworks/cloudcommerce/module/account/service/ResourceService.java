package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.utils.Validator;
import com.devworks.cloudcommerce.module.account.constants.RolesTypes;
import com.devworks.cloudcommerce.module.account.dto.ResourceDto;
import com.devworks.cloudcommerce.module.account.mapper.ResourceMapper;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.Group;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.repository.ResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Resource create(ResourceDto request) {
        var resource = resourceRepository.save(ResourceMapper.toEntity(request));
        return resource;

    }
}
