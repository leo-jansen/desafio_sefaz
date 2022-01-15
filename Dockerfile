#Build
FROM maven:3.8.4-openjdk-11 as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#Tomcat
FROM tomcat:9-jdk11
COPY --from=build /home/app/target/sefaz.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD [ "catalina.sh", "run" ]