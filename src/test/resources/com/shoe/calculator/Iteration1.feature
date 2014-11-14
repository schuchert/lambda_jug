Feature: Basic Math

  Scenario: Addition
    Given a user enters 30, 4
    When the user executes plus
    Then the result should be 34

  Scenario: Subtraction
    Given a user enters 30, 4
    When the user executes minus
    Then the result should be 26

  Scenario: Multiplication
    Given a user enters 4, 6
    When the user executes times
    Then the result should be 24

  Scenario: Division
    Given a user enters 8, 2
    When the user executes divide
    Then the result should be 4

  Scenario: Factorial
    Given a user enters 5
    When the user executes factorial
    Then the result should be 120

  Scenario: Subtraction with only one number entered
    Given a user enters 1
    When the user executes minus
    Then the result should be -1

  Scenario: Addition with only no number Entered
    Given a newly created calculator
    When the user executes plus
    Then the result should be 0

  Scenario: Dropping a value
    Given a user enters 42, 13
    When the user executes drop
    Then the result should be 42

  Scenario: Swapping
    Given a user enters 42, 13
    When the user executes swap
    Then the result should be 42
    And the next result should be 13

  Scenario: Executing bogus request
    When the user executes some_unknown_operator
    Then the error should include "Unknown Operator"

  Scenario: Squared
    Given a user enters 5
    When the user executes squared
    Then the result should be 25
