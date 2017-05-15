Requirements
------------

* .NET Core > 1.0

This example uses NuGet for dependency management which is part of .NET Core.

What to change
-------------

Substitute your actual username and API key in program.cs.  You can also expand the Airport class
to include additional items from the AirportInfoResult (currently only name and code are
deserialized)

Running the example
-------------------
Open a command prompt and navigate to the program directory, then type:
dotnet restore
dotnet build
dotnet run

You can also open the project in Visual Studio and run the example using the IDE.