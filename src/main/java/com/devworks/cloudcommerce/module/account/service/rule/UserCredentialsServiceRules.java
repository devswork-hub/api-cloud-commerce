package com.devworks.cloudcommerce.module.account.service.rule;

import com.devworks.cloudcommerce.module.account.model.Credentials;

public interface UserCredentialsServiceRules {
    Credentials findByEmailAndPassword(String email, String password);
}
