Feature: Alelo

Scenario: Search Director and Movie
    Given I access google
    When I search about "Snider" and your movie "Liga da Justiça"
    Then I recover the approximate amout of results