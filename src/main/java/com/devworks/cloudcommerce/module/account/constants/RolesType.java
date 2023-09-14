package com.devworks.cloudcommerce.module.account.constants;

import lombok.Getter;

@Getter
public enum RolesType {
    ADMIN("ADMIN", "Administrador do sistema com acesso total a todas as funcionalidades, configurações e recursos da plataforma."),
    MANAGER("MANAGER", "Gerente de loja com permissões para gerenciar produtos, pedidos e usuários, além de acesso a relatórios e análises."),
    CUSTOMER("CUSTOMER", "Cliente registrado com acesso a recursos de compra...");

    private final String name;
    private final String description;

    RolesType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
