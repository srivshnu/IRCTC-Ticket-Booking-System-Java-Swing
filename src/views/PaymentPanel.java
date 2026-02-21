package views;

import utils.Theme;
import utils.InputValidator;
import javax.swing.*;
import java.awt.*;

public class PaymentPanel extends JPanel {
    public PaymentPanel(double totalFare, int numPassengers, Runnable onPaySuccess, Runnable onBack) {
        setLayout(new BorderLayout());
        
        JPanel topBar = createTopBar("← Booking", e -> onBack.run());
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Theme.BACKGROUND);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        JLabel title = createCenteredLabel("Payment", Theme.TITLE_SMALL);
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel fareInfo = createCenteredLabel(String.format("Total Fare: ₹%.2f (%d passengers)", 
            totalFare, numPassengers), Theme.SUBTITLE);
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
            
            onPaySuccess.run();
        });
        
        centerPanel.add(payButton);
        
        add(topBar, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createTopBar(String buttonText, java.awt.event.ActionListener action) {
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

    private JPanel createFieldPanel(String label, Component field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);
        JLabel jLabel = new JLabel(label);
        jLabel.setPreferredSize(new Dimension(120, 25));
        panel.add(jLabel);
        panel.add(field);
        return panel;
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
}