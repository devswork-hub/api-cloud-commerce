package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import com.devworks.cloudcommerce.module.account.service.rule.RoleServiceRules;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleServiceRules {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role create(Role request) {
        var existsRole = roleRepository.findByName(request.getName());

        if(existsRole.isPresent()) {
            throw new BadRequestException("Role has already been declared");
        }

        return roleRepository.save(request);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found role with id " + id));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        roleRepository.deleteById(id);
    }
}
