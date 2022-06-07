@patient
Feature: Patient
  In order to maintain patient records 
  As a admin
  I want to add,edit,delete the patient records

  @addpatient @high
  Scenario: Add Valid Patient Record
    Given I have browser with openemr application
    When I enter username as 'admin'
    And I enter password as 'pass'
    And I select the language as 'English (Indian)'
    And I click on login
    And I click on patient menu
    And I click on new-search menu
    And I fill the form
      | firstname | lastname | dob        | gender |
      | John      | Wick     | 2022-06-06 | Male   |
    And I click on create new patient
    And I click on confirm create new patient
    And I store the alert text and handle it
    And I close happy birthday popup if avaiable
    Then I should have the alert text contains 'Tobacco'
    And I should get the added patient name as 'Medical Record Dashboard - John Wick'
