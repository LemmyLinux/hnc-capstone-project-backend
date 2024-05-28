# hnc-capstone-project-backend
My back-end Capstone project for Carlos III Web Application Development HNC.

## Installation

The recomended software version for this project is:

`java JDK 21`

`IntelliJ IDEA`

`MySQL 8.0.36`

To install the project just clone the repository and click on refresh project in the 
Gradle section of the IDE.

Before the first execution the script `createUser.sql` located in `[PROJECT_ROOT]/src/main/resources/sql` must be executed once.

Please note the file `application.properties` located in `[PROJECT_ROOT]/src/main/resources` 
establish the default uri and port for the database. 
To allow the project to connect with a database located in a different host/port 
those settings must be changed.


## Execution

To execute the project just open the `PfcBackendApplication.java` class and click on IDE run button.
