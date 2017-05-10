Requirements
------------

* Java SDK 1.8
* Maven

This example uses maven for dependency management and build execution.  For ease of use
you may want to ensure that 'mvn' is available on your PATH.  You can also import the project
into your IDE of choice to try it out.

What to change
-------------

Substitute your actual username and API key in application.properties.  You can also expand the
airport boards controller to accept more parameters and pass those along to the FlightXML call.

Running the example
-------------------
If you're running this in an IDE then just run the Flightxml3WithSpringApplication class as a
Java application.

Alternatively you can envoke the build using maven:  
mvn spring-boot:run

or build the jar file and run it with:  
mvn clean package  
java -jar target/