package views;

import utils.Theme;
import utils.InputValidator;
import models.User;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class RegisterPanel extends JPanel {
    public RegisterPanel(HashMap<String, User> users, Runnable onRegisterSuccess, Runnable onBack) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Theme.BACKGROUND);
        
        add(Box.createRigidArea(new Dimension(0, 50)));
        
        JLabel title = createCenteredLabel("Create Account", Theme.TITLE_MEDIUM);
        add(title);
        
        add(Box.createRigidArea(new Dimension(0, 30)));
        
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
        
        registerButton.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());
            String confirm = new String(confirmField.getPassword());
            String email = emailField.getText().trim();
            
            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty() || email.isEmpty()) {
                messageLabel.setText("All fields required!");
                return;
            }
            if (!InputValidator.isValidUsername(username)) {
                messageLabel.setText("Username must be at least 4 chars!");
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
            
            userField.setText("");
            passField.setText("");
            confirmField.setText("");
            emailField.setText("");
            messageLabel.setText(" ");
            onRegisterSuccess.run();
        });
        
        backButton.addActionListener(e -> {
            userField.setText("");
            passField.setText("");
            confirmField.setText("");
            emailField.setText("");
            messageLabel.setText(" ");
            onBack.run();
        });
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        formPanel.add(buttonPanel);
        
        add(formPanel);
        add(Box.createVerticalGlue());
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
}