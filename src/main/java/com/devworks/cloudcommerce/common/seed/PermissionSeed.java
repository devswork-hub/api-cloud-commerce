package com.devworks.cloudcommerce.common.seed;

import com.devworks.cloudcommerce.module.account.constants.ActionType;
import com.devworks.cloudcommerce.module.account.model.Action;
import com.devworks.cloudcommerce.module.account.model.Module;
import com.devworks.cloudcommerce.module.account.model.Permission;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.repository.ActionRepository;
import com.devworks.cloudcommerce.module.account.repository.ModuleRepository;
import com.devworks.cloudcommerce.module.account.repository.PermissionRepository;
import com.devworks.cloudcommerce.module.account.repository.ResourceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class PermissionSeed {
    private final PermissionRepository permissionRepository;
    private final ActionRepository actionRepository;
    private final ModuleRepository moduleRepository;
    private final ResourceRepository resourceRepository;

    public PermissionSeed(
        PermissionRepository permissionRepository,
        ActionRepository actionRepository,
        ModuleRepository moduleRepository,
        ResourceRepository resourceRepository)
    {
        this.permissionRepository = permissionRepository;
        this.actionRepository = actionRepository;
        this.moduleRepository = moduleRepository;
        this.resourceRepository = resourceRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        var module = getModuleByName("Dashboard");

        var ordersResource = resourceRepository.save(
            Resource.builder()
                .path("/orders")
                .name("Orders")
                .build()
        );

        var whishlistResource = resourceRepository.save(
            Resource.builder()
                .path("/whislist")
                .name("Whishlist")
                .build()
        );

        var supportResource = resourceRepository.save(
            Resource.builder()
                .path("/support-tickets")
                .name("Support Tickets")
                .build()
        );

        var permissions = new HashSet<Permission>();
        permissions.add(createPermission(ordersResource, ActionType.READ, module));
        permissions.add(createPermission(whishlistResource, ActionType.READ, module));
        permissions.add(createPermission(whishlistResource, ActionType.CREATE, module));
        permissions.add(createPermission(whishlistResource, ActionType.DELETE, module));
        permissions.add(createPermission(whishlistResource, ActionType.WRITE, module));
        permissions.add(createPermission(supportResource, ActionType.READ, module));
        permissions.add(createPermission(supportResource, ActionType.WRITE, module));

        permissionRepository.saveAll(permissions);
    }

    public Resource registerResource(String name, String path) {
        Resource resource = Resource.builder()
                .name(name)
                .path(path)
                .active(true)  // Defina o status do recurso conforme necessário
                .build();

        return resourceRepository.save(resource);
    }

    public Permission createPermission(Resource resource, ActionType actionType, Module module) {
        Permission permission = Permission.builder()
                .action(getActionByName(actionType))
                .resource(resource)
                .build();
        permission.setModules(Set.of(module));
        return permission;
    }

    public Action getActionByName(ActionType action) {
        return actionRepository.findByName(action.getName())
            .orElseGet(() -> actionRepository.save(Action.builder()
                .name(action.getName())
                .build()));
    }

    public Module getModuleByName(String name) {
        return moduleRepository.findByName(name)
            .orElseGet(() -> moduleRepository.save(Module.builder()
                .name(name)
                .active(true)  // Set the module to active or as needed
                .build()));
    }

    public Module getOrCreateModule(String name) {
        Module module = moduleRepository.findByName(name).orElse(null);
        if (module == null) {
            module = moduleRepository.save(Module.builder()
                .name(name)
                .active(true)
                .build());
        }
        return module;
    }
}
