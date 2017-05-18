#!/usr/bin/env perl

use strict;
use XML::Compile::SOAP11;
use XML::Compile::WSDL11;
use XML::Compile::Transport::SOAPHTTP;

use MIME::Base64 'encode_base64';
use DateTime;

# Replace the username / apiKey fields with your values
my $username = "YOUR_USERNAME";
my $apiKey = "YOUR_API_KEY";
my $WSDL = 'https://flightxml.flightaware.com/soap/FlightXML3/wsdl';

sub basic_auth {
	my ($request, $trace) = @_;

	# Encode userid and password
	my $authorization = 'Basic '. encode_base64 "$username:$apiKey";

	# Modify http header to include basic authentication
	$request->header(Authorization => $authorization);

	my $ua = $trace->{user_agent};
	$ua->request($request);
}

# Import the WSDL & define the service
my $ua = LWP::UserAgent->new(ssl_opts => { verify_hostname => 0 });
$ua->default_header('Authorization' => 'Basic ' . encode_base64 "$username:$apiKey");
my $wsdlResponse = $ua->get($WSDL)->{_content};
my $soapSrv = XML::Compile::WSDL11->new($wsdlResponse);
$soapSrv->importDefinitions('http://www.w3.org/2001/XMLSchema');

# Compile the FlightInfoStatus request call
my $flightInfoStatusCall = $soapSrv->compileClient(
	operation => "FlightInfoStatus",
	transport_hook => \&basic_auth
);

# Query for a faFlightID from FlightInfoStatus
my %request = (
	ident => 'SWA35',
	howMany => 15,
	offset => 0
);

my ($response, $trace) = $flightInfoStatusCall->(%request);

my @flights = @{$response->{'parameters'}->{'FlightInfoStatusResult'}->{'flights'}};
my $faFlightId;
foreach my $flight (@flights) {
	if (length $flight->{'actual_departure_time'} && $flight->{'actual_departure_time'}->{'epoch'} > 0) {
		$faFlightId = $flight->{'faFlightID'};
		last;
	}
}

print "Flight ID: $faFlightId\n";

# Compile the GetFlightTrack call
my $getFlightTrackCall = $soapSrv->compileClient(
	operation => "GetFlightTrack",
	transport_hook => \&basic_auth
);

# Query for the flight track info
my %request = (
	ident => $faFlightId
);

($response, $trace) = $getFlightTrackCall->(%request);

if (!$trace->{'errors'}) {
	my @tracks = @{$response->{'parameters'}->{'GetFlightTrackResult'}->{'tracks'}};
	foreach my $track (@tracks) {
		my $dt = DateTime->from_epoch( epoch => $track->{'timestamp'} );
		print "Lat: $track->{'latitude'}, Lng: $track->{'longitude'}, Time: ".$dt->year."/".$dt->month."/".$dt->day." ".$dt->hour.":".$dt->minute.":".$dt->second."\n";
	}
}