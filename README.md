# IRCTC Ticket Booking System

A desktop-based Indian Railway ticket booking application built with Java Swing. The project follows a modular MVC (Model-View-Controller) architecture for easy scalability and clean code separation.

## Features
* **User Authentication:** Secure login and registration with CAPTCHA validation.
* **Train Search:** Find available trains by source, destination, and date.
* **Ticket Booking:** Class-wise seat selection (Sleeper, 3AC, 2AC, 1AC) and dynamic fare calculation.
* **Passenger Management:** Add multiple passengers with age and gender details to a single booking.
* **Payment Simulation:** Card validation, total fare calculation, and automatic PNR generation.
* **Booking History:** Dashboard to view past bookings and ticket details.

## Tech Stack
* **Language:** Java
* **GUI Framework:** Java Swing & AWT
* **Architecture:** MVC Pattern

## Project Structure
* `/src/models/` - Core data structures (User, Train, Booking, Passenger).
* `/src/views/` - Separated GUI panels for each screen.
* `/src/main/` - Application entry point and main routing controller (`IRCTCApp.java`).
* `/src/utils/` - Shared constants, theme configurations, and input validators.
* `/src/components/` - Reusable custom UI components.

## How to Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/srivshnu/IRCTC-Ticket-Booking-System-Java-Swing.git](https://github.com/srivshnu/IRCTC-Ticket-Booking-System-Java-Swing.git)
2. Open the project in your preferred Java IDE.

3. Navigate to src/main/IRCTCApp.java.

4. Compile and run the file to launch the application.