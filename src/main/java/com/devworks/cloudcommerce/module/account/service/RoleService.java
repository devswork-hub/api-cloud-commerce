package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.common.utils.Validator;
import com.devworks.cloudcommerce.module.account.constants.RolesTypes;
import com.devworks.cloudcommerce.module.account.dto.input.AssignResourcesToRoleInput;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.repository.ResourceRepository;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import com.devworks.cloudcommerce.module.account.service.rule.RoleServiceRules;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService implements RoleServiceRules {
    private final RoleRepository roleRepository;
    private final ResourceRepository resourceRepository;

    public RoleService(RoleRepository roleRepository, ResourceRepository resourceRepository) {
        this.roleRepository = roleRepository;
        this.resourceRepository = resourceRepository;
    }


    @Override
    public Role create(Role request) {
        var existsRole = roleRepository.findByName(request.getName());
        if(existsRole.isPresent()) {
            throw new BadRequestException("Role has already been declared");
        }

        if (!Validator.isValidEnum(RolesTypes.class, request.getName())) {
            throw new BadRequestException("Invalid role type");
        }

        return roleRepository.save(request);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found role with id " + id));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        roleRepository.deleteById(id);
    }

    @Transactional
    public void assignResourcesToRole(UUID roleId, AssignResourcesToRoleInput input) {
        var role = findById(roleId);
        var newResources = new HashSet<Resource>();

        if (hasResourcesAssigned(roleId)) {
            throw new IllegalStateException("Recursos já estão atribuídos a esta role.");
        }

        for(UUID resourceId : input.resourcesIds()) {
            var existsResource = resourceRepository.findById(resourceId);

            if(existsResource.isPresent())
                newResources.add(existsResource.get());
            else
                throw new BadRequestException("Recurso não encontrado com ID: " + resourceId);
        }
        role.setResources(newResources);
        roleRepository.save(role);
    }

    private boolean hasResourcesAssigned(UUID roleId) {
        Role role = findById(roleId);
        return role != null && !role.getResources().isEmpty();
    }
}
