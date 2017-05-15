using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.Serialization;

namespace FlightXMLClient
{
    [DataContract(Name="airport")]
    class Airport
    {
        [DataMember(Name= "name")]
        public string Name;
        [DataMember(Name = "airport_code")]
        public string Code;
    }
}
