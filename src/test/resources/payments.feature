Feature: List of payments can be retrieved
  Scenario: client makes call to GET /v1/payments
    When the client calls /v1/payments
    Then the client receives status code of 200
    And the client receives payments response {"data":[{"type":"Payment","id":null,"version":0,"organisation_id":null}]}