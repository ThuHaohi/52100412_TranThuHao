# Spring Ecommerce

[![](https://img.shields.io/badge/framework-springboot-success.svg?logo=springboot)](https://spring.io/projects/spring-boot)
[![](https://img.shields.io/badge/database-mysql-9cf.svg?logo=mysql)](https://www.mysql.com/)
[![](https://img.shields.io/badge/orm-hibernate-yellowgreen.svg?logo=hibernate)](https://hibernate.org/)
[![](https://img.shields.io/badge/server-tomcat-yellow.svg?logo=apachetomcat)](https://tomcat.apache.org/)
[![](https://img.shields.io/badge/management-maven-red.svg?logo=apachemaven)](https://docs.spring.io/spring-security/reference/)
[![](https://img.shields.io/badge/documentation-swagger-brightgreen.svg?logo=swagger)](https://swagger.io/)
[![](https://img.shields.io/badge/version--control-github-critical.svg?logo=git)](https://git-scm.com/)
[![](https://img.shields.io/badge/auth-spring%20security-success.svg?logo=springsecurity)](https://docs.spring.io/spring-security/reference/)
[![](https://img.shields.io/badge/integrity-JWT-inactive.svg?logo=jsonwebtokens)](https://jwt.io/)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

# Entity-relationship diagram
- File erd.png

# A brief explanation for the code structure.
- Existing folders and files created by Spring Initializer
- Inside src/main/java:
  + Package entity contains classes and fields represented as tables and columns, relationships
  + Package repository contains interfaces those extend JpaRepository to handle data layer
  + Package service contains service classes to handle business logic
  + Package controller contains RestController classes to map url and http method with specific function
  + Package security contains configuration to handle security concerns of project
  + Package config contains configuration file for AWS, OpanAPI (Swagger) and Cors
  + Package enums contains enums of project
  + Package utilities contains common functions which can be reused throughout the whole projects
- Inside src/main/resources:
  + file schema.sql define sql script to drop and recreate tables
  + file data.sql define sql script to init data
  + file application.properties define global configuration for project such as database connection, startup port, aws credentials,...

# All required steps in order to get the application run on a local computer.
- Required dependencies: 
  + node 14
  ```shell
    node --version
    ```
  + JDK 11 
  ```shell
    java --version
    ```
- Steps:
  - `Database`
    + Run your mysql server locally at port 3306
  - `Backend`
    + Use IntelliJ or Eclipse to open folder be
    + Run As Spring Boot App
    + Go to [swagger ui](http://localhost:8080/swagger-ui/index.html)
    + Check database to verify schema spring_ecommerce, tables and init data
  - `Frontend`
    + Use VScode to open folder fe
    + Open integrated terminal (terminal should stand at folder fe)
    ```shell
    npm install
    ```
    ```shell
    npm start
    ```
    + Go to [web](http://localhost:3002)

# Additional requirement
- Already integrated:
  + Spring Security (applying JWT)
  + Open API (Swagger UI)
  + AWS S3 (Storing image)

# Full CURL commands (include full request endpoints, HTTP Headers and request payload if any)
**All are displayed in [swagger ui](http://localhost:8080/swagger-ui/index.html)**


- Brand (CRUDL)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249276329_Screen%20Shot%202023-11-29%20at%2015.54.54.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249317041_Screen%20Shot%202023-11-29%20at%2015.55.36.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249385144_Screen%20Shot%202023-11-29%20at%2015.56.01.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249405690_Screen%20Shot%202023-11-29%20at%2015.56.22.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249435380_Screen%20Shot%202023-11-29%20at%2015.56.36.png)


- Vehicle (CRUDL)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249461458_Screen%20Shot%202023-11-29%20at%2015.57.44.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249502327_Screen%20Shot%202023-11-29%20at%2015.58.10.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249526516_Screen%20Shot%202023-11-29%20at%2015.58.47.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249548531_Screen%20Shot%202023-11-29%20at%2015.59.14.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249574354_Screen%20Shot%202023-11-29%20at%2015.59.28.png)


- Order (Add to order, remove or decrease quantity, checkout)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249607311_Screen%20Shot%202023-11-29%20at%2016.02.50.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249636383_Screen%20Shot%202023-11-29%20at%2016.03.42.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249674619_Screen%20Shot%202023-11-29%20at%2016.04.51.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249718379_Screen%20Shot%202023-11-29%20at%2016.05.42.png)


- Customer (signup, login)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249772391_Screen%20Shot%202023-11-29%20at%2016.22.29.png)
![](https://uni-cs-mobile.s3.ap-southeast-1.amazonaws.com/temp/1701249794479_Screen%20Shot%202023-11-29%20at%2016.22.42.png)
