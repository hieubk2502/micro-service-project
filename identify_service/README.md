
- Pull image
image docker: docker pull mysql:8.0.37-debian 

- Run image
`docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.37`
- Create Database identify
`CREATE DATABASE IF NOT EXIST identify;`
- RUN 
`Run app mvn spring-boot:run -Dspring.profiles.active=dev`

- Build jar 
`mvn package mvn package -DskipTests -P dev`

- Run app 
`java -jar *.jar`

- docker 
`docker exec -it [Container] ` `sh cat /etc/os-release`

- Build image:
`docker build -t identity-service:0.0.1 . `

- Run container: 
`docker run -d identity-service:0.0.1`

- RUN Container with config properties 
`docker run --name identity-service -p 8080:8080 -e DBMS_CONNECTION= jdbc:mysql://${DBMS_IP_CONNECT:localhost}:3306/identify identify.dev-0.0.1-SNAPSHOT`

or

- create network 
`docker network create my-network` 
- mount when run image 
`docker run --network my-network --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.37-debian`

`docker run --name identity-service --network my-network -p 8080:8080 -e DBMS_CONNECTION= jdbc:mysql://${DBMS_IP_CONNECT:localhost}:3306/identify identify.dev-0.0.1-SNAPSHOT`

- push image 
`docker image push {name_dockerhub}/identify`
