package com.devworks.cloudcommerce.module.account.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
public enum ActionType {
    CREATE("CREATE", "Permite criar um novo registro"),
    READ("READ", "Permite a leitura de registros"),
    WRITE("WRITE", "Permite a edição de registros"),
    DELETE("DELETE", "Permite a exclusão de registros"),
    APPEND("APPEND", "Permite conceder permissão(ões) para adicionar informações"),
    ASSIGN("ASSIGN", "Permite atribuir recursos, tarefas ou responsabilidades"),
    SHARE("SHARE", "Permite compartilhar registros"),
    EXPORT("EXPORT", "Permite exportar registros");

    private final String name;
    private final String description;

    ActionType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Set<ActionType> allActionTypes() {
        return new HashSet<>(Arrays.asList(values()));
    }
}
