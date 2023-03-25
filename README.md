# sensor_simple

**Task: Project3_TZ.pdf**

**Spring boot + Rest API + xchart + RestTemplate**

create table Sensor
(
id        int primary key generated always as identity ,
name      varchar(100) not null unique
);

create table Measurement
(
id         int primary key generated always as identity,
value      double precision not null,
raining    boolean NOT NULL ,
measurement_date_time timestamp not null ,
sensor varchar(100) REFERENCES Sensor (name)
);


**REST API**

POST
http://localhost:8080/sensors/registration
{
"name": "Sensor1"
}

POST
http://localhost:8080/measurements/add
{
"value":24.7,
"raining":false,
"sensor": {
"name":"Sensor1"
}
}
