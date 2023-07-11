@regression
Feature: Get Category Details

  Scenario: User is successfully retrieved Name from response
    Given Get category request endpoint: "https://api.tmsandbox.co.nz/v1/Categories/6329/Details.json?catalogue=false"
    When User sends request to get category
    Then Name value on the response is "Home & garden"

  Scenario: User is successfully retrieved CanRelist from response
    Given Get category request endpoint: "https://api.tmsandbox.co.nz/v1/Categories/6329/Details.json?catalogue=false"
    When User sends request to get category
    Then CanRelist value on the response is True

  Scenario: User is successfully retrieved description from response
    Given Get category request endpoint: "https://api.tmsandbox.co.nz/v1/Categories/6329/Details.json?catalogue=false"
    When User sends request to get category
    Then Promotions with the Name:Feature has description equal to "Better position in category"