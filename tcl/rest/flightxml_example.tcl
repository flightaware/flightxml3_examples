#!/usr/bin/env tclsh

package require tls
package require rest
package require yajltcl

http::register https 443 ::tls::socket

set username "sampleUser"
set apiKey "abcdefghijklmnopqrstuvwxyz"
set baseUri "https://flightxml.flightaware.com/json/FlightXML3"

proc get_weather {airport} {
	global username apiKey baseUri
	if {[catch {set res [rest::simple "${baseUri}/WeatherConditions" [list airport_code $airport] \
			[list method get auth [list basic $username $apiKey]]]} err]} {
		puts "Error retrieving weather: $err"
		return
	}

	puts "Weather for $airport"
	set jsonkv [::yajl::json2dict $res]
   	puts [lindex [lindex [lindex $jsonkv 1] 3] 0]
}

proc get_enroute {airport} {
	global username apiKey baseUri
	if {[catch {set res [rest::simple "${baseUri}/AirportBoards" [list airport_code $airport include_ex_data 0 type enroute howMany 10] \
			[list method get auth [list basic $username $apiKey]]]} err]} {
		puts "Error retrieving flights: $err"
		return
	}

	puts "Aircraft enroute to $airport:"
	set jsonkv [::yajl::json2dict $res]
	array set boardData [lindex $jsonkv 1]
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

get_weather "KHOU"
get_enroute "KHOU"
