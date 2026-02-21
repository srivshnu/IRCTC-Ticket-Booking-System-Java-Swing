package models;

import java.util.ArrayList;

public class Booking
{
    private String pnr;
    private String trainNumber;
    private String trainName;
    private String date;
    private ArrayList<Passenger> passengers;
    private String classType;
    private double fare;
    
    public Booking(String pnr, String trainNumber, String trainName, String date,
                   ArrayList<Passenger> passengers, String classType, double fare)
    {
        this.pnr = pnr;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.date = date;
        this.passengers = passengers;
        this.classType = classType;
        this.fare = fare;
    }
    
    public String getPnr() { return pnr; }
    public String getTrainNumber() { return trainNumber; }
    public String getTrainName() { return trainName; }
    public String getDate() { return date; }
    public ArrayList<Passenger> getPassengers() { return passengers; }
    public String getClassType() { return classType; }
    public double getFare() { return fare; }
}