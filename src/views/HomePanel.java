package views;

import utils.Theme;
import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(Runnable onSearchClick, Runnable onHistoryClick, Runnable onLogoutClick) {
        setLayout(new BorderLayout());
        
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topBar.setBackground(Theme.PRIMARY);
        
        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setForeground(Theme.WHITE);
        welcomeLabel.setFont(Theme.BUTTON);
        
        JButton historyButton = createSmallButton("My Bookings");
        JButton logoutButton = createSmallButton("Logout");
        
        historyButton.addActionListener(e -> onHistoryClick.run());
        logoutButton.addActionListener(e -> onLogoutClick.run());
        
        topBar.add(welcomeLabel);
        topBar.add(historyButton);
        topBar.add(logoutButton);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Theme.BACKGROUND);
        
        centerPanel.add(Box.createVerticalGlue());
        
        JLabel title = new JLabel("Book Your Train Ticket");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(title);
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        
        JButton searchButton = new JButton("Search Trains");
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchButton.setBackground(Theme.PRIMARY);
        searchButton.setForeground(Theme.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(e -> onSearchClick.run());
        
        centerPanel.add(searchButton);
        centerPanel.add(Box.createVerticalGlue());
        
        add(topBar, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
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