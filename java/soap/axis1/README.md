Requirements
------------

* Java 1.8
* Apache Axis (http://axis.apache.org/axis/)

What to change
-------------

Substitute your actual username and API key in the FlightXMLTest 
class's main method.

Running the example
-------------------
* Assuming you have installed axis in the current directory. If not adjust the path for the command and included libs.

```export CLASSPATH=$(echo . axis1-1_4/lib/*.jar | tr ' ' ':')

curl -o wsdl3.xml https://flightxml.flightaware.com/soap/FlightXML3/wsdl

java org.apache.axis.wsdl.WSDL2Java wsdl3.xml

javac FlightXMLTest.java

java FlightXMLTest```