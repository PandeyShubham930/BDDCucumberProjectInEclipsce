Feature: Login

#common steps in background
Background:
Given user launch the chrome browser 

@sanity
Scenario: Successful login with login credentials

And user opens the url "https://admin-demo.nopcommerce.com/login"
And user enters the email as "admin@yourstore.com" 
And user enters the password as "admin"
When user cliks on login 
Then page title should be "Dashboard / nopCommerce administration"
When user clicks on logout
Then page title should be "Your store. Login"
And user close the browser

@datadriven
Scenario Outline: Successful login with multiple sets of data

And user opens the url "<URL>"
And user enters the email as "<username>" 
And user enters the password as "<pwd>"
When user cliks on login 
Then page title should be "<Title1>"
When user clicks on logout
Then page title should be "<Title2>"
And user close the browser
#data driven testing
Examples:
|             URL                        | username          | pwd   | Title1                               |    Title2       |
|https://admin-demo.nopcommerce.com/login|admin@yourstore.com| admin |Dashboard / nopCommerce administration|Your store. Login|
|https://admin-demo.nopcommerce.com/login|admin@yourstore.com| admin |Dashboard / nopCommerce administration|Your store. Login|
