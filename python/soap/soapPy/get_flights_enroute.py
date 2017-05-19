#!/usr/bin/env python

from SOAPpy import Config, HTTPTransport, SOAPAddress, WSDL

username = 'YOUR_USERNAME'
apiKey = 'YOUR_API_KEY'
wsdlFile = 'https://flightxml.flightaware.com/soap/FlightXML3/wsdl'

# This is a custom HTTP transport that allows Basic Authentication.
class myHTTPTransport(HTTPTransport):
	username = None
	passwd = None

	@classmethod
	def setAuthentication(cls,u,p):
		cls.username = u
		cls.passwd = p

	def call(self, addr, data, namespace, soapaction=None, encoding=None,
		http_proxy=None, config=Config, timeout=None):

		if not isinstance(addr, SOAPAddress):
			addr=SOAPAddress(addr, config)

		if self.username != None:
			addr.user = self.username+":"+self.passwd

		return HTTPTransport.call(self, addr, data, namespace, soapaction, encoding, http_proxy, config)

# Make a FlightXML server request.
myHTTPTransport.setAuthentication(username, apiKey)
DF = WSDL.Proxy(wsdlFile, transport=myHTTPTransport)

enroute = DF.AirportBoards('KSMO',0,'','enroute',10,0)

flights = enroute['enroute']['flights']

print "Aircraft en route to KSMO:"
for flight in flights:
	print "{} ({}) \t{} ({})".format(flight['ident'], flight['aircrafttype'],
					flight['origin']['airport_name'], flight['origin']['code'])