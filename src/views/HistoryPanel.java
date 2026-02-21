package views;

import models.User;
import models.Booking;
import models.Passenger;
import utils.Theme;
import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {
    public HistoryPanel(User user, Runnable onBack) {
        setLayout(new BorderLayout());
        
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(Theme.PRIMARY);
        JButton backButton = new JButton("← Home");
        backButton.setFont(Theme.SMALL_BUTTON);
        backButton.setBackground(Theme.PRIMARY);
        backButton.setForeground(Theme.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> onBack.run());
        topBar.add(backButton);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Theme.BACKGROUND);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JLabel title = new JLabel("My Bookings");
        title.setFont(Theme.TITLE_SMALL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel bookingsPanel = new JPanel();
        bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
        
        if (user.getBookings().isEmpty()) {
            JLabel noBookings = new JLabel("No bookings found");
            noBookings.setFont(Theme.SUBTITLE);
            noBookings.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookingsPanel.add(noBookings);
        } else {
            for (Booking booking : user.getBookings()) {
                bookingsPanel.add(createBookingCard(booking));
                bookingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(bookingsPanel);
        centerPanel.add(scrollPane);
        
        add(topBar, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
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
}