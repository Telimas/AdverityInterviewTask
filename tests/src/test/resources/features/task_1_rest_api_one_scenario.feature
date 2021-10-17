@Task1
Feature: Task 1 - REST API - one scenario

  Scenario: Get and validate photos {saved_photos}
    Given user gets photos
    Then number of photos is greater than "0"
    And get photo with "non sit quo" title
    And remove all photos that have "albumId" different than 100
    And remove all photos that do not contain the word "error" in title