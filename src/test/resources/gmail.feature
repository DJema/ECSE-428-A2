Feature: gmail

  Scenario: Sending an email with an attachment to a recipient
    Given I am logged in to gmail on my homepage
    When I compose an email
    And I add a recipient to my message
    And I add a subject to my message
    And I add an attachment to my message
    And I send the email
    Then the recipient receives the message with the right subject and attachment
    
  Scenario: Sending an email with a different attachment and subject
    Given I am logged in to gmail on my homepage
    When I compose an email
    And I add a different recipient to my message
    And I add a different subject to my message
    And I add a different attachment to my message
    And I send the email
    Then the recipient receives the message with the right subject and attachment
    
  Scenario: Sending an email with no subject to a recipient
    Given I am logged in on the gmail homepage
    When I compose an email
    And add a recipient to my message
    And add an alternate attachment to my message
    And I send the email
    And I accept the prompt window
    Then the recipient receives the message with no subject and an attachment
    
  Scenario: Sending an email without specifying a recipient
    Given I am logged in on the gmail homepage
    When I compose an email
    And I add a subject to my message
    And I add an attachment to my message
    And I send the email
    Then an error message is displayed specifying to add a recipient
    
  Scenario: Sending an email with an attachment that is too large
    Given I am logged in on the gmail homepage
    When I compose an email
    And I add a subject to my message
    And I add a large attachment to my message
    And I send the email
    Then an error message is displayed specifying the attachment is too large


    
    
   



