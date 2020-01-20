Feature: SpringBootAdminControllerGetPing 
#This is how background can be used to eliminate duplicate steps 
Background: 
User navigates to Spring Boot Rest Project Admin API URL Given 
I am on Spring Boot Admin Endpoint URL 

#Scenario with AND 
Scenario: 
When I fire RestTemplate on "/admin/ping"
#And I point to "/admin/ping" 
Then Response should be 200 

#Scenario with BUT 
Scenario: 
When I fire RestTemplate on "http://localhost:8900/admin/ping"
#And I point to "/admin/ping" 
Then Response should be 200 
But Response should not have any other entity