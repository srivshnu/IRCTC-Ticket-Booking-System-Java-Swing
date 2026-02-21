package views;

import utils.Theme;
import components.CaptchaPanel;
import models.User;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;

public class LoginPanel extends JPanel
{
    public LoginPanel(HashMap<String, User> users, Consumer<User> onLoginSuccess, Runnable onBack)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Theme.BACKGROUND);
        
        add(Box.createRigidArea(new Dimension(0, 80)));
        
        JLabel title = createCenteredLabel("Login to IRCTC", Theme.TITLE_MEDIUM);
        add(title);
        
        add(Box.createRigidArea(new Dimension(0, 40)));
        
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
        
        loginButton.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());
            String captcha = captchaField.getText().trim();
            
            if (username.isEmpty() || password.isEmpty() || captcha.isEmpty())
            {
                messageLabel.setText("All fields required!");
                return;
            }
            if (!captcha.equals(captchaPanel.getText())) {
                messageLabel.setText("Invalid captcha!");
                captchaPanel.regenerate();
                captchaField.setText("");
                return;
            }
            if (!users.containsKey(username) || !users.get(username).getPassword().equals(password))
            {
                messageLabel.setText("Invalid username or password!");
                return;
            }
            
            // Success! Clear fields and trigger the callback
            userField.setText("");
            passField.setText("");
            captchaField.setText("");
            messageLabel.setText(" ");
            captchaPanel.regenerate();
            onLoginSuccess.accept(users.get(username));
        });
        
        backButton.addActionListener(e -> {
            userField.setText("");
            passField.setText("");
            captchaField.setText("");
            messageLabel.setText(" ");
            captchaPanel.regenerate();
            onBack.run();
        });
        
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        formPanel.add(buttonPanel);
        
        add(formPanel);
        add(Box.createVerticalGlue());
    }

    private JLabel createCenteredLabel(String text, Font font)
    {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JPanel createFieldPanel(String label, Component field)
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);
        JLabel jLabel = new JLabel(label);
        jLabel.setPreferredSize(new Dimension(120, 25));
        panel.add(jLabel);
        panel.add(field);
        return panel;
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