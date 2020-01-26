# Spring Boot, MySQL, JPA, Hibernate Rest API Tutorial

Build Restful CRUD API for a simple Note-Taking application using Spring Boot, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x (Optional)

## Steps to Setup

**Build and run the app using maven**

```bash
mvn package
java -jar target/easy-notes-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

## Explore Rest APIs

The app will start running at <http://localhost:8080>

You can test them using the embedded Swagger UI.

## Steps to Deploy - Openshift

Service Accounts: https://docs.openshift.com/container-platform/4.1/authentication/using-service-accounts-in-applications.html

**1. Create a new project**

```bash
oc new-project spring-boot-easynotes
```

Create secret
```bash
oc create secret generic git     --from-literal=username=<user> --from-literal=password=<pass> --type=kubernetes.io/basic-auth
```

Create app
```bash
oc new-app openshift/java:8~https://github.com/mvsgodinho/spring-boot-easynotes.git --source-secret='git' -o yaml
```

Source: https://docs.openshift.com/online/using_images/s2i_images/java.html#s2i-images-java-deploy-applications-from-binary

## Using a Mysql database

**1. Create Mysql database**
```bash
create database notes_app
```

**2. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ uncomment Mysql properties and comment h2 properties

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

## Learn more

Forked from https://github.com/callicoder/spring-boot-mysql-rest-api-tutorial.git and improved by Marcos Godinho.

