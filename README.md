# booking-service
booking-service

Booking service implementation with Spring WebFLUX and R2DBC using PostgreSQL.

To spin up PostgreSQL you can run  docker-compose up -d --build

Available endpoints:
- POST /bookings - to create booking
- GET /bookings - to get all bookings
- GET /bookings?date={date} - to get booking by date
