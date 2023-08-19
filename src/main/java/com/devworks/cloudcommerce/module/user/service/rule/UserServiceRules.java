package com.devworks.cloudcommerce.module.user.service.rule;

import com.devworks.cloudcommerce.module.user.model.User;
import com.devworks.cloudcommerce.shared.util.GenericDto;

public interface UserServiceRules {
    GenericDto<User> create(GenericDto<User> request);
    User findByEmail(String email);
}
