using System;
using console_app.FlightXML3;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace console_app
{
    class Program
    {
        static void Main(string[] args)
        {
            FlightXML3SoapClient client = new FlightXML3SoapClient();
            client.ClientCredentials.UserName.UserName = "sampleUser";
            client.ClientCredentials.UserName.Password = "abcdefghijklmnopqrstuvwxyz";

            AirportBoardsRequest req = new AirportBoardsRequest();
            req.airport_code = "KIAH";

            AirportBoardsStruct res = client.AirportBoards("KIAH", false, "", "enroute", 10, 0);
            Console.WriteLine("Flights enroute to KIAH");

            foreach (var flight in res.enroute.flights)
            {
                Console.WriteLine(String.Format("{0} ({1})\t{2} ({3})", flight.ident, flight.aircrafttype,
                    flight.origin.airport_name, flight.origin.code));
            }
        }
    }
}
