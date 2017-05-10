/*
 * This required node-rest-client.
 * To install type 'npm install'
 * Tested with node.js v4.8.3
 */
var Client = require('node-rest-client').Client;

var username = 'YOUR_USERNAME';
var apiKey = 'YOUR_API_KEY';
var fxmlUrl = 'https://flightxml.flightaware.com/json/FlightXML3/'

var client_options = {
	user: username,
	password: apiKey
};
var client = new Client(client_options);

client.registerMethod('findFlights', fxmlUrl + 'FindFlight', 'GET');
client.registerMethod('weatherConditions', fxmlUrl + 'WeatherConditions', 'GET');

var findFlightArgs = {
	parameters: {
		origin: 'KIAH',
		destination: 'KJFK',
		type: 'nonstop'
	}
};

client.methods.findFlights(findFlightArgs, function (data, response) {
	console.log('Number of Flights: %j', data.FindFlightResult.num_flights);
	console.log('First flight found: %j', data.FindFlightResult.flights[0]);
});

var weatherConditionsArgs = {
	parameters: {
		airport_code: 'KHOU'
	}
};

client.methods.weatherConditions(weatherConditionsArgs, function (data, response) {
	console.log('Current conditions at Hobby Airport: %j', data.WeatherConditionsResult.conditions[0]);
})