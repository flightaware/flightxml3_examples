#!/usr/bin/env ruby

require 'rubygems'
require 'savon'

SOAP_URL = 'https://flightxml.flightaware.com/soap/FlightXML3/wsdl'
USERNAME = 'YOUR_USERNAME'
API_KEY = 'YOUR_API_KEY'

client = Savon.client do
	wsdl SOAP_URL
	basic_auth [USERNAME, API_KEY]
	convert_request_keys_to :none
end

message = {
	:airport_code => 'KJFK',
	:include_ex_data => 0,
	:type => 'enroute',
	:howMany => 10
}

response = client.call(:airport_boards, message: message)

enroute = response.body[:airport_boards_results][:airport_boards_result][:enroute]

puts "Aircraft enroute to KJFK:"

enroute[:flights].each do |flight|
	puts "#{flight[:ident]} (#{flight[:aircrafttype]})\t#{flight[:origin][:airport_name]} (#{flight[:origin][:code]})"
end