package com.devworks.cloudcommerce.module.account.constants;

public enum ResourcePermissionTypes {
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

    ResourcePermissionTypes(String name, String description) {
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
