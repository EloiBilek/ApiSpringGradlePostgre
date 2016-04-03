---
Title: ApiSpringGradlePostgre
Description: API example using Spring, Gradle and PostgreSQL
Author: Eloi Bilek
Tags: gradle, restfull, api, java
Created: 2016 Mar 04

---

# ApiSpringGradlePostgre
==============================

It 's a very simple example of an RestFull API, CRUD of User.
Using:
* Java 8
* Gradle 2.11
* Spring 4
* Hibernate 5
* DataSources/Resource Pools C3P0 0.9.1.2
* PostgreSQL 9.4.1208.jre7
* Tomcat 8
* Eclipse Java EE IDE - Version: Mars.1 Release (4.5.1)

## Note 1!
This project was started with: New > Gradle > Project Gradle.

![init project](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SGP/init_project.png)

Using the plugin (Add in Eclipse Marketplace). 

![gradle plugin](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SGP/gradle_plugin.png)

To run the project in Tomcat server linked in Eclipse, set the Project Facets in: Project Properties > Project Facets.

![facets](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SGP/facets.png)

... And add external dependencies on: Project Properties > Deployment Assembly.

![facets](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SGP/properties_deployment.png)

## Note 2!
This Project have a DataSource control to the pool of connections (C3P0) in:
/ApiSpringGradlePostgre/src/main/java/com/apispringgradlepostgre/config/PersistenceConfig.java

Before starting project, set application.properties with the url of your database, username and password. If the database does not exist, set the hibernate.hbm2ddl.auto attribute to create it.

After importing the project to the Eclipse workspace, run build in: ApiSpringGradlePostgre > Gradle > Refresh Gradle Project.
Or select task (assemble, build...) in Gradle Tasks perspective.

![gradle tasks](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SGP/gradle_tasks.png)

In Tomcat server, add the project, click Publish, make sure synchronized.
Start with Play or Debug.

To test requests (post, get, put and delete), I use the following plugin for chrome:

![rest plugin](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SGP/chrome_rest_plugin.png)

Request example.

![request test](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SGP/request_test.png)

Response example.

![response test](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SGP/response_test.png)








