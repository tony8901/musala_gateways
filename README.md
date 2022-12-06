## Spring boot project
***
REST service (JSON/HTTP) to store information in a database about gateways and their associated devices.
Each gateway has:
* A unique serial number (String),
* Human-readable name (String),
* IPv4 Address (String),
* Multiple associated devices (List).

Each device has:
* A UID (Long),
* Vendor (String),
* Creation date (LocalDate),
* Status - online/offline (Boolean).

The project includes the dependencies:
* [H2 Database](https://www.h2database.com/).
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa).
* [Spring Web](https://spring.io/projects/spring-ws).
* [Spring Boot Dev Tools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools).

Persistence:
Rest API with access to H2 database for persistence.

Features:
* Find all gateways (GET method)
  (http://host:port/api/gateways).

* Find a gateway by Serial Number (GET method)
  (http://host:port/api/gateways/{SerialNumber}).

* Add a new gateway (POST method with the Gateway object in the request body)
  (http://host:port/api/gateways).

* Add a device to the gateway (POST method)
  (http://host:port/api/gateways/{SerialNumber}/{UID}).

* Delete a gateway (DELETE method)
  (http://host:port/api/gateways/{SerialNumber}).

* Delete a device from gateway (DELETE method)
  (http://host:port/api/gateways/{SerialNumber}/devices/{UID}).

* Find all devices (GET method)
  (http://host:port/api/devices).

* Find a device by UID (GET method)
  (http://host:port/api/devices/{UID}).

* Add a new device (POST method with the object of type Device in the request body)
  (http://host:port/api/devices).

* Delete a device (DELETE method)
  (http://host:port/api/gateways/{UID}).

> The test data for Postman is in the "postman/postman_collection.json" file.

> The H2 database files are located in the "musala_h2_database" directory.