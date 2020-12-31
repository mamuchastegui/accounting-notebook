# Agile Engine Accounting Notebook

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.agileengine.testtask.TesttaskApplication` class from your IDE.


## Postman

##### Get Account Balance
```
curl --location --request GET 'localhost:8080/accounts/1'
```

##### Make a Debit/Credit
```
curl --location --request POST 'localhost:8080/transactions' \
--header 'Content-Type: application/json' \
--data-raw '{
    "type": "debit",
    "amount": 25.5
}'
```

##### Get Transaction List
```
curl --location --request GET 'localhost:8080/transactions'
```

##### Get Transaction By Id
```
curl --location --request GET 'localhost:8080/transactions/09c8f205-54ad-4f31-ac1d-b81f8e1854b5'
```
