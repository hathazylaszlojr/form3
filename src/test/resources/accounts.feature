# These tests are mostly covering sunshine path, they could also cover invalid API requests
Feature: Manage (add, list, delete, search) accounts
  Scenario: client makes call to GET /v1/accounts
    Given Accounts endpoint having account with id 123456
    Given Accounts endpoint having account with id 123457
    When the client calls /v1/accounts
    Then the client receives status code of 200
    And the client receives 2 accounts containing account with id 123456
    And the client receives 2 accounts containing account with id 123457

  Scenario: client makes call to DELETE /v1/accounts
    Given Accounts endpoint having account with id 123456
    Given Accounts endpoint having account with id 123457
    When the client calls delete account with id 123457
    And the client is calling /v1/accounts
    Then the client receives status code of 200
    And the client receives 1 accounts containing account with id 123456

  Scenario: client makes call to PUT /v1/accounts
    Given Accounts endpoint having account with id 123456
    When the client calls update account with id 123456 to name John Smith
    And the client is calling /v1/accounts
    Then the client receives status code of 200
    And the client receives 1 accounts containing account with id 123456
    And the client receives 1 accounts containing account with name John Smith
    