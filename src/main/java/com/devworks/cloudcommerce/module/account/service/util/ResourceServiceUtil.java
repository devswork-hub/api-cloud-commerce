package com.devworks.cloudcommerce.module.account.service.util;

import com.devworks.cloudcommerce.common.model.BaseServiceUtil;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.repository.ResourceRepository;

public class ResourceServiceUtil extends BaseServiceUtil {
    public ResourceServiceUtil() {super();}

    public static Resource registerResource(String name, String path, ResourceRepository resourceRepository) {
        var resource = Resource.builder()
            .name(name)
            .path(path)
            .active(true)
            .build();

        return resourceRepository.save(resource);
    }
}
