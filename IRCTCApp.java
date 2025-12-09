import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// Theme Constants
class Theme {
    public static final Color PRIMARY = new Color(176, 39, 45);
    public static final Color BACKGROUND = new Color(245, 245, 245);
    public static final Color WHITE = Color.WHITE;
    public static final Color ERROR = Color.RED;
    public static final Color SUCCESS = new Color(46, 125, 50);
    
    public static final Font TITLE_LARGE = new Font("Arial", Font.BOLD, 48);
    public static final Font TITLE_MEDIUM = new Font("Arial", Font.BOLD, 28);
    public static final Font TITLE_SMALL = new Font("Arial", Font.BOLD, 24);
    public static final Font SUBTITLE = new Font("Arial", Font.PLAIN, 16);
    public static final Font BUTTON = new Font("Arial", Font.BOLD, 14);
    public static final Font SMALL_BUTTON = new Font("Arial", Font.PLAIN, 12);
}

// Fare Constants
class FareConfig {
    public static final double SLEEPER_FARE = 500.0;
    public static final double AC3_FARE = 1000.0;
    public static final double AC2_FARE = 1500.0;
    public static final double AC1_FARE = 2500.0;
}

// Data Models
class User {
    private String username;
    private String password;
    private String email;
    private ArrayList<Booking> bookings;
    
    public User(String username, String password, String email) {
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

class Train {
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
                 int ac3Seats, int ac2Seats, int ac1Seats) {
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
    
    public void bookSeats(String classType, int count) {
        switch(classType) {
            case "Sleeper": sleeperSeats -= count; break;
            case "3AC": ac3Seats -= count; break;
            case "2AC": ac2Seats -= count; break;
            case "1AC": ac1Seats -= count; break;
        }
    }
}

class Passenger {
    private String name;
    private int age;
    private String gender;
    
    public Passenger(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
}

class Booking {
    private String pnr;
    private String trainNumber;
    private String trainName;
    private String date;
    private ArrayList<Passenger> passengers;
    private String classType;
    private double fare;
    
    public Booking(String pnr, String trainNumber, String trainName, String date,
                   ArrayList<Passenger> passengers, String classType, double fare) {
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

// Input Validator
class InputValidator {
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_AGE = 1;
    private static final int MAX_AGE = 120;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CVV_LENGTH = 3;
    
    public static boolean isValidUsername(String username) {
        return username != null && username.length() >= MIN_USERNAME_LENGTH;
    }
    
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }
    
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    public static boolean isValidAge(int age) {
        return age >= MIN_AGE && age <= MAX_AGE;
    }
    
    public static boolean isValidDate(String date) {
        if (date == null || !date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return false;
        }
        try {
            String[] parts = date.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 2024;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.length() == CARD_NUMBER_LENGTH 
               && cardNumber.matches("\\d+");
    }
    
    public static boolean isValidCVV(String cvv) {
        return cvv != null && cvv.length() == CVV_LENGTH && cvv.matches("\\d+");
    }
}

// Captcha Panel Component
class CaptchaPanel extends JPanel {
    private String captchaText;
    
    public CaptchaPanel() {
        setPreferredSize(new Dimension(100, 40));
        setBackground(Theme.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        regenerate();
    }
    
    public void regenerate() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < 6; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        captchaText = builder.toString();
        repaint();
    }
    
    public String getText() {
        return captchaText;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                  RenderingHints.VALUE_ANTIALIAS_ON);
        
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.setColor(Color.BLACK);
        graphics.drawString(captchaText, 10, 27);
        
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            graphics.drawLine(random.nextInt(100), random.nextInt(40), 
                            random.nextInt(100), random.nextInt(40));
        }
    }
}

// Main Application
public class IRCTCApp extends JFrame {
    private HashMap<String, User> users;
    private User currentUser;
    private ArrayList<Train> trains;
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    private JPanel welcomePanel;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JPanel homePanel;
    private JPanel searchPanel;
    private JPanel bookingPanel;
    private JPanel paymentPanel;
    private JPanel confirmPanel;
    private JPanel historyPanel;
    
    private Train selectedTrain;
    private String selectedClass;
    private String selectedDate;
    private double selectedFare;
    private ArrayList<Passenger> currentPassengers;
    
    public IRCTCApp() {
        users = new HashMap<>();
        trains = new ArrayList<>();
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        currentPassengers = new ArrayList<>();
        
        setTitle("IRCTC - Indian Railway Catering and Tourism Corporation");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        initializeTrains();
        createPanels();
        setupMainPanel();
        
        add(mainPanel);
        cardLayout.show(mainPanel, "welcome");
        setVisible(true);
    }
    
    private void initializeTrains() {
        trains.add(new Train("12301", "Rajdhani Express", "New Delhi", "Howrah", 
                           "16:55", "10:05", 0, 50, 30, 20));
        trains.add(new Train("12302", "Rajdhani Express", "Howrah", "New Delhi", 
                           "17:00", "09:55", 0, 45, 28, 18));
        trains.add(new Train("12951", "Mumbai Rajdhani", "Mumbai Central", "New Delhi", 
                           "16:25", "08:35", 0, 60, 35, 25));
        trains.add(new Train("12430", "Lucknow AC SF", "New Delhi", "Lucknow", 
                           "22:20", "06:40", 0, 80, 45, 0));
        trains.add(new Train("12622", "Tamil Nadu Exp", "New Delhi", "Chennai", 
                           "22:30", "07:05", 120, 40, 25, 15));
        trains.add(new Train("12860", "Gitanjali Exp", "Mumbai CST", "Howrah", 
                           "06:00", "11:55", 150, 50, 30, 20));
    }
    
    private void createPanels() {
        createWelcomePanel();
        createLoginPanel();
        createRegisterPanel();
        createHomePanel();
        createSearchPanel();
        bookingPanel = new JPanel(new BorderLayout());
        paymentPanel = new JPanel(new BorderLayout());
        confirmPanel = new JPanel(new BorderLayout());
        historyPanel = new JPanel(new BorderLayout());
    }
    
    private void setupMainPanel() {
        mainPanel.add(welcomePanel, "welcome");
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        mainPanel.add(homePanel, "home");
        mainPanel.add(searchPanel, "search");
        mainPanel.add(bookingPanel, "booking");
        mainPanel.add(paymentPanel, "payment");
        mainPanel.add(confirmPanel, "confirm");
        mainPanel.add(historyPanel, "history");
    }
    
    private void createWelcomePanel() {
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(Theme.BACKGROUND);
        
        welcomePanel.add(Box.createVerticalGlue());
        
        JLabel title = createCenteredLabel("IRCTC", Theme.TITLE_LARGE);
        title.setForeground(Theme.PRIMARY);
        welcomePanel.add(title);
        
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel subtitle = createCenteredLabel("Indian Railway Catering and Tourism Corporation", 
                                             Theme.SUBTITLE);
        welcomePanel.add(subtitle);
        
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 50)));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        
        JButton loginButton = createButton("Login");
        JButton registerButton = createButton("Register");
        
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        registerButton.addActionListener(e -> cardLayout.show(mainPanel, "register"));
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        welcomePanel.add(buttonPanel);
        
        welcomePanel.add(Box.createVerticalGlue());
    }
    
    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Theme.BACKGROUND);
        
        loginPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        
        JLabel title = createCenteredLabel("Login to IRCTC", Theme.TITLE_MEDIUM);
        loginPanel.add(title);
        
        loginPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        
        JTextField userField = new JTextField(20);
        JPasswordField passField = new JPasswordField(20);
        JTextField captchaField = new JTextField(10);
        CaptchaPanel captchaPanel = new CaptchaPanel();
        
        formPanel.add(createFieldPanel("Username:", userField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(createFieldPanel("Password:", passField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JPanel captchaInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        captchaInputPanel.setOpaque(false);
        captchaInputPanel.add(new JLabel("Captcha:"));
        captchaInputPanel.add(captchaPanel);
        captchaInputPanel.add(captchaField);
        formPanel.add(captchaInputPanel);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel messageLabel = createCenteredLabel(" ", Theme.SUBTITLE);
        messageLabel.setForeground(Theme.ERROR);
        formPanel.add(messageLabel);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        
        JButton loginButton = createButton("Login");
        JButton backButton = createButton("Back");
        
        loginButton.addActionListener(e -> handleLogin(userField, passField, captchaField, 
                                                       captchaPanel, messageLabel));
        backButton.addActionListener(e -> {
            clearFields(userField, passField, captchaField);
            messageLabel.setText(" ");
            captchaPanel.regenerate();
            cardLayout.show(mainPanel, "welcome");
        });
        
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        formPanel.add(buttonPanel);
        
        loginPanel.add(formPanel);
        loginPanel.add(Box.createVerticalGlue());
    }
    
    private void handleLogin(JTextField userField, JPasswordField passField, 
                            JTextField captchaField, CaptchaPanel captchaPanel, 
                            JLabel messageLabel) {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());
        String captcha = captchaField.getText().trim();
        
        if (username.isEmpty() || password.isEmpty() || captcha.isEmpty()) {
            messageLabel.setText("All fields required!");
            return;
        }
        
        if (!captcha.equals(captchaPanel.getText())) {
            messageLabel.setText("Invalid captcha!");
            captchaPanel.regenerate();
            captchaField.setText("");
            return;
        }
        
        if (!users.containsKey(username)) {
            messageLabel.setText("User not found!");
            return;
        }
        
        User user = users.get(username);
        if (!user.getPassword().equals(password)) {
            messageLabel.setText("Incorrect password!");
            return;
        }
        
        currentUser = user;
        clearFields(userField, passField, captchaField);
        messageLabel.setText(" ");
        captchaPanel.regenerate();
        cardLayout.show(mainPanel, "home");
    }
    
    private void createRegisterPanel() {
        registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBackground(Theme.BACKGROUND);
        
        registerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        
        JLabel title = createCenteredLabel("Create Account", Theme.TITLE_MEDIUM);
        registerPanel.add(title);
        
        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        
        JTextField userField = new JTextField(20);
        JPasswordField passField = new JPasswordField(20);
        JPasswordField confirmField = new JPasswordField(20);
        JTextField emailField = new JTextField(20);
        
        formPanel.add(createFieldPanel("Username:", userField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel passPanel = createFieldPanel("Password:", passField);
        JLabel passRequirement = new JLabel("(Min 8 chars, 1 uppercase, 1 number, 1 special)");
        passRequirement.setFont(new Font("Arial", Font.ITALIC, 11));
        passPanel.add(passRequirement);
        formPanel.add(passPanel);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(createFieldPanel("Confirm Pass:", confirmField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(createFieldPanel("Email:", emailField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel messageLabel = createCenteredLabel(" ", Theme.SUBTITLE);
        messageLabel.setForeground(Theme.ERROR);
        formPanel.add(messageLabel);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        
        JButton registerButton = createButton("Register");
        JButton backButton = createButton("Back");
        
        registerButton.addActionListener(e -> handleRegistration(userField, passField, 
                                                                 confirmField, emailField, 
                                                                 messageLabel));
        backButton.addActionListener(e -> {
            clearFields(userField, passField, confirmField, emailField);
            messageLabel.setText(" ");
            cardLayout.show(mainPanel, "welcome");
        });
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        formPanel.add(buttonPanel);
        
        registerPanel.add(formPanel);
        registerPanel.add(Box.createVerticalGlue());
    }
    
    private void handleRegistration(JTextField userField, JPasswordField passField,
                                   JPasswordField confirmField, JTextField emailField,
                                   JLabel messageLabel) {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());
        String confirm = new String(confirmField.getPassword());
        String email = emailField.getText().trim();
        
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty() || email.isEmpty()) {
            messageLabel.setText("All fields required!");
            return;
        }
        
        if (!InputValidator.isValidUsername(username)) {
            messageLabel.setText("Username must be at least 4 characters!");
            return;
        }
        
        if (users.containsKey(username)) {
            messageLabel.setText("Username already exists!");
            return;
        }
        
        if (!InputValidator.isValidPassword(password)) {
            messageLabel.setText("Password doesn't meet requirements!");
            return;
        }
        
        if (!password.equals(confirm)) {
            messageLabel.setText("Passwords don't match!");
            return;
        }
        
        if (!InputValidator.isValidEmail(email)) {
            messageLabel.setText("Invalid email format!");
            return;
        }
        
        users.put(username, new User(username, password, email));
        JOptionPane.showMessageDialog(this, "Account created successfully!");
        
        clearFields(userField, passField, confirmField, emailField);
        messageLabel.setText(" ");
        cardLayout.show(mainPanel, "login");
    }
    
    private void createHomePanel() {
        homePanel = new JPanel(new BorderLayout());
        
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topBar.setBackground(Theme.PRIMARY);
        
        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setForeground(Theme.WHITE);
        welcomeLabel.setFont(Theme.BUTTON);
        
        JButton historyButton = createSmallButton("My Bookings");
        JButton logoutButton = createSmallButton("Logout");
        
        historyButton.addActionListener(e -> {
            updateHistoryPanel();
            cardLayout.show(mainPanel, "history");
        });
        
        logoutButton.addActionListener(e -> {
            currentUser = null;
            cardLayout.show(mainPanel, "welcome");
        });
        
        topBar.add(welcomeLabel);
        topBar.add(historyButton);
        topBar.add(logoutButton);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Theme.BACKGROUND);
        
        centerPanel.add(Box.createVerticalGlue());
        
        JLabel title = createCenteredLabel("Book Your Train Ticket", 
                                          new Font("Arial", Font.BOLD, 32));
        centerPanel.add(title);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        
        JButton searchButton = createButton("Search Trains");
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(e -> cardLayout.show(mainPanel, "search"));
        
        centerPanel.add(searchButton);
        centerPanel.add(Box.createVerticalGlue());
        
        homePanel.add(topBar, BorderLayout.NORTH);
        homePanel.add(centerPanel, BorderLayout.CENTER);
    }
    
    private void createSearchPanel() {
        searchPanel = new JPanel(new BorderLayout());
        
        JPanel topBar = createTopBar("← Home", e -> cardLayout.show(mainPanel, "home"));
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Theme.BACKGROUND);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JLabel title = createCenteredLabel("Search Trains", Theme.TITLE_SMALL);
        formPanel.add(title);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JPanel searchForm = new JPanel();
        searchForm.setLayout(new BoxLayout(searchForm, BoxLayout.Y_AXIS));
        searchForm.setOpaque(false);
        
        String[] stations = {"New Delhi", "Mumbai Central", "Mumbai CST", "Howrah", 
                           "Chennai", "Lucknow", "Bangalore", "Hyderabad"};
        JComboBox<String> sourceBox = new JComboBox<>(stations);
        JComboBox<String> destBox = new JComboBox<>(stations);
        JTextField dateField = new JTextField("DD-MM-YYYY");
        
        searchForm.add(createFieldPanel("From:", sourceBox));
        searchForm.add(Box.createRigidArea(new Dimension(0, 15)));
        searchForm.add(createFieldPanel("To:", destBox));
        searchForm.add(Box.createRigidArea(new Dimension(0, 15)));
        searchForm.add(createFieldPanel("Date:", dateField));
        
        formPanel.add(searchForm);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        
        JButton searchButton = createButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(e -> performSearch(sourceBox, destBox, dateField, 
                                                         resultsPanel));
        
        formPanel.add(searchButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(scrollPane);
        
        searchPanel.add(topBar, BorderLayout.NORTH);
        searchPanel.add(formPanel, BorderLayout.CENTER);
    }
    
    private void performSearch(JComboBox<String> sourceBox, JComboBox<String> destBox,
                              JTextField dateField, JPanel resultsPanel) {
        String source = (String) sourceBox.getSelectedItem();
        String destination = (String) destBox.getSelectedItem();
        String date = dateField.getText().trim();
        
        if (source.equals(destination)) {
            JOptionPane.showMessageDialog(this, "Source and destination cannot be same!");
            return;
        }
        
        if (!InputValidator.isValidDate(date)) {
            JOptionPane.showMessageDialog(this, "Enter valid date (DD-MM-YYYY)!");
            return;
        }
        
        selectedDate = date;
        resultsPanel.removeAll();
        
        boolean found = false;
        for (Train train : trains) {
            if (train.getSource().equals(source) && train.getDestination().equals(destination)) {
                found = true;
                JPanel trainCard = createTrainCard(train);
                resultsPanel.add(trainCard);
                resultsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        
        if (!found) {
            JLabel noTrains = createCenteredLabel("No trains found for this route", Theme.SUBTITLE);
            resultsPanel.add(noTrains);
        }
        
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
    
    private JPanel createTrainCard(Train train) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setBackground(Theme.WHITE);
        
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setOpaque(false);
        infoPanel.add(new JLabel(String.format("%s - %s | %s → %s | %s - %s", 
            train.getNumber(), train.getName(), train.getSource(), train.getDestination(), 
            train.getDepartureTime(), train.getArrivalTime())));
        card.add(infoPanel);
        
        JPanel seatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        seatPanel.setOpaque(false);
        
        if (train.getSleeperSeats() > 0) {
            JButton sleeperButton = createSmallButton(String.format("Sleeper (₹%.0f) - %d seats", 
                FareConfig.SLEEPER_FARE, train.getSleeperSeats()));
            sleeperButton.addActionListener(e -> selectTrain(train, "Sleeper", FareConfig.SLEEPER_FARE));
            seatPanel.add(sleeperButton);
        }
        
        if (train.getAc3Seats() > 0) {
            JButton ac3Button = createSmallButton(String.format("3AC (₹%.0f) - %d seats", 
                FareConfig.AC3_FARE, train.getAc3Seats()));
            ac3Button.addActionListener(e -> selectTrain(train, "3AC", FareConfig.AC3_FARE));
            seatPanel.add(ac3Button);
        }
        
        if (train.getAc2Seats() > 0) {
            JButton ac2Button = createSmallButton(String.format("2AC (₹%.0f) - %d seats", 
                FareConfig.AC2_FARE, train.getAc2Seats()));
            ac2Button.addActionListener(e -> selectTrain(train, "2AC", FareConfig.AC2_FARE));
            seatPanel.add(ac2Button);
        }
        
        if (train.getAc1Seats() > 0) {
            JButton ac1Button = createSmallButton(String.format("1AC (₹%.0f) - %d seats", 
                FareConfig.AC1_FARE, train.getAc1Seats()));
            ac1Button.addActionListener(e -> selectTrain(train, "1AC", FareConfig.AC1_FARE));
            seatPanel.add(ac1Button);
        }
        
        card.add(seatPanel);
        return card;
    }
    
    private void selectTrain(Train train, String classType, double fare) {
        selectedTrain = train;
        selectedClass = classType;
        selectedFare = fare;
        updateBookingPanel();
        cardLayout.show(mainPanel, "booking");
    }
    
    private void updateBookingPanel() {
        bookingPanel.removeAll();
        
        JPanel topBar = createTopBar("← Search", e -> cardLayout.show(mainPanel, "search"));
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Theme.BACKGROUND);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel title = createCenteredLabel("Passenger Details", Theme.TITLE_SMALL);
        centerPanel.add(title);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel trainInfo = createCenteredLabel(String.format("%s - %s | %s | Date: %s", 
            selectedTrain.getNumber(), selectedTrain.getName(), selectedClass, selectedDate), 
            Theme.SUBTITLE);
        centerPanel.add(trainInfo);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        
        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(5);
        String[] genders = {"Male", "Female", "Other"};
        JComboBox<String> genderBox = new JComboBox<>(genders);
        
        formPanel.add(createFieldPanel("Name:", nameField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(createFieldPanel("Age:", ageField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(createFieldPanel("Gender:", genderBox));
        
        centerPanel.add(formPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel passengerListPanel = new JPanel();
        passengerListPanel.setLayout(new BoxLayout(passengerListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(passengerListPanel);
        scrollPane.setPreferredSize(new Dimension(700, 150));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        
        JButton addButton = createSmallButton("Add Passenger");
        JButton proceedButton = createButton("Proceed to Payment");
        
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String ageStr = ageField.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            
            if (name.isEmpty() || ageStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }
            
            try {
                int age = Integer.parseInt(ageStr);
                if (!InputValidator.isValidAge(age)) {
                    JOptionPane.showMessageDialog(this, "Invalid age!");
                    return;
                }
                
                Passenger passenger = new Passenger(name, age, gender);
                currentPassengers.add(passenger);
                
                JLabel passengerLabel = new JLabel(String.format("%d. %s (%d, %s)", 
                    currentPassengers.size(), name, age, gender));
                passengerListPanel.add(passengerLabel);
                passengerListPanel.revalidate();
                passengerListPanel.repaint();
                
                nameField.setText("");
                ageField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number!");
            }
        });
        
        proceedButton.addActionListener(e -> {
            if (currentPassengers.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Add at least one passenger!");
                return;
            }
            updatePaymentPanel();
            cardLayout.show(mainPanel, "payment");
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(proceedButton);
        
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(scrollPane);
        
        bookingPanel.add(topBar, BorderLayout.NORTH);
        bookingPanel.add(centerPanel, BorderLayout.CENTER);
        bookingPanel.revalidate();
        bookingPanel.repaint();
    }
    
    private void updatePaymentPanel() {
        paymentPanel.removeAll();
        
        JPanel topBar = createTopBar("← Booking", e -> cardLayout.show(mainPanel, "booking"));
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Theme.BACKGROUND);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JLabel title = createCenteredLabel("Payment", Theme.TITLE_SMALL);
        centerPanel.add(title);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        double totalFare = selectedFare * currentPassengers.size();
        JLabel fareInfo = createCenteredLabel(String.format("Total Fare: ₹%.2f (%d passengers)", 
            totalFare, currentPassengers.size()), Theme.SUBTITLE);
        fareInfo.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(fareInfo);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        
        JTextField cardField = new JTextField(20);
        JTextField cvvField = new JTextField(5);
        JTextField nameField = new JTextField(20);
        
        formPanel.add(createFieldPanel("Card Number:", cardField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(createFieldPanel("CVV:", cvvField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(createFieldPanel("Cardholder Name:", nameField));
        
        centerPanel.add(formPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JButton payButton = createButton("Pay Now");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.addActionListener(e -> {
            String cardNumber = cardField.getText().trim();
            String cvv = cvvField.getText().trim();
            String name = nameField.getText().trim();
            
            if (cardNumber.isEmpty() || cvv.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields required!");
                return;
            }
            
            if (!InputValidator.isValidCardNumber(cardNumber)) {
                JOptionPane.showMessageDialog(this, "Invalid card number!");
                return;
            }
            
            if (!InputValidator.isValidCVV(cvv)) {
                JOptionPane.showMessageDialog(this, "Invalid CVV!");
                return;
            }
            
            processPayment(totalFare);
        });
        
        centerPanel.add(payButton);
        
        paymentPanel.add(topBar, BorderLayout.NORTH);
        paymentPanel.add(centerPanel, BorderLayout.CENTER);
        paymentPanel.revalidate();
        paymentPanel.repaint();
    }
    
    private void processPayment(double totalFare) {
        String pnr = generatePNR();
        
        Booking booking = new Booking(pnr, selectedTrain.getNumber(), 
            selectedTrain.getName(), selectedDate, 
            new ArrayList<>(currentPassengers), selectedClass, totalFare);
        
        currentUser.addBooking(booking);
        selectedTrain.bookSeats(selectedClass, currentPassengers.size());
        
        updateConfirmPanel(booking);
        currentPassengers.clear();
        cardLayout.show(mainPanel, "confirm");
    }
    
    private void updateConfirmPanel(Booking booking) {
        confirmPanel.removeAll();
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Theme.BACKGROUND);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JLabel successLabel = createCenteredLabel("✓ Booking Confirmed!", Theme.TITLE_MEDIUM);
        successLabel.setForeground(Theme.SUCCESS);
        centerPanel.add(successLabel);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setOpaque(false);
        
        detailsPanel.add(createCenteredLabel("PNR: " + booking.getPnr(), Theme.SUBTITLE));
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(createCenteredLabel(String.format("Train: %s - %s", 
            booking.getTrainNumber(), booking.getTrainName()), Theme.SUBTITLE));
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(createCenteredLabel("Date: " + booking.getDate(), Theme.SUBTITLE));
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(createCenteredLabel("Class: " + booking.getClassType(), Theme.SUBTITLE));
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(createCenteredLabel(String.format("Total Fare: ₹%.2f", 
            booking.getFare()), Theme.SUBTITLE));
        
        centerPanel.add(detailsPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        
        JButton homeButton = createButton("Home");
        JButton historyButton = createButton("My Bookings");
        
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "home"));
        historyButton.addActionListener(e -> {
            updateHistoryPanel();
            cardLayout.show(mainPanel, "history");
        });
        
        buttonPanel.add(homeButton);
        buttonPanel.add(historyButton);
        
        centerPanel.add(buttonPanel);
        
        confirmPanel.add(centerPanel, BorderLayout.CENTER);
        confirmPanel.revalidate();
        confirmPanel.repaint();
    }
    
    private void updateHistoryPanel() {
        historyPanel.removeAll();
        
        JPanel topBar = createTopBar("← Home", e -> cardLayout.show(mainPanel, "home"));
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Theme.BACKGROUND);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel title = createCenteredLabel("My Bookings", Theme.TITLE_SMALL);
        centerPanel.add(title);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel bookingsPanel = new JPanel();
        bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
        
        if (currentUser.getBookings().isEmpty()) {
            JLabel noBookings = createCenteredLabel("No bookings found", Theme.SUBTITLE);
            bookingsPanel.add(noBookings);
        } else {
            for (Booking booking : currentUser.getBookings()) {
                JPanel bookingCard = createBookingCard(booking);
                bookingsPanel.add(bookingCard);
                bookingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(bookingsPanel);
        centerPanel.add(scrollPane);
        
        historyPanel.add(topBar, BorderLayout.NORTH);
        historyPanel.add(centerPanel, BorderLayout.CENTER);
        historyPanel.revalidate();
        historyPanel.repaint();
    }
    
    private JPanel createBookingCard(Booking booking) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setBackground(Theme.WHITE);
        card.setMaximumSize(new Dimension(700, 150));
        
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setOpaque(false);
        infoPanel.add(new JLabel(String.format("PNR: %s | Train: %s - %s", 
            booking.getPnr(), booking.getTrainNumber(), booking.getTrainName())));
        card.add(infoPanel);
        
        JPanel detailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        detailPanel.setOpaque(false);
        detailPanel.add(new JLabel(String.format("Date: %s | Class: %s | Fare: ₹%.2f", 
            booking.getDate(), booking.getClassType(), booking.getFare())));
        card.add(detailPanel);
        
        JPanel passengerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passengerPanel.setOpaque(false);
        StringBuilder passengers = new StringBuilder("Passengers: ");
        for (int i = 0; i < booking.getPassengers().size(); i++) {
            Passenger p = booking.getPassengers().get(i);
            passengers.append(p.getName());
            if (i < booking.getPassengers().size() - 1) passengers.append(", ");
        }
        passengerPanel.add(new JLabel(passengers.toString()));
        card.add(passengerPanel);
        
        return card;
    }
    
    private String generatePNR() {
        Random random = new Random();
        StringBuilder pnr = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            pnr.append(random.nextInt(10));
        }
        return pnr.toString();
    }
    
    private JPanel createTopBar(String buttonText, ActionListener action) {
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(Theme.PRIMARY);
        
        JButton backButton = createSmallButton(buttonText);
        backButton.addActionListener(action);
        topBar.add(backButton);
        
        return topBar;
    }
    
    private JLabel createCenteredLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Theme.BUTTON);
        button.setBackground(Theme.PRIMARY);
        button.setForeground(Theme.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }
    
    private JButton createSmallButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Theme.SMALL_BUTTON);
        button.setBackground(Theme.PRIMARY);
        button.setForeground(Theme.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
    
    private JPanel createFieldPanel(String label, Component field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);
        JLabel jLabel = new JLabel(label);
        jLabel.setPreferredSize(new Dimension(120, 25));
        panel.add(jLabel);
        panel.add(field);
        return panel;
    }
    
    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IRCTCApp());
    }
}