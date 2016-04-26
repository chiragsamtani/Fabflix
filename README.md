# Fabflix
**Compile java sources via ubuntu**
- Clone repo and export to war file by using the command:
  `jar cvf ..\mywebapp.war *.*`

- Compile files using the command:


`sudo javac -classpath 
'/var/lib/tomcat7/webapps/fabflix/WEB-INF/lib/mysql-connector-java-5.1.38-bin.jar
:/var/lib/tomcat7/webapps/fabflix/WEB-INF/lib/javax.json-1.0.jar
:/var/lib/tomcat7/webapps/fabflix/WEB-INF/lib/servlet-api.jar
:/var/lib/tomcat7/webapps/fabflix/WEB-INF/lib/recaptcha4j-0.0.7.jar
:/var/lib/tomcat7/webapps/fabflix/WEB-INF/classes' fabflix/*.java`


3. Reload the project in Apache2 or Apache Tomcat WebServer
4. Load the service by selecting the deployed .war file generated via port 8080
