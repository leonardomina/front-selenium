Feature: Login na aplicação

  Scenario: Usuário realiza login com sucesso
    Given que o usuário está na página de login
    When o usuário insere credenciais válidas
    And clica no botão de login
    Then o usuário deve ser redirecionado para a página inicial
