Feature: Access SgDigital website  website
  Use selenium java with cucumber-jvm and navigate to website

  Background: : Launch Browser -Browser is Launched and navigation to test application is verified.
    Given Browser is launched
    When I Navigate to SgDigital home page
    Then SgDigital page is displayed


  Scenario: TC1 -MenuItem_CareerVerify
    Given SgDigital page is displayed
    Then Total of 12 Menu items are displayed

  Scenario: TC2 -PageHome_TopMenuBarLayoutVerify
    Given SgDigital page is displayed
    Then Careers Menu Item is Enabled and Visible

  Scenario: TC3 - PageCareer_LayoutVerify
    Given Career Page Navigation is completed
    Then Total of 12 Menu items are displayed in predefined sequence
    And "Careers with SG Digital" Label is visible
    And "Current vacancies" Label is visible
    And Text Search Filter is enabled and editable
    And Search Button is enabled and displayed
    And Location Dropdown is displayed


  Scenario: TC4 - PageCareer_Location_Selection_Verification
    Given Career Page Navigation is completed
    When Select "<Location>" in the location drop down
    Then "Singapore" is displayed in Location dropdown
