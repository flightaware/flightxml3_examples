using System;
using console_app.flightxml.flightaware.com;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;

namespace console_app
{
    class Program
    {
        static void Main(string[] args)
        {
            FlightXML3 client = new FlightXML3();

            System.Net.ICredentials creds = new NetworkCredential("USERNAME", "APIKEY");
            client.Credentials = creds;

            AirportBoardsStruct res = client.AirportBoards("KIAH", false, true, "", "enroute", 10, true, 0, true);
            Console.WriteLine("Flights enroute to KIAH");

            Console.WriteLine(res);
            foreach (var flight in res.enroute.flights)
            {
                Console.WriteLine(String.Format("{0} ({1})\t{2} ({3})", flight.ident, flight.aircrafttype, 
                flight.origin.airport_name, flight.origin.code));
            }
        }
    }
}
