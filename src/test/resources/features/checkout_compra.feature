@Checkout
Feature: Fluxo de Checkout

  Scenario Outline: Finalizar compra com sucesso
    Given que o usuário acessa Shop Four Grid e adiciona produtos ao carrinho
    And o total do carrinho deve ser <total_price>
    When clica no botão Proceed to Checkout
    And preenche os campos do formulário de checkout
      | First Name       | <first_name>   |
      | Last Name        | <last_name>    |
      | Company Name     | <company_name> |
      | Email Address    | <email>        |
      | Country          | <country>      |
      | State/City       | <state_city>   |
      | Zip Code         | <zip_code>     |
      | Full Address     | <address>      |
      | Additional Notes | <notes>        |
    And clica no botão Save
    And clica no botão Place Order
    Then a mensagem "Order success!" deve ser exibida

    Examples:
      | first_name | last_name | company_name | email             | country      | state_city     | zip_code | address       | notes               | total_price |
      | John       | Doe       | ACME Inc.    | john@example.com  | Afghanistan  | Aland Islands  | 10001    | 123 Main St   | Testando background | 222.00      |
