# Registro de usuário
<hr />

Um usuario deve poder criar uma conta e gerar suas credenciais para acessar o sistema.

## Funcionalidades da feature
- o usuario cria seus dados pessoais
- o usuario cria define seus dados de acesso


## Controller

<details>
    <summary>Request Data</summary>

```json
{
  "first_name": "string",
  "last_name": "string",
  "email": "string",
  "password": "string"
}
```
</details>

## Regra de negócio
Ao informar os dados de registro, a service deve realizar a seguinte lógica:

- Entidade`User`
  - adicionar o atributo `first_name`;
  - adicionar o atributo `last_name`;
  - adicionar o atributo `email`;

- Entidade `UserCredentials`
  - adicionar o atributo `userId (User.getId)` ;
  - adicionar o atributo `email`;
  - adicionar o atributo `password`;
    - para esse atributo, deve ser realizado a logica de criacao de salt e geracao da senha hasheada

Como o usuario e suas credenciais foram criados pelo fluxo normal, deve ser atribuido tambem, o `account_status` como `ACTIVE` 