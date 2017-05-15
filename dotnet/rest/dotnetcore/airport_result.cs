using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.Serialization;

namespace FlightXMLClient
{
    [DataContract(Name="airportResult")]
    class AirportInfoResult
    {
        [DataMember(Name="AirportInfoResult")]
        public Airport AirportResult { get; set; }
    }
}
