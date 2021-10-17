@Task1
Feature: Task 1 - REST API - separate scenarios

  @Precondition
  Scenario: Pre-Condition: Get Photos {saved_photos}
    Given user gets photos

  Scenario: Validate number of photos
    Given precondition "saved_photos" is loaded
    Then number of photos is greater than "0"

  Scenario: Find photo by title
    Given precondition "saved_photos" is loaded
    Then get photo with "non sit quo" title

  Scenario: Remove all photos that have albumId different than 100
    Given precondition "saved_photos" is loaded
    Then remove all photos that have "albumId" different than 100

  Scenario: Remove all photos that don't have [error] word in title
    Given precondition "saved_photos" is loaded
    Then remove all photos that do not contain the word "error" in title