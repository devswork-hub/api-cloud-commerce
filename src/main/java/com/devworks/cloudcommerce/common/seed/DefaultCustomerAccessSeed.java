//package com.devworks.cloudcommerce.common.seed;
//
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
//public class DefaultCustomerAccessSeed {
//    private final ActionRepository actionRepository;
//    private final ModuleRepository moduleRepository;
//    private final PermissionRepository permissionRepository;
//    private final RoleRepository roleRepository;
//    private final ResourceRepository resourceRepository;
//
//    private final PermissionService permissionService;
//    private final RoleService roleService;
//
//    public DefaultCustomerAccessSeed(
//            ActionRepository actionRepository,
//            ModuleRepository moduleRepository,
//            PermissionRepository permissionRepository,
//            ResourceRepository resourceRepository,
//            RoleService roleService,
//            RoleRepository roleRepository, PermissionService permissionService) {
//        this.actionRepository = actionRepository;
//        this.moduleRepository = moduleRepository;
//        this.permissionRepository = permissionRepository;
//        this.resourceRepository = resourceRepository;
//        this.roleService = roleService;
//        this.roleRepository = roleRepository;
//        this.permissionService = permissionService;
//    }
//
//    @PostConstruct
//    @Transactional
//    public void init() {
//        // Modules
//        var dashboardModule = ModuleServiceUtils.getOrCreateModule("Dashboard", moduleRepository);
//        var accountSettingsModule = ModuleServiceUtils.getOrCreateModule("Account Settings", moduleRepository);
//
//        // Resources
//        var ordersResource = ResourceServiceUtil.registerResource("Orders", "/orders", resourceRepository);
//        var whishlistResource = ResourceServiceUtil.registerResource("Whishlist", "/whislist", resourceRepository);
//        var supportResource = ResourceServiceUtil.registerResource("Support Tickets", "/support-tickets", resourceRepository);
//        var profileInfoResource = ResourceServiceUtil.registerResource("Profile Info", "/profile", resourceRepository);
//        var addressesResource = ResourceServiceUtil.registerResource("Addresses", "/addresses", resourceRepository);
//        var paymentMethodsResource = ResourceServiceUtil.registerResource("Payment Methods", "/payment-methods", resourceRepository);
//
//        // Permissions
//        var permissions = new HashSet<Permission>();
//        permissions.add(PermissionServiceUtil.createPermission(ordersResource, ActionType.READ, dashboardModule, actionRepository));
//
//        permissions.add(PermissionServiceUtil.createPermission(whishlistResource, ActionType.READ, dashboardModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(whishlistResource, ActionType.CREATE, dashboardModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(whishlistResource, ActionType.DELETE, dashboardModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(whishlistResource, ActionType.WRITE, dashboardModule, actionRepository));
//
//        permissions.add(PermissionServiceUtil.createPermission(supportResource, ActionType.READ, dashboardModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(supportResource, ActionType.WRITE, dashboardModule, actionRepository));
//
//        permissions.add(PermissionServiceUtil.createPermission(profileInfoResource, ActionType.READ, accountSettingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(profileInfoResource, ActionType.WRITE, accountSettingsModule, actionRepository));
//
//        permissions.add(PermissionServiceUtil.createPermission(addressesResource, ActionType.READ, accountSettingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(addressesResource, ActionType.WRITE, accountSettingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(addressesResource, ActionType.DELETE, accountSettingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(addressesResource, ActionType.CREATE, accountSettingsModule, actionRepository));
//
//        permissions.add(PermissionServiceUtil.createPermission(paymentMethodsResource, ActionType.READ, accountSettingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(paymentMethodsResource, ActionType.WRITE, accountSettingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(paymentMethodsResource, ActionType.DELETE, accountSettingsModule, actionRepository));
//        permissions.add(PermissionServiceUtil.createPermission(paymentMethodsResource, ActionType.CREATE, accountSettingsModule, actionRepository));
//
//        var savedPermissions = permissionRepository.saveAll(permissions);
//
//        var customerRole = roleService.findByName(RolesType.CUSTOMER.getName());
//
//        RoleServiceUtil.assignPermissions(
//            customerRole.getId(),
//            savedPermissions.stream().map(Permission::getId).collect(Collectors.toSet()),
//            roleRepository,
//            roleService,
//            permissionService);
//    }
//}
