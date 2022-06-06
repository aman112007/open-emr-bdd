Feature: Login
  In order to maintain the health records
  As a admin
  I want to access the openemr portal
  
  Scenario: Valid Credential
  Given I have browser with openemr application
  When I enter username as 'admin'
  And I enter password as 'pass'
  And I select the language as 'English (Indian)'
  And I click on login
  Then I should get access to the portal with the title as 'OpenEMR'