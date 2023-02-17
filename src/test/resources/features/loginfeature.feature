Feature: User login

Scenario Outline: Validate page title
Given user is on login page
When enter "<username>" and "<password>"
Then Click on Login
Then Verify the error message
Then Close Browser

Examples:
|username|password|
|singaraomaddineni|9493242484|