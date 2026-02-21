package views;

import models.Train;
import utils.Theme;
import utils.InputValidator;
import utils.FareConfig;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchPanel extends JPanel {
    
    public interface TrainSelectListener {
        void onSelect(Train train, String classType, double fare, String date);
    }

    private String selectedDate;
    
    public SearchPanel(ArrayList<Train> trains, TrainSelectListener onTrainSelect, Runnable onBack) {
        setLayout(new BorderLayout());
        
        JPanel topBar = createTopBar("← Home", e -> onBack.run());
        
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
        searchButton.addActionListener(e -> {
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
                    resultsPanel.add(createTrainCard(train, onTrainSelect));
                    resultsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }
            
            if (!found) {
                resultsPanel.add(createCenteredLabel("No trains found for this route", Theme.SUBTITLE));
            }
            
            resultsPanel.revalidate();
            resultsPanel.repaint();
        });
        
        formPanel.add(searchButton);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        formPanel.add(scrollPane);
        
        add(topBar, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

    private JPanel createTrainCard(Train train, TrainSelectListener onTrainSelect) {
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
            JButton btn = createSmallButton(String.format("Sleeper (₹%.0f)", FareConfig.SLEEPER_FARE));
            btn.addActionListener(e -> onTrainSelect.onSelect(train, "Sleeper", FareConfig.SLEEPER_FARE, selectedDate));
            seatPanel.add(btn);
        }
        if (train.getAc3Seats() > 0) {
            JButton btn = createSmallButton(String.format("3AC (₹%.0f)", FareConfig.AC3_FARE));
            btn.addActionListener(e -> onTrainSelect.onSelect(train, "3AC", FareConfig.AC3_FARE, selectedDate));
            seatPanel.add(btn);
        }
        if (train.getAc2Seats() > 0) {
            JButton btn = createSmallButton(String.format("2AC (₹%.0f)", FareConfig.AC2_FARE));
            btn.addActionListener(e -> onTrainSelect.onSelect(train, "2AC", FareConfig.AC2_FARE, selectedDate));
            seatPanel.add(btn);
        }
        if (train.getAc1Seats() > 0) {
            JButton btn = createSmallButton(String.format("1AC (₹%.0f)", FareConfig.AC1_FARE));
            btn.addActionListener(e -> onTrainSelect.onSelect(train, "1AC", FareConfig.AC1_FARE, selectedDate));
            seatPanel.add(btn);
        }
        
        card.add(seatPanel);
        return card;
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