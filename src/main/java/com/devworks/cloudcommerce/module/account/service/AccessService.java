package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.module.account.dto.UserDTO;
import com.devworks.cloudcommerce.module.account.model.Module;
import com.devworks.cloudcommerce.module.account.model.Permission;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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

        return modules;
    }

    private void addPermissionToModules(Permission permission, List<Map<String, Object>> modules) {
        for (Module module : permission.getModules()) {
            String moduleName = module.getName();
            String actionName = permission.getAction().getName();
            Map<String, Object> existingModule = findExistingModule(modules, moduleName);

            if (existingModule == null) {
                existingModule = createNewModule(moduleName, module);
                modules.add(existingModule);
            }

            List<String> actions = (List<String>) existingModule.get("actions");
            actions.add(actionName);
        }
    }

    private Map<String, Object> findExistingModule(List<Map<String, Object>> modules, String moduleName) {
        for (Map<String, Object> moduleData : modules) {
            if (moduleData.get("name").equals(moduleName)) {
                return moduleData;
            }
        }
        return null;
    }

    private Map<String, Object> createNewModule(String moduleName, Module module) {
        Map<String, Object> newModule = new LinkedHashMap<>();
        newModule.put("name", moduleName);
        newModule.put("active", true);
        newModule.put("resource", createResource(module));
        newModule.put("actions", new ArrayList<String>());
        return newModule;
    }

    private Map<String, Object> createResource(Module module) {
        Map<String, Object> resource = new LinkedHashMap<>();
        resource.put("name", module.getName());
        resource.put("active", module.isActive());
        resource.put("path", new ArrayList<>());
        return resource;
    }


    public record AccessInput(UUID userUUID) {}
}