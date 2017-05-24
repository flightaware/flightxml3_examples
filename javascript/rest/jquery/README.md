Notes
------
It is not suggested that you embed the FlightXML API call into your page's script.  Not only does this expose your username and apiKey to anyone inspecting your source, but it also creates issues with making cross-origin requests.  Look at the node or php examples to see how you can make the requests from your backend and then supply that data to the frontend.

In this example the username and apiKey are appended to the URL.  That may stop working in the future as browsers block those requests.

Requirements
------------

* browser

What to change
-------------

Substitute your actual username and API key in index.html.

Running the example
-------------------
Open the index.html file in your browser of choice.