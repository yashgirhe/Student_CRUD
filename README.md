
# Spring Boot CRUD Application

This is a minimal spring boot application for performing create, read, update and delete operations on student entity.


## Requirements

[JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

[Intellij](https://www.jetbrains.com/idea/download/?section=windows) / [Eclipse](https://www.eclipse.org/downloads/) / [VS Code](https://code.visualstudio.com/download)

[MySQL](https://www.mysql.com/downloads/)


## Run Locally

• Clone this repository and open the directory in an of the IDEs

• Make sure you are using JDK 17

• You need to create a database (student_db) in your local machine

• In application.properties, make sure you set your local db credentials-

spring.datasource.username=<mysql username>
spring.datasource.password=<mysql password>

• Now you can simply java run the application and use the below link to access the swagger documentation -
http://localhost:8080/swagger-ui/index.html#/


## Demo to use APIs

We have total 6 APIs. Here is how we can test them all -

1.POST - We will use this API to add a student object into database. Our student entity takes id, name, address and subject properties. Below is the sample format -

{
  
  "id": 0,
  
  "name": "string",
  
  "address": "string",
  
  "subject": "string"

}

2.GET by id - This API searches the student object by id. If it is present in the database it will return the object. We need to pass the "id" in order to execute the API.

3.GET - This API returns all the students present inside the database. We does not need to pass any payload to execute this API.

4.PUT - We can execute this API to update the existing object in database. We need to pass the id and updated object for this operation.

5.Delete by id - We can delete any object we want by passing id to this particular API.

6.Delete - We can delete all the records from the table by hitting this API.