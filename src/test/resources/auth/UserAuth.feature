Feature: User auth
  User should be able to auth using valid login and password
  or if data is invalid access should be denied

  Scenario Outline: Testing auth with valid login and password
    Given I am on the login page
    When I enter login as "<email>" and password as "<password>"
    Then I successfully logged in with header "<email>" and "<encPassword>"

    Examples:
      | email            | password | encPassword                                                  |
      | sobaka@gmail.com | 1234     | $2a$10$QI4fQZ3CzXQ4fvM1eJ671ee3vjhhlmYVO8jfG/bOpj1HC3LgFfNHW |

  Scenario Outline: Testing auth with invalid login and password
    When I enter incorrect login as "<email>" and password as "<password>"
    Then Authentication failed

    Examples:
      | email            | password |
      | sobaka@gmail.com | 12345    |
