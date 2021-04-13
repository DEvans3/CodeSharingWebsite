# CodeSharingWebsite Overview
A sample programming project for a code sharing website.

# Prerequisites for Application (If conditions are not already met)
  1. Download and install Eclipse
  2. Download and install MySQL
  3. Download and install Apache Tomcat (I used v8.5 but you can use other versions if you want to)

# Steps to Setup Database
  1. Create a MySQL connection of localhost:3000. Set the username to "root" and the password to "password".
  2. Run setup.sql query to create database and tables
  3. Run initial data.sql query to add data to tables

# Steps to run Application
  1. Add WebServer_Project folder to Eclipse workspace
  2. Add new Apache Tomcat sever to workspace
  3. Add jsp-api.jar, mysql-connector-java-(version number).jar (version number depends on what version of MySQL you downloaded), servlet-api.jar, and websocket-api.jar to Modulepath. Java Build Path can be found by right-clicking on the Project → Build Path → Configure Build Path. Under Libraries tab, click Add Jars or Add External JARs
  4. Now you can start the application by right-clicking on login.jsp → Run As → Run on Server. Select the Apache Tomcat server that you created.
