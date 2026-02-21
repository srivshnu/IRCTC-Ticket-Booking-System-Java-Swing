package components;

import utils.Theme;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CaptchaPanel extends JPanel
{
    private String captchaText;
    
    public CaptchaPanel()
    {
        setPreferredSize(new Dimension(100, 40));
        setBackground(Theme.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        regenerate();
    }
    
    public void regenerate()
    {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < 6; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        captchaText = builder.toString();
        repaint();
    }
    
    public String getText()
    {
        return captchaText;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                  RenderingHints.VALUE_ANTIALIAS_ON);
        
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.setColor(Color.BLACK);
        graphics.drawString(captchaText, 10, 27);
        
        Random random = new Random();
        for (int i = 0; i < 5; i++)
        {
            graphics.drawLine(random.nextInt(100), random.nextInt(40), random.nextInt(100), random.nextInt(40));
        }
    }
}