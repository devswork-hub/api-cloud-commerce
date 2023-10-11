//package com.devworks.cloudcommerce.common.seed;
//
//import com.devworks.cloudcommerce.common.model.BaseSeed;
//import com.devworks.cloudcommerce.module.account.constants.ActionType;
//import com.devworks.cloudcommerce.module.account.constants.RolesType;
//import com.devworks.cloudcommerce.module.account.model.Permission;
//import com.devworks.cloudcommerce.module.account.repository.*;
//import com.devworks.cloudcommerce.module.account.service.PermissionService;
//import com.devworks.cloudcommerce.module.account.service.RoleService;
//import com.devworks.cloudcommerce.module.account.service.util.ModuleServiceUtils;
//import com.devworks.cloudcommerce.module.account.service.util.PermissionServiceUtil;
//import com.devworks.cloudcommerce.module.account.service.util.ResourceServiceUtil;
//import com.devworks.cloudcommerce.module.account.service.util.RoleServiceUtil;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.stream.Collectors;
//
//@Component
//public class DefaultAdminAccessSeed extends BaseSeed {
//    private final ActionRepository actionRepository;
//    private final ModuleRepository moduleRepository;
//    private final PermissionRepository permissionRepository;
//    private final ResourceRepository resourceRepository;
//    private final RoleRepository roleRepository;
//    private final PermissionService permissionService;
//    private final RoleService roleService;
//
//    public DefaultAdminAccessSeed(
//        ActionRepository actionRepository,
//        ModuleRepository moduleRepository,
//        PermissionRepository permissionRepository,
//        ResourceRepository resourceRepository,
//        RoleService roleService,
//        RoleRepository roleRepository,
//        PermissionService permissionService
//    ) {
//        this.moduleRepository = moduleRepository;
//        this.resourceRepository = resourceRepository;
//        this.actionRepository = actionRepository;
//        this.permissionRepository = permissionRepository;
//        this.roleService = roleService;
//        this.roleRepository = roleRepository;
//        this.permissionService = permissionService;
//    }
//
//    @PostConstruct
//    @Transactional
//    @Override
//    public void init() {
//        // Modules
//        var settingsModule = ModuleServiceUtils.getOrCreateModule("Settings", moduleRepository);
//
//        // Resources
//        var profileResource = ResourceServiceUtil.registerResource("Profile", "/profile", resourceRepository);
//        var paymentMethodsResource = ResourceServiceUtil.registerResource("Payment Methods", "/payment-methods", resourceRepository);
//        var shippingSettingResource = ResourceServiceUtil.registerResource("Shipping", "/shipping", resourceRepository);
//
//
//        // Permissions
//        var permissions = new HashSet<Permission>();
//
//        permissions.add(PermissionServiceUtil.createPermission(profileResource, ActionType.READ, settingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(profileResource, ActionType.WRITE, settingsModule, actionRepository));
//
//        permissions.add(PermissionServiceUtil.createPermission(paymentMethodsResource, ActionType.READ, settingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(paymentMethodsResource, ActionType.WRITE, settingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(paymentMethodsResource, ActionType.DELETE, settingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(paymentMethodsResource, ActionType.CREATE, settingsModule, actionRepository));
//
//        permissions.add(PermissionServiceUtil.createPermission(shippingSettingResource, ActionType.READ, settingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(shippingSettingResource, ActionType.WRITE, settingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(shippingSettingResource, ActionType.DELETE, settingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(shippingSettingResource, ActionType.CREATE, settingsModule, actionRepository));
//
//        var savedPermissions = permissionRepository.saveAll(permissions);
//
//        var adminRole = roleService.findByName(RolesType.ADMIN.getName());
//
//        RoleServiceUtil.assignPermissions(
//            adminRole.getId(),
//            savedPermissions.stream().map(Permission::getId).collect(Collectors.toSet()),
//            roleRepository,
//            roleService,
//            permissionService
//        );
//    }
//}
