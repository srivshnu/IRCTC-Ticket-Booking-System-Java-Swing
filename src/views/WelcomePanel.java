package views;

import utils.Theme;
import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel(Runnable onLoginClick, Runnable onRegisterClick)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Theme.BACKGROUND);
        
        add(Box.createVerticalGlue());
        
        JLabel title = createCenteredLabel("IRCTC", Theme.TITLE_LARGE);
        title.setForeground(Theme.PRIMARY);
        add(title);
        
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel subtitle = createCenteredLabel("Indian Railway Catering and Tourism Corporation", Theme.SUBTITLE);
        add(subtitle);
        
        add(Box.createRigidArea(new Dimension(0, 50)));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        
        JButton loginButton = createButton("Login");
        JButton registerButton = createButton("Register");
        
        loginButton.addActionListener(e -> onLoginClick.run());
        registerButton.addActionListener(e -> onRegisterClick.run());
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel);
        
        add(Box.createVerticalGlue());
    }

    private JLabel createCenteredLabel(String text, Font font)
    {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JButton createButton(String text)
    {
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