package com.devworks.cloudcommerce.module.account.service;

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
        var user = userService.findById(input.userUUID());

        Map<String, Object> data = new LinkedHashMap<>();
        List<Map<String, Object>> modules = new ArrayList<>();

        Map<String, Set<String>> moduleActions = new HashMap<>();

        for (Role role : user.getRoles()) {
            List<Permission> permissions = roleRepository.findPermissionsByRole(role);
            for (Permission permission : permissions) {
                for (Module module : permission.getModules()) {
                    String moduleName = module.getName();
                    Set<String> actions = moduleActions.computeIfAbsent(moduleName, k -> new HashSet<>());
                    actions.add(permission.getAction().getName());
                }
            }
        }

        for (Map.Entry<String, Set<String>> entry : moduleActions.entrySet()) {
            Map<String, Object> moduleData = new LinkedHashMap<>();
            moduleData.put("name", entry.getKey());
            moduleData.put("active", true);

            Map<String, Object> resource = new LinkedHashMap<>();
            resource.put("name", "Wishlist");
            resource.put("path", "/wishlist");
            resource.put("active", false);
            resource.put("actions", new ArrayList<>(entry.getValue()));

            moduleData.put("resource", resource);
            modules.add(moduleData);
        }

        data.put("modules", modules);

        return data;
    }

    public record AccessInput(UUID userUUID) {}
}
