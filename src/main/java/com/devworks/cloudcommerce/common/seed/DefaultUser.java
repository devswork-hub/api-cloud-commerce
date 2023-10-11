//package com.devworks.cloudcommerce.common.seed;
//
//import com.devworks.cloudcommerce.common.model.BaseSeed;
//import com.devworks.cloudcommerce.module.account.constants.RolesType;
//import com.devworks.cloudcommerce.module.account.dto.UserDTO;
//import com.devworks.cloudcommerce.module.account.service.RoleService;
//import com.devworks.cloudcommerce.module.account.service.UserService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//
//@Component
//public class DefaultUser extends BaseSeed {
//    private final RoleService roleService;
//    private final UserService userService;
//
//    public DefaultUser(RoleService roleService, UserService userService) {
//        this.roleService = roleService;
//        this.userService = userService;
//    }
//
//    @Override
//    @PostConstruct
//    protected void init() {
//        userService.create(UserDTO.builder()
//            .firstName("Admin")
//            .email("admin@email.com")
//            .roles(Set.of(roleService.findByName(RolesType.ADMIN.getName())))
//            .build());
//    }
//}
//
