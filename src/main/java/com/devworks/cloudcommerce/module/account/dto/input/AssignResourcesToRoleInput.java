package com.devworks.cloudcommerce.module.account.dto.input;

import java.util.Set;
import java.util.UUID;

public record AssignResourcesToRoleInput(Set<UUID> resourcesIds) {
}
