package utils;

public class InputValidator
{
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_AGE = 1;
    private static final int MAX_AGE = 120;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CVV_LENGTH = 3;
    
    public static boolean isValidUsername(String username)
    {
        return username != null && username.length() >= MIN_USERNAME_LENGTH;
    }
    
    public static boolean isValidPassword(String password)
    {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH)
            return false;

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray())
        {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }
    
    public static boolean isValidEmail(String email)
    {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    public static boolean isValidAge(int age)
    {
        return age >= MIN_AGE && age <= MAX_AGE;
    }
    
    public static boolean isValidDate(String date)
    {
        if (date == null || !date.matches("\\d{2}-\\d{2}-\\d{4}"))
            return false;

        try
        {
            String[] parts = date.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 2024;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    public static boolean isValidCardNumber(String cardNumber)
    {
        return cardNumber != null && cardNumber.length() == CARD_NUMBER_LENGTH 
               && cardNumber.matches("\\d+");
    }
    
    public static boolean isValidCVV(String cvv)
    {
        return cvv != null && cvv.length() == CVV_LENGTH && cvv.matches("\\d+");
    }
}