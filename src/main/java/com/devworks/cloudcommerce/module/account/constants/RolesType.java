package com.devworks.cloudcommerce.module.account.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static Set<RolesType> allRoleTypes() {
        return new HashSet<>(Arrays.asList(values()));
    }
    public static Set<RolesType> defaultCommonRoles() {
        return new HashSet<>(List.of(RolesType.CUSTOMER));
    }
    public static Set<RolesType> defaultAdminRoles() {
        return new HashSet<>(Arrays.asList(RolesType.ADMIN, RolesType.MANAGER));
    }
}
