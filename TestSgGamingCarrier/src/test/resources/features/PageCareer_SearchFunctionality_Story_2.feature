Feature: Verify Careers Page Search Functionality Layout

  Background: : Launch Browser -Browser is Launched and navigation to test application is verified.
    Given Browser is launched
    When I Navigate to SgDigital home page
    Then SgDigital page is displayed

  Scenario Outline:PageCareer_LayoutVerify
    Given Career Page Navigation is completed
    And Text Search Filter is enabled and editable
    And Search Button is enabled and displayed
    And Select "<Location>" in the location drop down
    And Input Test Data "<JobText>" in the search Edit box
    And Trigger Search by clicking Search or press enter key
    Then Verify the results for given "<JobText>" and Location "<Location>" and Keyword "<ValidKeyword>"
    Examples:
      | JobText  |  Location | ValidKeyword  |
      | Test  | Singapore   |  Y |
      | Specialist  | Singapore   | Y |
      | C++  | Bristol   | Y |
      | Business Analyst  | Singapore   | Y |
      | Business Analyst  | London   | Y |
      | T E S T  | Singapore   | N |
      | L2  | Singapore   | Y |
      | L2  | Bengaluru   | Y |
      | TEST###  | Singapore   | Y |