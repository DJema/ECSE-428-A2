Feature: gmail

  Scenario: Send an email with an attachment to a recipient
    Given I am logged in to gmail on my homepage
    When I click on Compose
    And I add a recipient to my message
    And I add a subject to my message
    And I add an attachment to my message
    And I click Send
    Then the recipient receives the message with the right subject and attachment
    
  Scenario: Send email on google chrome with different attachment and subject
    Given I am logged in to gmail on my homepage
    When I click on Compose
    And I add a different recipient to my message
    And I add a different subject to my message
    And I add a different attachment to my message
    And I click Send
    Then the recipient receives the message with the right subject and attachment
    
  Scenario: Send an email with no subject to a recipient
    Given I am logged in on the gmail homepage
    When I click on Compose
    And add a recipient to my message
    And add an alternate attachment to my message
    And I click Send
    And I accept the prompt window
    Then the recipient receives the message with no subject and an attachment
    
  Scenario: Send an email without specifying a recipient
    Given I am logged in on the gmail homepage
    When I click on Compose
    And I add a subject to my message
    And I add an attachment to my message
    And I click Send
    Then an error message is displayed specifying to add a recipient
    
  Scenario: Send an email with an attachment that is too large
    Given I am logged in on the gmail homepage
    When I click on Compose
    And I add a subject to my message
    And I add a large attachment to my message
    And I click Send
    Then an error message is displayed specifying the attachment is too large


    
    
   



