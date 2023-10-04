package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.module.account.dto.UserDTO;
import com.devworks.cloudcommerce.module.account.model.Module;
import com.devworks.cloudcommerce.module.account.model.Permission;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccessService {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public AccessService(
            UserService userService,
            RoleRepository roleRepository
    ) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    public Map<String, Object> execute(AccessInput input) {
        var user = userService.findById(input.userUUID);

        Map<String, Object> data = new LinkedHashMap<>();
        List<Map<String, Object>> modules = generateModulesForUserRoles(user);

        data.put("modules", modules);

        return data;
    }

    private List<Map<String, Object>> generateModulesForUserRoles(UserDTO user) {
        List<Map<String, Object>> modules = new ArrayList<>();

        for (Role role : user.getRoles()) {
            List<Permission> rolePermissions = roleRepository.findPermissionsByRole(role);
            for (Permission permission : rolePermissions) {
                addPermissionToModules(permission, modules);
            }
        }

        return modules.stream().collect(Collectors.toMap(
            module -> module.get("name").toString(),
            module -> module,
            (existingModule, newModule) -> {
                // TODO
                // Resolve casting
                List<String> actions = (List<String>) existingModule.get("actions");
                List<Resource> resources = (List<Resource>) existingModule.get("resources");

                actions.addAll((List<String>) newModule.get("actions"));
                resources.addAll((List<Resource>) newModule.get("resources"));
                existingModule.put("actions", actions);
                existingModule.put("resources", resources);
                return existingModule;
            }
        )).values().stream().toList();
    }

    private void addPermissionToModules(Permission permission, List<Map<String, Object>> modules) {
        for (Module module : permission.getModules()) {
            String moduleName = module.getName();
            String actionName = permission.getAction().getName();
            Resource resource = permission.getResource();

            Map<String, Object> existingModule;

            List<Resource> resources = new ArrayList<>();
            List<String> actions = new ArrayList<>();
            actions.add(actionName);
            resources.add(Resource.builder()
                .name(resource.getName())
                .active(resource.isActive())
                .path(resource.getPath())
                .build());

            existingModule = createNewModule(moduleName, module, resources, actions);
            modules.add(existingModule);
        }
    }

    private Map<String, Object> createNewModule(String moduleName, Module module, List<Resource> resources, List<String> actions) {
        Map<String, Object> newModule = new LinkedHashMap<>();
        newModule.put("name", moduleName);
        newModule.put("active", module.isActive());
        newModule.put("resources", resources);
        newModule.put("actions", actions);
        return newModule;
    }

    public record AccessInput(UUID userUUID) {}
}