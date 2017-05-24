<?php

require_once 'vendor/autoload.php';

use GuzzleHttp\Client;

/*
 * This script will query FlightXML AirportBoards and return a list of the
 * next 5 flights arrived/departed/scheduled/enroute.  It expects the query parameter
 * airport_code to be set.
 *
 * It uses the guzzle http client to make the request from FlightXML.
 */

if (!$_GET['airport_code']) {
	echo json_encode(array('error' => 'Missing airport_code in request'));
	return;
}
$airport_code = $_GET['airport_code'];

// Your FlightXML username/password
$auth_info = [
	'YOUR_USERNAME',
	'YOUR_API_KEY'
];

// Create the base client - add whatever defauls you need here.
$client = new Client([
	'base_uri' => 'http://flightxml.flightaware.com/json/FlightXML3/',
	'auth' => $auth_info
]);

// Query AirportBoards
$queryParams = [
	'airport_code' => $airport_code,
	'include_ex_data' => 0,
	'howMany' => 5
];

$fxml_response = $client->request('GET', 'AirportBoards', ['query' => $queryParams]);

try {
	$body = json_decode($fxml_response->getBody(), true);

	if ($fxml_response->getStatusCode() == 200 && !array_key_exists('error', $body)) {
		foreach (['arrivals', 'departures', 'enroute', 'scheduled'] as $board) {
			if($body['AirportBoardsResult'][$board]) {
				$boardFlights = $body['AirportBoardsResult'][$board]['flights'];
				$response[$board] = $boardFlights;
			}
		}
	} else {
		$response['error'] = $body['error'];
	}
} catch (Exception $e) {
	echo json_encode(['error' => 'Failed to retrieve Airport Board details.']);
}

// Send back the data
header('Content-Type: application/json');
echo json_encode($response);