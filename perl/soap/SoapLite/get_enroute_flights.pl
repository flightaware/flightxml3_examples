#!/usr/bin/env perl

use strict;
use SOAP::Lite;
use Data::Dumper;

my $username = "YOUR_USERNAME";
my $apiKey = "YOUR_API_KEY";

my $baseUri = "https://flightxml.flightaware.com/soap/FlightXML3";

sub SOAP::Transport::HTTP::Client::get_basic_credentials {
    return $username => $apiKey;
}

my $soap = SOAP::Lite->default_ns($baseUri)->proxy("$baseUri/op")->autotype(0);

my $airportBoards = $soap->call("AirportBoards",
    SOAP::Data->name("airport_code" => 'KSMO'),
    SOAP::Data->name("howMany" => 10),
    SOAP::Data->name("filter" => ''),
    SOAP::Data->name("type" => 'enroute'),
    SOAP::Data->name("offset" => 0)
);
die if !defined($airportBoards) || $airportBoards->fault;

print "Aircraft en route to KSMO:\n";
    
my $flights = $airportBoards->result->{'enroute'}->{'flights'};
    
 foreach my $flight (@$flights) {
    print $flight->{'ident'} . " (" . $flight->{'aircrafttype'} . ") \t" .
        $flight->{'origin'}->{'airport_name'} . " (" . $flight->{'origin'}->{'code'} . ")\n";
}