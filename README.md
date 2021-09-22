# About Airport API:
This API has below mentioned two get operations :

1) GET v1/airports/runways?countryName=India&countryCode=IN

This operation will retrieve all the runways for every airport (as one airport can have more than one runway) for the given country code or country name. We need to provide atleast one of the query param, either countryCode or countryName.

Output response will have countryCode, List of Airports with Airport Id and all the runways present in that airport.

2) GET v1/airports/reports?recordSize=20

This operation will retrieve number of airports for all the country in descending order.
Here i am taking record size as query param (modified it a bit than the asked question in assignment), which will decide how many records will be displayed in output.

For example if we give 20 in record size then it will output first 20 countries with highest number of Airports.
If record size is not given then by default it shows first 10 countries with highest number of Airports.


For operation and monitoring purpose. I have used spring boot actuator for health check. Currenlty i have enable only /health but other endpoints can also be enabled if required.

GET /actuator/health


# Getting Started

(1) You need to have jdk 8 or higher to build this project.
(2a) Clone the repo to your favorite IDE(In case of Eclipse, after cloning first configure it as maven project).
(2b) Build using Maven (mvn clean install).
(2c) Run as spring boot application (mvn spring-boot:run).
(2d) If for some reason cloning does not work from git repo then directly download the zip project, extract it and then import it in your IDE. Repeat the above steps again.


# About Implementation
As in assignment it was mentioned to build production ready application, I added all the CSV data in resource file, one time load of all the data to model classes and then using it for further processing.

Another way of doing it will be uploading the csv data in MySql or H2 In-Memory database first and then creating the entity and Repo using JPA-Hibernate. I have not choosen to do this as H2 In-Memory Database is only suitable for testing or lower environment and i don't have any info about production SQL database url or connection.

I have decided this approach so that the API is production ready and can be deployed anywhere without any changes.
Executable Jar created here can be directly run on your local machine(java -jar Example.jar) or it can be deployed to Azure App Service using zip deploy method or any other cloud platform easily. 

If reviewer wants, i can also do this assignment by loading the data into some local database and then using JPA-Hibernate for further processing.

# Focus
Other than implementing business logic, i always focus on clean code, Java docs, logs, meaningful JUnit test cases.


