Requirements
------------

* Java 1.8
* Apache Axis2 (http://axis.apache.org/axis2/java/core/)

What to change
-------------

Substitute your actual username and API key in the FlightXMLTest 
class's main method.

Running the example
-------------------
* Assuming you have installed axis2 in the current directory. If not adjust the path for the command and included libs.
* You must also have your JAVA_HOME variable set.  See documentation for your OS on how best to do that.

```export CLASSPATH=$(echo . axis2-1.7.4/lib/*.jar | tr ' ' ':')

./axis2-1.7.4/bin/wsdl2java.sh -S . -uri https://flightxml.flightaware.com/soap/FlightXML3/wsdl

javac FlightXMLTest.java

java FlightXMLTest```