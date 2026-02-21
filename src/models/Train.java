package models;

public class Train
{
    private String number;
    private String name;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int sleeperSeats;
    private int ac3Seats;
    private int ac2Seats;
    private int ac1Seats;
    
    public Train(String number, String name, String source, String destination,
                 String departureTime, String arrivalTime, int sleeperSeats,
                 int ac3Seats, int ac2Seats, int ac1Seats)
    {
        this.number = number;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.sleeperSeats = sleeperSeats;
        this.ac3Seats = ac3Seats;
        this.ac2Seats = ac2Seats;
        this.ac1Seats = ac1Seats;
    }
    
    public String getNumber() { return number; }
    public String getName() { return name; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public int getSleeperSeats() { return sleeperSeats; }
    public int getAc3Seats() { return ac3Seats; }
    public int getAc2Seats() { return ac2Seats; }
    public int getAc1Seats() { return ac1Seats; }
    
    public void bookSeats(String classType, int count)
    {
        switch(classType)
        {
            case "Sleeper": sleeperSeats -= count; break;
            case "3AC": ac3Seats -= count; break;
            case "2AC": ac2Seats -= count; break;
            case "1AC": ac1Seats -= count; break;
        }
    }
}