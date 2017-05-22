#!/usr/bin/env python

from requests import Session
from requests.auth import HTTPBasicAuth
from zeep import Client
from zeep.transports import Transport

username = 'YOUR_USERNAME'
apiKey = 'YOUR_API_KEY'
wsdlFile = 'https://flightxml.flightaware.com/soap/FlightXML3/wsdl'

session = Session()
session.auth = HTTPBasicAuth(username, apiKey)
client = Client(wsdlFile, transport=Transport(session=session))
boards = client.service.AirportBoards('KHOU',0,'','enroute',10,0)

flights = boards['enroute']['flights']
print "Flight enroute to KHOU:"
for flight in flights:
	print "{} ({}) \t{} ({})".format(flight['ident'], flight['aircrafttype'],
					flight['origin']['airport_name'], flight['origin']['code'])