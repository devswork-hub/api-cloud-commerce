package com.devworks.cloudcommerce.module.account.constants;

import lombok.Getter;

@Getter
public enum AccountStatusTypes {
    ACTIVE("Ativo", "A conta está em um estado normal e pode ser usada para acessar o sistema"),
    INACTIVE("Inativo", "A conta está temporariamente inativa, geralmente por escolha do usuário. Eles podem reativar a conta quando desejarem"),
    SUSPENDED("Suspenso", "A conta foi suspensa devido a violações de termos de uso ou outras razões. O acesso ao sistema é negado durante a suspensão"),
    AWAITING_CONFIRMATION("Aguardando Confirmação", "A conta foi criada, mas ainda não foi confirmada por meio de um link de confirmação enviado por email."),
    BLOCKED("Bloqueado", "A conta foi bloqueada devido a atividades suspeitas ou violações graves dos termos de uso. O acesso é negado até que a situação seja resolvida."),
    CLOSED("Encerrado", "A conta foi encerrada ou excluída permanentemente pelo usuário ou pelo administrador do sistema.");

    private final String name;
    private final String description;

    AccountStatusTypes(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
