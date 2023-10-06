package com.devworks.cloudcommerce.module.account.service.util;

import com.devworks.cloudcommerce.common.model.BaseServiceUtil;
import com.devworks.cloudcommerce.module.account.model.Module;
import com.devworks.cloudcommerce.module.account.repository.ModuleRepository;

public class ModuleServiceUtils extends BaseServiceUtil {
    private ModuleServiceUtils() { super(); }

    public static Module getOrCreateModule(String name, ModuleRepository moduleRepository) {
        return moduleRepository.findByName(name)
            .orElseGet(() -> moduleRepository.save(Module.builder()
                .name(name)
                .active(true)
                .build()));
    }
}


