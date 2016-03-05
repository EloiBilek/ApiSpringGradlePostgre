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

This project was started with: New > Gradle > Project Gradle.

![init project](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SMP/init_project.png)

Using the plugin (Add in Eclipse Marketplace). 

![gradle plugin](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SMP/gradle_plugin.png)

To run the project in Tomcat server linked in Eclipse, set the Project Facets in: Project Properties > Project Facets.

![facets](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SMP/facets.png)

... And add external dependencies on: Project Properties > Deployment Assembly.

![facets](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SMP/properties_deployment.png)

Build in: ApiSpringGradlePostgre > Gradle > Refresh Gradle Project.
Or select task (assemble, build...) in Gradle Tasks perspective.

![gradle tasks](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SMP/gradle_tasks.png)

To test requests (post, get, put and delete), I use the following plugin for chrome:

![rest plugin](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SMP/chrome_rest_plugin.png)

Request example.

![request test](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SMP/resquest_test.png)

Response example.

![response test](https://github.com/EloiBilek/eloibilek.github.io/raw/master/SMP/response_test.png)








