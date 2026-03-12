Feature: Swag Labs Authentication

  @smoke
  Scenario: Standard user can log in successfully
    Given the user is on the login page
    When the user enters standard credentials
    Then the user should be redirected to the inventory page