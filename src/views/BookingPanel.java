package views;

import models.Train;
import models.Passenger;
import utils.Theme;
import utils.InputValidator;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class BookingPanel extends JPanel {
    private ArrayList<Passenger> currentPassengers;

    public BookingPanel(Train train, String classType, String date, 
                        Consumer<ArrayList<Passenger>> onProceed, Runnable onBack) {
        currentPassengers = new ArrayList<>();
        setLayout(new BorderLayout());
        
        JPanel topBar = createTopBar("← Search", e -> onBack.run());
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Theme.BACKGROUND);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JLabel title = createCenteredLabel("Passenger Details", Theme.TITLE_SMALL);
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel trainInfo = createCenteredLabel(String.format("%s - %s | %s | Date: %s", 
            train.getNumber(), train.getName(), classType, date), Theme.SUBTITLE);
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
            onProceed.accept(currentPassengers);
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(proceedButton);
        
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(scrollPane);
        
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