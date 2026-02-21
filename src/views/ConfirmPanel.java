package views;

import models.Booking;
import utils.Theme;
import javax.swing.*;
import java.awt.*;

public class ConfirmPanel extends JPanel {
    public ConfirmPanel(Booking booking, Runnable onHome, Runnable onHistory) {
        setLayout(new BorderLayout());
        
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
        detailsPanel.add(createCenteredLabel(String.format("Total Fare: ₹%.2f", booking.getFare()), Theme.SUBTITLE));
        
        centerPanel.add(detailsPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        
        JButton homeButton = createButton("Home");
        JButton historyButton = createButton("My Bookings");
        
        homeButton.addActionListener(e -> onHome.run());
        historyButton.addActionListener(e -> onHistory.run());
        
        buttonPanel.add(homeButton);
        buttonPanel.add(historyButton);
        
        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.CENTER);
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
}