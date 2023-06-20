# Cinema_Room_REST_Service
Virtual movie theater realised by REST service.

Endpoints:

* **_/seats_** endpoint that handles GET requests and returns the information about the movie theatre in JSON format.
* **_/purchase_** endpoint that handles POST requests and marks a booked ticket as purchased. A request should contain 
the following data:
row — the row number;
column — the column number.
* **_/return_** endpoint, which handles POST requests and allow customers to refund their tickets.
* **_/stats_** endpoint that will handle POST requests with URL parameters. If the URL parameters contain a password key
with a super_secret value, return the movie theatre statistics.