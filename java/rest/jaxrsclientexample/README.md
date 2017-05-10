Requirements
------------

* Java SDK 1.7
* Maven

This example uses maven for dependency management and build execution.  For ease of use
you may want to ensure that 'mvn' is available on your PATH.  You can also import the project
into your IDE of choice to try it out.

What to change
-------------

Substitute your actual username and API key in the main class, com.flightaware.flightxml.examples.JaxRsExample.

Running the example
-------------------
If you're running this in an IDE then just run the JaxRsExample class as a
Java application after importing the maven dependencies.

Alternatively you can build the jar and run the program:  
mvn clean package
java -jar target/jaxrs-client-example-1.0.0-jar-with-dependencies.jar