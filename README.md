# Inventory-ws
Implementation for Inventory API.

## Scope
1. Provide an implementation of Inventory API
2. Spring MVC REST controller with mock mvc test
3. JPA mapping using H2 embedded database
4. Maven build
5. unit tests

## Assumptions (made based on general understanding)
1. DB design is similar to API Model design
2. There is a One-to-Many relationship between Manufacturer and Inventory because one Manufacturer can manufacture
   many products
3. While creating the Inventory in the DB following has been implemented:
    a. If a Manufacturer with matching details does not exist in the system, it creates one. 
    b. If a Manufacturer exists with matching details then uses the existing Manufacturer entity.
    c. Otherwise, if a Manufacturer with same name exits, but other details do not match then it throws an error
       alerting the consumer that the supplied details need to be rectified.
       
4. Basic test for Controller is enough becuase it is sufficient to show that the candidate knows how to write these tests
5. Database is populated with some initial sample data so that GET requests can be executed

## Building and Running the project
### Pre-requisite
    1. Java 1.8 or above
    2. Maven installed
    
### Steps    
    1. Build using Maven: 
        $ mvn clean install
    
    2. Run the application using Maven:
        $ mvn spring-boot:run -Dspring-boot.run.arguments=--inventory.ws.user.creds.password=passwordOfYourChoice,--spring.profiles.active=dev
    
    3. Run Using an IDE: Supply two environment properties: 
        a. inventory.ws.user.creds.password=<password-of-your-choice>
        b. spring.profiles.active=dev
    