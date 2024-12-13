import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserManager {
    private static final String CREDENTIALS_FILE = "credentials.txt";

    public boolean registerUser (User user) {
        if (isUsernameTaken(user.getUsername())) {
            return false; // Username is taken
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE, true))) {
            writer.write(user.getUsername() + "," + hashPassword(user.getPassword()));
            writer.newLine();
            return true; // Registration successful
        } catch (IOException e) {
            System.out.println("An error occurred while saving credentials.");
            e.printStackTrace();
            return false;
        }
    }

    private boolean isUsernameTaken(String username) {
        File file = new File(CREDENTIALS_FILE);
        if (!file.exists()) {
            return false; // File does not exist, so username is available
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username)) {
                    return true; // Username already exists
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while checking username.");
            e.printStackTrace();
        }
        return false; // Username is available
    }

    public User authenticateUser (String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(hashPassword(password))) {
                    return new User(username, password); // Return User object on successful authentication
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while authenticating user.");
            e.printStackTrace();
        }
        return null; // Authentication failed
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString(); // Return hashed password as a hex string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}