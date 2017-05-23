import com.flightaware.flightxml.soap.FlightXML3.*;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

import java.util.Arrays;

class FlightXMLTest {
	public static void main(String[] args) {
		try {
			FlightXML3Locator locator = new FlightXML3Locator();
			FlightXML3Soap df = locator.getFlightXML3Soap();
			FlightXML3SoapStub stub = (FlightXML3SoapStub) df;

			// Replace with your username and password
			stub.setUsername("YOUR_USERNAME");
			stub.setPassword("YOUR_API_KEY");

			// Make the request
			AirportBoardsRequest req = new AirportBoardsRequest();
			req.setAirport_code("KIAH");
			req.setInclude_ex_data(false);
			req.setType("enroute");
			req.setFilter("");
			req.setHowMany(10);

			AirportBoardsStruct result = df.airportBoards(req).getAirportBoardsResult();
			System.out.println("Flights enroute to KIAH:");

			Arrays.asList(result.getEnroute().getFlights()).forEach(flight -> {
				System.out.println(String.format("%s (%s)\t%s (%s)", flight.getIdent(), flight.getAircrafttype(), flight.getOrigin().getAirport_name(), flight.getOrigin().getCode()));
			});

		} catch (ServiceException ex) {
			System.err.println(ex);
		} catch (RemoteException ex) {
			System.err.println(ex);
		}
	}
}