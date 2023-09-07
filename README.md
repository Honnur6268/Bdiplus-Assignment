# Bdiplus-Assignment

Task Application

Steps to Run Project:
For Backend
  1. Download the repository from the Github - https://github.com/Honnur6268/Bdiplus-Assignment.git
  2. Open the Spring Boot project in your Eclipse IDE
  3. Create Database in MySQL Workbench or Command Line Client
  4. Configure and enter your username, password and database name in application.yml file to connect to Database.
  5. Run the application as Spring boot app
  6. Open url in browser -
     http://localhost:8080/swagger-ui/index.html#/
     Opens Swagger UI where you can see Rest Api endpoints and test the apis.

     Rest Endpoints:
       a. GET -  To view all tasks   - http://localhost:8080/api/task/tasks
       b. POST - To save Task to db - http://localhost:8080/api/task/save
       c. PUT - To update task  - http://localhost:8080/api/task/update
       d. GET - To get the task by ID - http://localhost:8080/api/task/{id}
       e. DELETE - To delete the task by ID - http://localhost:8080/api/task/{id}

     Test the api using Postman or Swagger Ui

For Frontend
  Download the repository from the Github
  after running the Spring Boot Application, Open the index.html file in browser
