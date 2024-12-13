import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static User user; // This will hold the logged-in user

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        while (true) {
            System.out.println(" ");
            System.out.println("Welcome to EcoMeter!");
            System.out.println(" ");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        registerUser (scanner, userManager);
                        break;
                    case 2:
                        user = loginUser (scanner, userManager); // Store the logged-in user
                        if (user != null) {
                            // If login is successful, show the dashboard
                            showDashboard(user);
                        }
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace(); // Print stack trace for debugging
            }
        }
    }

    private static void registerUser (Scanner scanner, UserManager userManager) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(username, password);
        if (userManager.registerUser (user)) {
            System.out.println("Registration successful!");
            System.out.println(" ");
            System.out.println(" ");
        } else {
            System.out.println("Username already taken. Please choose a different username.");
            System.out.println(" ");
            System.out.println(" ");
        }
    }

    private static User loginUser (Scanner scanner, UserManager userManager) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User authenticatedUser  = userManager.authenticateUser (username, password); // Get the authenticated user
        if (authenticatedUser  != null) {
            System.out.println("Login successful!");
            return authenticatedUser ; // Return the authenticated user
        } else {
            System.out.println("Invalid username or password.");
            return null; // Return null if login fails
        }
    }

    private static void showDashboard(User user) {
        if (user != null) { // Check if user is not null
            Scanner scanner = new Scanner(System.in);
            Appliances applianceManager = new Appliances(user.getUsername()); // Create an Appliances instance
            ReportManager reportManager = new ReportManager(user.getUsername()); // Create a ReportManager instance

            while (true) {
                System.out.println("  ");
                System.out.println("Welcome to your dashboard, " + user.getUsername() + "!");
                System.out.println("1. Add Appliance");
                System.out.println("2. Remove Appliance");
                System.out.println("3. View Appliances");
                System.out.println("4. View Daily Report");
                System.out.println("5. Edit Appliances");
                System.out.println("6. How can I conserve energy?");
                System.out.println("7. Log out");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        applianceManager.addAppliance(scanner); // Call addAppliance method
                        break;
                    case 2:
                        applianceManager.removeAppliance(scanner); // Call removeAppliance method
                        break;
                    case 3:
                        applianceManager.viewAppliances(); // Call viewAppliances method
                        break;
                    case 4:
                        reportManager.generateDailyReport(); // Call generateDailyReport method
                        break;
                    case 5:
                        reportManager.editAppliance(scanner); // Call generateMonthlyReport method
                        break;
                    case 6:
                            Tips energyTips = new Tips();
                            energyTips.displayAllTips(); // Display all energy conservation tips
                        break;
                    case 7:
                        System.out.println("Logging out");
                        return; // Exit the dashboard
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("No user is logged in.");
        }
    }
}