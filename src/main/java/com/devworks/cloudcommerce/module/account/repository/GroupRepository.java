package com.devworks.cloudcommerce.module.account.repository;

import com.devworks.cloudcommerce.module.account.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {}
