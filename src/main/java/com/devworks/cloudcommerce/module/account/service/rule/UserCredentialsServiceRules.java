package com.devworks.cloudcommerce.module.account.service.rule;

import com.devworks.cloudcommerce.module.account.model.UserCredentials;

public interface UserCredentialsServiceRules {
    UserCredentials findByEmailAndPassword(String email, String password);
}
