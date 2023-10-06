package com.devworks.cloudcommerce.module.account.service.util;

import com.devworks.cloudcommerce.common.model.BaseServiceUtil;
import com.devworks.cloudcommerce.module.account.constants.ActionType;
import com.devworks.cloudcommerce.module.account.model.Module;
import com.devworks.cloudcommerce.module.account.model.Permission;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.repository.ActionRepository;
import com.devworks.cloudcommerce.module.account.service.ActionService;

import java.util.Set;

public class PermissionServiceUtil extends BaseServiceUtil {
    private PermissionServiceUtil() { super(); }

    public static Permission createPermission(Resource resource, ActionType actionType, Module module, ActionRepository actionRepository) {
        Permission permission = Permission.builder()
            .action(ActionService.getActionByNameInActionType(actionType, actionRepository))
            .resource(resource)
            .build();
        permission.setModules(Set.of(module));
        return permission;
    }
}

