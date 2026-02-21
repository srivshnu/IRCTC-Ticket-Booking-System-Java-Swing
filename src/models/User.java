package models;

import java.util.ArrayList;

public class User
{
    private String username;
    private String password;
    private String email;
    private ArrayList<Booking> bookings;
    
    public User(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bookings = new ArrayList<>();
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public ArrayList<Booking> getBookings() { return bookings; }
    public void addBooking(Booking booking) { bookings.add(booking); }
}