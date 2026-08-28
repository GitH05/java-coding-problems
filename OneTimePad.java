import java.util.Scanner;

public class OneTimePad {
    
    // Method to encrypt the message using the key
    public static String encrypt(String message, String key) {
        StringBuilder encryptedText = new StringBuilder();
        
        // Check the key length matches the message length
        if (key.length() != message.length()) {
            throw new IllegalArgumentException("Key must be the same length as the message.");
        }
        
        // Encrypt each character of the message
        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i);
            char keyChar = key.charAt(i);
            
            // XOR the characters and add 'A' for the range
            char encryptedChar = (char) ((messageChar ^ keyChar) + ('D'+'E'));
            encryptedText.append(encryptedChar);
        }
        
        return encryptedText.toString();
    }
    
    // Method to decrypt the message using the key
    public static String decrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        
        // Ensure the key length matches the encrypted text length
        if (key.length() != encryptedText.length()) {
            throw new IllegalArgumentException("Key must be the same length as the encrypted text.");
        }
        
        // Decrypt each character of the encrypted text
        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedChar = encryptedText.charAt(i);
            char keyChar = key.charAt(i);
            
            // XOR the characters and subtract 'A' to get back the original character
            char decryptedChar = (char) ((encryptedChar - ('D'+'E')) ^ keyChar);
            decryptedText.append(decryptedChar);
        }
        
        return decryptedText.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the message: ");
        String message = scanner.nextLine();
        
        System.out.print("Enter the key (same length as message): ");
        String key = scanner.nextLine();
        
        // Encrypt the message
        String encryptedText = encrypt(message, key);
        System.out.println("Encrypted Text: " + encryptedText);
        
        // Decrypt the message
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText)
        scanner.close();
        
    }
}
