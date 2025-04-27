# BankingApplication-Microservices-
A banking application that consists of multiple microservices to handle different banking operations



Account Service - 
-1st and most important Microservice
-It has Create Account for customer,Deposit Money in own account, Withdraw money from own account,Get Account and get Account Balance services
-Applied AES Encryption technique to encrypt password of user


Transaction Service
- 2nd Microservice for Transfering Money from one account to another
-It has Transfer Money and Transaction History services

Notification Service
- Sends email notification once the transaction is successfull

Gateway Service
-Uses API Gateway to connect all the three above microservices and enables the enpoints to be triggered using the gateway port for all the services

1.Testing:- Written JUnit test cases for all the operations in specific services and it passed

2. Scalability:- Tested scalability using JMeter and Runner Collection in Postman works well

3. Observability:- Checked health and metrics using Springboot-actuator (trying to work on other external ways as well)

4.Security:- Provided security to the user data by using encryption

