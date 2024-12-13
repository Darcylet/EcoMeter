package appliances;

import java.io.*;
import java.util.Scanner;

public class Appliances {
    private String username;

    public Appliances(String username) {
        this.username = username;
    }

    public void addAppliance(Scanner scanner) {
        System.out.print("Enter appliance name: ");
        String applianceName = scanner.nextLine();

        System.out.print("Enter energy consumption (Watts): ");
        String energyConsumption = scanner.nextLine();

        System.out.print("How many hours a day do you use this appliance? ");
        String hoursUsed = scanner.nextLine();

        // Create a directory based on the username
        File userDirectory = new File(username);
        if (!userDirectory.exists()) {
            userDirectory.mkdir(); // Create the directory if it doesn't exist
        }

        // Create a filename based on the username
        String filename = username + "/appliances.txt";

        // Append the appliance to the user's file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(applianceName + "," + energyConsumption + "," + hoursUsed);
            writer.newLine();
            System.out.println("Appliance '" + applianceName + "' with energy consumption '" + energyConsumption + " Watts' and usage of '" + hoursUsed + " hours' added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the appliance.");
            e.printStackTrace();
        }
    }

    public void removeAppliance(Scanner scanner) {
        System.out.print("Enter appliance name to remove: ");
        String applianceNameToRemove = scanner.nextLine().trim(); // Get the input and trim whitespace

        String filename = username + "/appliances.txt";
        File inputFile = new File(filename);
        File tempFile = new File("tempfile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split by comma
                if (parts.length < 2) {
                    // Skip invalid entries
                    writer.write(line);
                    writer.newLine();
                    continue;
                }
                String applianceName = parts[0].trim(); // Get the appliance name and trim whitespace
                if (applianceName.equalsIgnoreCase(applianceNameToRemove)) {
                    found = true; // Found the appliance to remove
                    System.out.println("Appliance '" + applianceName + "' removed successfully.");
                    continue; // Skip writing this line to the temp file
                }
                writer.write(line); // Write the line to the temp file if it doesn't match
                writer.newLine();
            }

            if (!found) {
                System.out.println("Appliance '" + applianceNameToRemove + "' not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while removing the appliance.");
            e.printStackTrace();
        }

        // Delete the original file and rename the temp file
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public void viewAppliances() {
        String filename = username + "/appliances.txt";
        File inputFile = new File(filename);

        if (!inputFile.exists()) {
            System.out.println("No appliances found for user '" + username + "'.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            System.out.println(" ");
            System.out.println("Appliances for user '" + username + "':");
            boolean hasAppliances = false; // Flag to check if there are any appliances

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split by comma
                if (parts.length < 3) {
                    System.out.println("Invalid entry found: " + line);
                    continue; // Skip invalid entries
                }
                String applianceName = parts[0].trim();
                String energyConsumption = parts[1].trim();
                String hoursUsed = parts[2].trim();
                System.out.println("Appliance: " + applianceName);
                System.out.println("Power Consumption " + energyConsumption + " watts");
                System.out.println("Hours Used: " + hoursUsed + " hours per day");
                System.out.println(" ");

                hasAppliances = true; // Set flag to true if at least one appliance is found
            }

            if (!hasAppliances) {
                System.out.println("No valid appliances found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the appliances.");
            e.printStackTrace();
        }

        System.out.println(" ");
        System.out.println("Press Enter to go back");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for user input
    }
}