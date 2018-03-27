# Company Management System

A simple app to manage Employees. Stores employees in the database. Provides tools to record hours worked a day and advance payments. 
The app is nowhere to be treated as a serious project as its a part of my learning process. :)

## How to run

git clone https://github.com/Maciass92/company-management.git

cd company-management

./mvnw spring-boot:run

## Tools used

* [Spring 5.0](https://spring.io/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Hibernate](http://hibernate.org/) - ORM tool
* [h2 database](http://www.h2database.com/html/main.html) - in-memory database
* [Thymeleaf](https://www.thymeleaf.org/) - Template engine
* [Project Lombok](https://projectlombok.org/) - Boilerplate code generator


## What's missing

* forms validation (to be done)
* proper frontend as it was purely intended to be a backend training
* to-do-list module (tbd, most likely a separate project)
* orders management module (tbd)

## Known issues
* Not an actual issue, but displaying date & time is done in a pitiful way. Will do via JS.
* Useless slider at Employee display screen
* It is possible to add Workday with the same date twice

## Authors

* **Maciej Komorowski**

