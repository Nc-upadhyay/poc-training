## Topic
1. [API Gateway](#1-api-gateway-)
2. [Api Authentication](#2-api-authentication)
3. [Aws Lambda](#3-aws-lambda-)

## 1. API Gateway 
It serves as a centralized entry point for managing and routing requests from clients to the appropriate microservices or backend services within a system.

It works b/w client and micro-services.

1. Use case
   1. Authentication 
   2. Rate Limit
   3. Monitoring

[//]: # (<img src="src/main/resources/imges/api_gateway.png" alt="Alt text" width="500"/>)
Api Gateway Creation process ...
![Api Gateway a](src/main/resources/imges/api_gateway-1.png)

Set http methods
![Api Gateway a](src/main/resources/imges/api_gateway-2.png)

Output when hit the api gateway url
![Api Gateway a](src/main/resources/imges/api_gateway-3.png)

## 2. Api Authentication
API authentication is an important security process that authenticates the identity of users or applications prior to providing API access. It makes sure that only legitimate entities interact with an API, avoiding unauthorized access, data exposure, and API misuse.
Using api authentication you can secure you api. by this you can prevent from D-DOS attack.in this client set api key in header as x-api-key

### Usecase
1. Rate limiting
2. Identifying different client

![Api Authentication](src/main/resources/imges/api-authentication-1.png)

## 3. AWS Lambda 
AWS lambda are server-less compute functions are fully managed by the AWS where developers can run their code without worrying about servers. AWS lambda functions will allow you to run the code with out provisioning or managing servers.
### Features 
1. File Processing : Aws lambda can trigger using s3.
2. Provide high availability
3. Serverless execution
4. Pay-per-use pricing
5. Supports different programming languages
6. integrate with other aws services and SAAS application

Creating aws lambda
![Api Lambda](src/main/resources/imges/lambda-1.png)
Executing aws lambda
![Api Lambda](src/main/resources/imges/lambda-2.png)

##  4. Amazon Cognito
It is aws service that provide user authentication,authorization and provide management of web and mobile 
application. while users cognito pool is the user directory that handle user sign in and sign up process for 
users to access the application.

Cognito pool provide aws credential to grant your user access to other aws services.

#### Sign In process using cognito services
![Sign in process](src/main/resources/imges/signin.png)

#### Sign up process using cognito services
![Sign up process](src/main/resources/imges/signup.png)

#### Sign in successful
![Sign in process successful ](src/main/resources/imges/congnito_success.png)

#### User sign in/up data 
![Sing in/up data](src/main/resources/imges/cognito_pool_user_data.png)
