#!/usr/bin/env tclsh

package require WS::Client 2.4
package require base64

set username "sampleUser"
set apiKey "abcdefghijklmnopqrstuvwxyz"
set baseUri "https://flightxml.flightaware.com/soap/FlightXML3"

set wsdl [::WS::Client::GetAndParseWsdl "$baseUri/wsdl"]
set apiname [::WS::Client::LoadParsedWsdl $wsdl]
set authheader [list Authorization "Basic [::base64::encode $username:$apiKey]"]

proc get_weather {airport} {
	global apiname authheader
	if {[catch {array set result [::WS::Client::DoCall $apiname WeatherConditions [list airport_code $airport] $authheader]} err]} {
		puts "Error retrieving the weather: $err"
		return
	}

	puts "Weather for $airport"
	puts [lindex [lindex $result(WeatherConditionsResult) 3] 0]
}

proc get_enroute {airport} {
	global apiname authheader
	if {[catch {array set result [::WS::Client::DoCall $apiname AirportBoards [list airport_code $airport include_ex_data 0 type enroute howMany 10] $authheader]} err]} {
		puts "Error retrieving flights: $err"
		return
	}

	puts "Aircraft enroute to $airport:"
	array set boardData $result(AirportBoardsResult)
	array set enroute $boardData(enroute)
	foreach flight $enroute(flights) {
		array unset flightArr
		array set flightArr $flight
		array unset origin
		array set origin $flightArr(origin)
		foreach key {ident aircrafttype} {
			if {![info exists flightArr($key)]} {
				set flightArr($key) {}
			}
		}
		puts "$flightArr(ident) ($flightArr(aircrafttype))\t$origin(airport_name) ($origin(code))"
	}
}

get_weather "KSMO"
get_enroute "KSMO"