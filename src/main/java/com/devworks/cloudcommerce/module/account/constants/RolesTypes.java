package com.devworks.cloudcommerce.module.account.constants;

public enum RolesTypes {
    ADMIN("ADMIN", "Administrador do sistema com acesso total a todas as funcionalidades, configurações e recursos da plataforma."),
    MANAGER("MANAGER", "Gerente de loja com permissões para gerenciar produtos, pedidos e usuários, além de acesso a relatórios e análises."),
    CUSTOMER("CUSTOMER", "Cliente registrado com acesso a recursos de compra...");

    private final String name;
    private final String description;

    RolesTypes(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
