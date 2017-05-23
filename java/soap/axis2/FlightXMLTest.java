import org.apache.axis2.transport.http.*;
import org.apache.axis2.transport.http.impl.httpclient4.HttpTransportPropertiesImpl;
import com.flightaware.flightxml.soap.flightxml3.*;
import com.flightaware.flightxml.soap.flightxml3.FlightXML3Stub.*;

import java.rmi.RemoteException;
import java.util.Arrays;

class FlightXMLTest {
	public static void main(String[] args) {
		try {
			HTTPAuthenticator auth = new HttpTransportPropertiesImpl.Authenticator();
			auth.setUsername("YOUR_USERNAME");
			auth.setPassword("YOUR_API_KEY");

			FlightXML3Stub stub = new FlightXML3Stub();
			stub._getServiceClient().getOptions().setProperty(HTTPConstants.CHUNKED, false);
			stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);

			// Setup the request
			AirportBoardsRequest req = new AirportBoardsRequest();
			req.setAirport_code("KORD");
			req.setInclude_ex_data(false);
			req.setType("enroute");
			req.setHowMany(10);

			AirportBoardsRequestE reqE = new AirportBoardsRequestE();
			reqE.setAirportBoardsRequest(req);

			// Make the call
			System.out.println("Flights enroute to KORD:");
			Arrays.asList(
				stub.airportBoards(reqE)
					.getAirportBoardsResults()
						.getAirportBoardsResult()
						.getEnroute()
						.getFlights())
					.forEach(f -> {
				System.out.println(String.format("%s (%s)\t%s (%s)", f.getIdent(), f.getAircrafttype(),
						f.getOrigin().getAirport_name(), f.getOrigin().getCode()));
			});
		} catch (RemoteException ex) {
			System.err.println(ex);
		}
	}
}