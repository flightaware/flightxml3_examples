using System;
using System.Text;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using Flurl;
using System.Collections.Generic;
using System.Runtime.Serialization.Json;

namespace FlightXMLClient
{
    class Program
    {
        static void Main(string[] args)
        {
            string fxmlUrl = "http://flightxml.flightaware.com/json/FlightXML3";
            string username = "YOUR_USERNAME";
            string apiKey = "YOUR_APIKEY";

            var uriBuilder = new UriBuilder(fxmlUrl);
            var requestUrl = fxmlUrl
                            .AppendPathSegment("AirportInfo")
                            .SetQueryParams(new
                            {
                                airport_code = "KIAH"
                            });
                            
            ProcessRepositories(requestUrl, username, apiKey).Wait();
        }

        private static async Task ProcessRepositories(string apiUrl, string username, string apiKey)
        {
            var serializer = new DataContractJsonSerializer(typeof(AirportInfoResult));
            var client = new HttpClient();
            var credentials = Encoding.ASCII.GetBytes(username + ":" + apiKey);
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", Convert.ToBase64String(credentials));

            var streamTask = client.GetStreamAsync(apiUrl);
            var airportInfo = serializer.ReadObject(await streamTask) as AirportInfoResult;
            Console.WriteLine(airportInfo.AirportResult.Name);
            Console.WriteLine(airportInfo.AirportResult.Code);
        }
    }
}
