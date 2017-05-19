#!//usr/bin/env python

import requests

username = "YOUR_USERNAME"
apiKey = "YOUR_API_KEY"
fxmlUrl = "https://flightxml.flightaware.com/json/FlightXML3/"

payload = {'airport_code':'KSFO', 'type':'enroute', 'howMany':'10'}
response = requests.get(fxmlUrl + "AirportBoards", params=payload, auth=(username, apiKey))

if response.status_code == 200:
	print "Aircraft enroute to KSFO:"
	decodedResponse = response.json()
	for flight in decodedResponse['AirportBoardsResult']['enroute']['flights']:
		print "{} ({})\t{} ({})".format(flight['ident'], flight['aircrafttype'], flight['origin']['airport_name'], flight['origin']['code'])
else:
	print "There was an error retrieving the data from the server."
