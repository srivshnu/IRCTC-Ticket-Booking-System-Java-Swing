package main;

import models.User;
import models.Train;
import models.Passenger;
import models.Booking;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class IRCTCApp extends JFrame {
    private HashMap<String, User> users;
    private ArrayList<Train> trains;
    private User currentUser;
    
    // Global booking state
    private Train selectedTrain;
    private String selectedClass;
    private String selectedDate;
    private double selectedFare;
    private ArrayList<Passenger> currentPassengers;
    
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public IRCTCApp() {
        users = new HashMap<>();
        trains = new ArrayList<>();
        currentPassengers = new ArrayList<>();
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        setTitle("IRCTC - Indian Railway Catering and Tourism Corporation");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        initializeTrains();
        setupStaticRoutes();
        
        add(mainPanel);
        cardLayout.show(mainPanel, "welcome");
        setVisible(true);
    }
    
    private void initializeTrains() {
        trains.add(new Train("12301", "Rajdhani Express", "New Delhi", "Howrah", "16:55", "10:05", 0, 50, 30, 20));
        trains.add(new Train("12302", "Rajdhani Express", "Howrah", "New Delhi", "17:00", "09:55", 0, 45, 28, 18));
        trains.add(new Train("12951", "Mumbai Rajdhani", "Mumbai Central", "New Delhi", "16:25", "08:35", 0, 60, 35, 25));
        trains.add(new Train("12430", "Lucknow AC SF", "New Delhi", "Lucknow", "22:20", "06:40", 0, 80, 45, 0));
        trains.add(new Train("12622", "Tamil Nadu Exp", "New Delhi", "Chennai", "22:30", "07:05", 120, 40, 25, 15));
        trains.add(new Train("12860", "Gitanjali Exp", "Mumbai CST", "Howrah", "06:00", "11:55", 150, 50, 30, 20));
    }
    
    private void setupStaticRoutes() {
        WelcomePanel welcomePanel = new WelcomePanel(
            () -> cardLayout.show(mainPanel, "login"),
            () -> cardLayout.show(mainPanel, "register")
        );
        
        LoginPanel loginPanel = new LoginPanel(users, 
            (user) -> {
                currentUser = user;
                cardLayout.show(mainPanel, "home");
            }, 
            () -> cardLayout.show(mainPanel, "welcome")
        );
        
        RegisterPanel registerPanel = new RegisterPanel(users,
            () -> cardLayout.show(mainPanel, "login"),
            () -> cardLayout.show(mainPanel, "welcome")
        );
        
        HomePanel homePanel = new HomePanel(
            () -> cardLayout.show(mainPanel, "search"),
            () -> showHistoryPanel(),
            () -> {
                currentUser = null;
                cardLayout.show(mainPanel, "welcome");
            }
        );
        
        SearchPanel searchPanel = new SearchPanel(trains, 
            (train, classType, fare, date) -> {
                selectedTrain = train;
                selectedClass = classType;
                selectedFare = fare;
                selectedDate = date;
                showBookingPanel();
            }, 
            () -> cardLayout.show(mainPanel, "home")
        );
        
        mainPanel.add(welcomePanel, "welcome");
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        mainPanel.add(homePanel, "home");
        mainPanel.add(searchPanel, "search");
    }

    // Dynamic routes that need fresh data every time they are shown
    
    private void showBookingPanel() {
        BookingPanel bookingPanel = new BookingPanel(selectedTrain, selectedClass, selectedDate, 
            (passengers) -> {
                currentPassengers = passengers;
                showPaymentPanel();
            },
            () -> cardLayout.show(mainPanel, "search")
        );
        mainPanel.add(bookingPanel, "booking");
        cardLayout.show(mainPanel, "booking");
    }
    
    private void showPaymentPanel() {
        double totalFare = selectedFare * currentPassengers.size();
        PaymentPanel paymentPanel = new PaymentPanel(totalFare, currentPassengers.size(),
            () -> processPayment(totalFare),
            () -> cardLayout.show(mainPanel, "booking")
        );
        mainPanel.add(paymentPanel, "payment");
        cardLayout.show(mainPanel, "payment");
    }
    
    private void processPayment(double totalFare) {
        String pnr = generatePNR();
        Booking booking = new Booking(pnr, selectedTrain.getNumber(), selectedTrain.getName(), 
            selectedDate, new ArrayList<>(currentPassengers), selectedClass, totalFare);
        
        currentUser.addBooking(booking);
        selectedTrain.bookSeats(selectedClass, currentPassengers.size());
        currentPassengers.clear();
        
        showConfirmPanel(booking);
    }
    
    private void showConfirmPanel(Booking booking) {
        ConfirmPanel confirmPanel = new ConfirmPanel(booking,
            () -> cardLayout.show(mainPanel, "home"),
            () -> showHistoryPanel()
        );
        mainPanel.add(confirmPanel, "confirm");
        cardLayout.show(mainPanel, "confirm");
    }
    
    private void showHistoryPanel() {
        HistoryPanel historyPanel = new HistoryPanel(currentUser, 
            () -> cardLayout.show(mainPanel, "home")
        );
        mainPanel.add(historyPanel, "history");
        cardLayout.show(mainPanel, "history");
    }

    private String generatePNR() {
        Random random = new Random();
        StringBuilder pnr = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            pnr.append(random.nextInt(10));
        }
        return pnr.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IRCTCApp::new);
    }
}