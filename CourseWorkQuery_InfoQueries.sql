SELECT train.train_number, wagon.wagon_number, seat.seat_number,
convert(varchar(50),train.departure_date,0) AS departure_date,
convert(varchar(50),train.arrival_date,0) AS arrival_date, ticket.price
FROM
station INNER JOIN route_station ON station.id = route_station.station_id INNER JOIN
(ticket INNER JOIN (seat INNER JOIN (train INNER JOIN wagon ON train.id = wagon.train_id) ON seat.wagon_id = wagon.id) ON ticket.seat_id = seat.id)
ON route_station.id = ticket.start_station_id

CREATE VIEW [ticket_info_demo] AS
SELECT train.train_number, wagon.wagon_number, seat.seat_number,
station.station_name AS start_station, ticket.end_station_id,
convert(varchar(50),train.departure_date,0) AS departure_date,
convert(varchar(50),train.arrival_date,0) AS arrival_date, ticket.price FROM
station INNER JOIN route_station ON station.id = route_station.station_id INNER JOIN
(ticket INNER JOIN (seat INNER JOIN (train INNER JOIN wagon ON train.id = wagon.train_id) ON seat.wagon_id = wagon.id) ON ticket.seat_id = seat.id)
ON route_station.id = ticket.start_station_id

CREATE VIEW [ticket_info] AS
SELECT train_number, wagon_number, seat_number,
start_station,station.station_name AS end_station,
departure_date, arrival_date, price
FROM ticket_info_demo INNER JOIN (route_station INNER JOIN station ON station.id = route_station.station_id) 
ON ticket_info_demo.end_station_id = route_station.id

SELECT train.train_number, wagon.wagon_number, seat.seat_number
FROM (seat INNER JOIN (train INNER JOIN wagon ON train.id = wagon.train_id) ON seat.wagon_id = wagon.id)


SELECT train.train_number, count(seat.seat_number) AS number_of_seats
FROM (seat INNER JOIN (train INNER JOIN wagon ON train.id = wagon.train_id) ON seat.wagon_id = wagon.id)
GROUP BY train_number

SELECT train_route.route_name,AVG(ticket.price) AS average_ticket_price
FROM ticket INNER JOIN (route_station INNER JOIN train_route ON route_station.route_id = train_route.id) ON ticket.start_station_id = route_station.id
GROUP BY train_route.route_name
HAVING AVG(ticket.price) > 120

UPDATE ticket
SET price = ticket.price/1.05

INSERT INTO train(train_number,route_id,departure_date,arrival_date)
VALUES
(108,1,CAST('2023-04-06 18:00:00' AS datetime),CAST('2023-04-07 16:00:00' AS datetime))

DELETE FROM train WHERE train.id NOT IN(SELECT wagon.train_id FROM wagon)