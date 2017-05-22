#!/usr/bin/env ruby

require 'rubygems'
require 'json'
require 'rest-client'
require 'base64'


username = 'YOUR_USERNAME'
apiKey = 'YOUR_API_KEY'

authHeader = Base64.strict_encode64("#{username}:#{apiKey}")
fxmlUrl = 'https://flightxml.flightaware.com/json/FlightXML3'

params = {
	:airport_code => 'KLAX',
	:include_ex_data => 0,
	:type => 'enroute',
	:howMany => 10
}

response = RestClient.get "#{fxmlUrl}/AirportBoards", {params: params, Authorization: "Basic #{authHeader}"}

if response.code == 200
	parsed = JSON.parse(response.body)

	puts "Flights enroute to KLAX: "
	flights = parsed["AirportBoardsResult"]["enroute"]["flights"]
	flights.each do |flight|
		puts "#{flight['ident']} (#{flight['aircrafttype']})\t#{flight['origin']['airport_name']} (#{flight['origin']['code']})"
	end
else
	puts "Error retrieving data from the server"
end