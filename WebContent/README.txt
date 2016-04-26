Compile java sources via ubuntu

1. direct to /var/lib/tomcat7/webapps/fabflix
2. compile files using the command:

sudo javac -classpath 
'/var/lib/tomcat7/webapps/fabflix/WEB-INF/lib/mysql-connector-java-5.1.38-bin.jar
:/var/lib/tomcat7/webapps/fabflix/WEB-INF/lib/javax.json-1.0.jar
:/var/lib/tomcat7/webapps/fabflix/WEB-INF/lib/servlet-api.jar
:/var/lib/tomcat7/webapps/fabflix/WEB-INF/lib/recaptcha4j-0.0.7.jar
:/var/lib/tomcat7/webapps/fabflix/WEB-INF/classes' fabflix/*.java


3. Reload the project in Tomcat Manager
4. Load the website by clicking on the website.
5. For XML PARSING (Go under the website and navigate to /parseXML.html page)
example: http://54.191.129.247:8080/project4/parseXML.html
and click on the Parse Button shown in the webpage. This will parse the files and store the files to the DB


In addition for the empty database, we included a STOREDPROCEDURE.sql called add_movie that we used to insert the
movies to the database. We also included this into the .war file.
