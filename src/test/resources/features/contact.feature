Feature: Contact

  Scenario: Verify customer inputs invalid email
    Given The customer tries to Contact us
    When The customer puts invalid email address
    Then Please enter a valid email message should be displayed