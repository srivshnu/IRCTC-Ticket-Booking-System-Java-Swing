# IRCTC-Ticket-Booking-System-Java-Swing

A desktop-based IRCTC-style train ticket booking system built using Core Java and Swing. It simulates the basic flow of an online railway reservation portal: user registration, login, train search, booking, payment, and booking history.

# Features
User registration and login (in-memory user storage)

Home dashboard after login

Train search by source, destination, and date

Display of available trains with class-wise availability

Passenger details entry (multiple passengers per booking)

Fare calculation based on class and number of passengers

Simple payment simulation screen

Booking confirmation screen with summary

Booking history per user (in-memory)

Theming with a custom Theme class for colors, fonts, and styling

Tech Stack
Language: Java (Core)

GUI Framework: Swing

Data structures: ArrayList, HashMap

Architecture: Single desktop application using CardLayout for screen navigation

Project Structure (Conceptual)
IRCTCApp – Main JFrame and application controller (screens, navigation, state)

User – Stores user credentials and booking history

Train – Represents a train, its route, timings, and seat availability per class

Passenger – Represents passenger details for a booking

Booking – Represents a confirmed booking with train, passengers, and fare

FareConfig – Holds fare values per class and related calculations

Theme – Centralized colors, fonts, and common UI helpers (buttons, labels, panels)

Note: Class names may differ slightly depending on how the project is organized, but the idea is as above.

How It Works
Welcome Screen

Options to Login or Register.

Registration

Create a new account with basic details.

Stored in memory using a HashMap<String, User>.

Login

Validates user credentials against stored users.

On success, navigates to Home.

Home Screen

Options like Search Trains, View History, Logout.

Search Trains

Enter source, destination, and date.

Lists matching trains with available seats by class.

Booking Flow

Select train and class.

Enter passenger details (name, age, gender, etc.).

Fare calculated based on number of passengers and selected class.

Payment Screen

Simulated payment (no real payment gateway).

On “success”, booking is confirmed.

Confirmation & History

Confirmation screen shows booking summary.

Booking saved into the current user’s history.

History screen lists all previous bookings for that user.

All data (users, trains, bookings) is maintained in memory for simplicity, making it easy to understand and run as a learning project.

AI Usage Disclosure:

Core logic for:

User registration and login

Train search and selection

Booking, payment simulation, and history

is written manually as practice in Java.

AI assistance was used only for:

Choosing and adding color palettes

Font,Styling and layout ideas

Renaming objects for increasing readability

This project is intended as a learning/portfolio project, not a production-ready IRCTC clone.

