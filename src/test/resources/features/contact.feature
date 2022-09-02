Feature: Contact

  Scenario: Verify customer inputs invalid email
    Given The customer tries to Contact us
    When The customer puts invalid email address
    Then Please enter a valid email message should be displayed

  Scenario: Verify mandatory field error message displayed
    Given The customer tries to Contact us
    When Customer submits empty info
    Then Error message for mandatory information are displayed

  Scenario: Verify thanks message after populating mandatory fields
    Given The customer tries to Contact us
    When Customer populates mandatory field
    | Forename | Email         | Message |
    | John     | john@test.com | Hello |
    | Doe     | doe@test.com | Hi |
    And Customer clicks submit button
    Then Thanks message is displayed