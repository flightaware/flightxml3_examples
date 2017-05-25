<?php

$options = array(
                 'trace' => true,
                 'exceptions' => 0,
                 'login' => 'YOUR_USERNAME',
                 'password' => 'YOUR_API_KEY',
                 );
$client = new SoapClient('https://flightxml.flightaware.com/soap/FlightXML3/wsdl', $options);

// get the weather.
$params = array("airport_code" => "KAUS");
$result = $client->WeatherConditions($params);
foreach ($result->WeatherConditionsResult->conditions as $key => $condition) {
	print_r($condition);
	if (php_sapi_name() != 'cli') {
		echo "<br/>";
	}
	echo "\n";
}

?>