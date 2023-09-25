package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.common.utils.Validator;
import com.devworks.cloudcommerce.module.account.constants.RolesType;
import com.devworks.cloudcommerce.module.account.dto.RoleDTO;
import com.devworks.cloudcommerce.module.account.mapper.RoleMapper;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import com.devworks.cloudcommerce.module.account.service.rule.RoleServiceRules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService implements RoleServiceRules {
  private final RoleRepository roleRepository;

  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public RoleDTO create(RoleDTO request) {
    if(!Validator.isValidEnum(RolesType.class, request.getName()))
      throw new BadRequestException("Invalid role");

    var existsRole = roleRepository.findByName(request.getName());
    if (existsRole.isPresent())
      throw new BadRequestException("Role has already been declared");

    Role role = RoleMapper.toEntity(request);
    Role savedRole = roleRepository.save(role);
    return RoleMapper.toDto(savedRole);
  }

  @Override
  public List<RoleDTO> findAll() {
    return roleRepository.findAll().stream()
      .map(RoleMapper::toDto)
      .toList();
  }

  @Override
  public RoleDTO findById(UUID id) {
    Role role = roleRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Not found role with id " + id));
    return RoleMapper.toDto(role);
  }

  @Override
  public void delete(UUID id) {
    findById(id);
    roleRepository.deleteById(id);
  }

  public Role findByName(String role) {
    return roleRepository.findByName(role)
        .orElseThrow(() -> new NotFoundException("Role not found with name " + role));
  }

  public boolean existsRole(String role) {
    return roleRepository.existsByName(role);
  }
}


