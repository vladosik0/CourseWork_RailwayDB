CREATE TABLE station(
id INTEGER PRIMARY KEY IDENTITY(1,1),
station_name VARCHAR (50) NOT NULL
)
CREATE TABLE train_route(
id INTEGER PRIMARY KEY IDENTITY(1,1),
route_name VARCHAR(250) NOT NULL
)
CREATE TABLE train(
id INTEGER PRIMARY KEY IDENTITY(1,1),
train_number INTEGER NOT NULL,
route_id INTEGER FOREIGN KEY REFERENCES train_route(id) NOT NULL,
departure_date DATETIME NOT NULL,
arrival_date DATETIME NOT NULL
)
CREATE TABLE wagon(
id INTEGER PRIMARY KEY IDENTITY(1,1),
wagon_number INTEGER NOT NULL,
wagon_type VARCHAR (20) NOT NULL,
train_id INTEGER FOREIGN KEY REFERENCES train(id) NOT NULL
)
CREATE TABLE seat(
id INTEGER PRIMARY KEY IDENTITY (1,1),
seat_number INTEGER NOT NULL,
wagon_id INTEGER FOREIGN KEY REFERENCES wagon(id) NOT NULL
)
CREATE TABLE route_station(
id INTEGER PRIMARY KEY IDENTITY (1,1),
route_serial_number INTEGER NOT NULL,
station_id INTEGER FOREIGN KEY REFERENCES station(id) NOT NULL,
route_id INTEGER FOREIGN KEY REFERENCES train_route(id) NOT NULL
)
CREATE TABLE ticket(
id INTEGER PRIMARY KEY IDENTITY(1,1),
price DECIMAL NOT NULL,
available BIT DEFAULT 1,
start_station_id INTEGER FOREIGN KEY REFERENCES route_station(id),
end_station_id INTEGER FOREIGN KEY REFERENCES route_station(id),
seat_id INTEGER FOREIGN KEY REFERENCES seat(id)
)

INSERT INTO station(station_name)
VALUES
('Київ'),
('Львів'),
('Варшава'),
('Будапешт'),
('Берлін'),
('Прага'),
('Відень'),
('Мюнхен'),
('Брюссель'),
('Париж')

INSERT INTO train_route
VALUES
('Київ-Львів-Варшава-Берлін'),
('Будапешт-Відень-Мюнхен-Берлін'),
('Прага-Відень-Будапешт'),
('Мюнхен-Прага-Варшава'),
('Париж-Брюссель-Мюнхен')



INSERT INTO train(train_number,route_id,departure_date,arrival_date)
VALUES
(101,1,CAST('2023-04-06 12:00:00' AS datetime),CAST('2023-04-07 10:00:00' AS datetime)),
(102,2,CAST('2023-04-06 15:00:00' AS datetime),CAST('2023-04-07 03:00:00' AS datetime)),
(103,3,CAST('2023-04-06 08:00:00' AS datetime),CAST('2023-04-06 15:00:00' AS datetime)),
(104,4,CAST('2023-04-06 02:30:00' AS datetime),CAST('2023-04-06 18:30:00' AS datetime)),
(105,5,CAST('2023-04-06 20:00:00' AS datetime),CAST('2023-04-07 07:00:00' AS datetime)),
(106,2,CAST('2023-04-06 04:30:00' AS datetime),CAST('2023-04-06 16:30:00' AS datetime)),
(107,1,CAST('2023-04-06 18:00:00' AS datetime),CAST('2023-04-07 16:00:00' AS datetime))

INSERT INTO wagon(wagon_number,wagon_type,train_id)
VALUES
(1001,'Купе',1),
(1002,'Плацкарт',2),
(1003,'Плацкарт',6),
(1004,'Плацкарт',7),
(1005,'Купе',3),
(1006,'Купе',3),
(1007,'Плацкарт',4),
(1008,'Купе',4),
(1009,'Купе',5),
(1010,'Плацкарт',5)

INSERT INTO seat(seat_number,wagon_id)
VALUES
(1,1),
(2,1),
(6,2),
(8,2),
(7,3),
(5,3),
(3,4),
(9,4),
(10,5),
(12,5),
(34,6),
(52,6),
(11,7),
(40,7),
(9,8),
(12,8),
(16,9),
(11,9),
(4,10),
(7,10)

INSERT INTO route_station(route_serial_number,station_id,route_id)
VALUES
(1,1,1),
(2,2,1),
(3,3,1),
(4,5,1),
(1,4,2),
(2,7,2),
(3,8,2),
(4,5,2),
(1,6,3),
(2,7,3),
(3,4,3),
(1,8,4),
(2,6,4),
(3,3,4),
(1,10,5),
(2,9,5),
(3,8,5)

INSERT INTO ticket(price,start_station_id,end_station_id,seat_id)
VALUES
(90.0,1,3,1),
(150.0,2,4,2),
(120.0,5,8,3),
(100.0,6,7,4),
(95.0,5,7,5),
(115.0,6,8,6),
(60.0,1,2,7),
(110.0,3,4,8),
(100.0,9,10,9),
(90.0,10,11,10),
(150.0,9,11,11),
(100.0,9,11,12),
(120.0,12,13,13),
(120.0,12,13,14),
(135.0,12,14,15),
(125.0,13,14,16),
(150.0,15,16,17),
(160.0,15,17,18),
(150.0,16,17,19),
(160.0,15,17,20),
(130.0,7,8,5),
(60.0,1,2,8),
(150.0,16,17,17),
(125.0,13,14,14),
(150.0,7,8,12)

