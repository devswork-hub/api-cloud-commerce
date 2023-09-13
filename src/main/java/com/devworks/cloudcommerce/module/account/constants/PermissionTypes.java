package com.devworks.cloudcommerce.module.account.constants;

import lombok.Getter;

@Getter
public enum PermissionTypes {
    CREATE("CREATE", "Permite criar um novo registro"),
    READ("READ", "Permite a leitura de informações e gerenciamento de produtos, pedidos e usuários por um gerente de loja, além de acesso a relatórios e análises."),
    WRITE("WRITE", "Administrador do sistema com acesso total a todas as funcionalidades, configurações e recursos da plataforma."),
    DELETE("DELETE", "Permite a exclusão de registros e gerenciamento de produtos, pedidos e usuários por um gerente de loja, além de acesso a relatórios e análises."),
    APPEND("APPEND", "Concede permissão para adicionar informações ou recursos em todas as funcionalidades, configurações e recursos da plataforma, destinado ao administrador do sistema."),
    APPEND_TO("APPEND_TO", "Permite adicionar informações ou recursos a registros existentes, destinado a um gerente de loja com controle sobre produtos, pedidos e usuários, além de acesso a relatórios e análises."),
    ASSIGN("ASSIGN", "Permite atribuir recursos, tarefas ou responsabilidades em todas as funcionalidades, configurações e recursos da plataforma, destinado ao administrador do sistema."),
    SHARE("SHARE", "Oferece permissão a um cliente registrado para acessar recursos relacionados à compra e compartilhamento de informações.");

    private final String name;
    private final String description;

    PermissionTypes(String name, String description) {
        this.name = name;
        this.description = description;
    }
//
//    CREATE("CREATE", "Permite criar um novo registro", "ROLE_CREATE", "RESOURCE_CREATE"),
//    READ("READ", "Permite a leitura de informações", "ROLE_READ", "RESOURCE_READ"),
//    WRITE("WRITE", "Administrador com acesso total", "ROLE_WRITE", "RESOURCE_WRITE"),
//    DELETE("DELETE", "Permite a exclusão de registros", "ROLE_DELETE", "RESOURCE_DELETE"),
//    APPEND("APPEND", "Administração com acesso total", "ROLE_APPEND", "RESOURCE_APPEND"),
//    APPEND_TO("APPEND_TO", "Permite adicionar informações", "ROLE_APPEND_TO", "RESOURCE_APPEND_TO"),
//    ASSIGN("ASSIGN", "Permite atribuir recursos", "ROLE_ASSIGN", "RESOURCE_ASSIGN"),
//    SHARE("SHARE", "Oferece permissão a um cliente", "ROLE_SHARE", "RESOURCE_SHARE");
//
//    private final String description;
//    private final String rolePermission;  // Permissão a nível de função (role)
//    private final String resourcePermission;  // Permissão a nível de recurso (resource)
//
//    PermissionTypes(String description, String rolePermission, String resourcePermission, String resourceCreate) {
//        this.description = description;
//        this.rolePermission = rolePermission;
//        this.resourcePermission = resourcePermission;
//    }

}
