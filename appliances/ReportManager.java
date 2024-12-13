package appliances;

import java.io.*;
import java.util.*;

public class ReportManager {
    private String username;

    public ReportManager(String username) {
        this.username = username;
    }

    public void generateDailyReport() {
        String filename = username + "/appliances.txt";
        File inputFile = new File(filename);

        if (!inputFile.exists()) {
            System.out.println("No appliances found for user '" + username + "'.");
            return;
        }

        double totalDailyConsumption = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            System.out.println("Daily Consumption Report for user '" + username + "':");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split by comma
                if (parts.length < 3) {
                    System.out.println("Invalid entry found: " + line);
                    continue; // Skip invalid entries
                }
                String applianceName = parts[0].trim();
                double powerConsumption = Double.parseDouble(parts[1].trim()); // in watts
                double hoursUsed = Double.parseDouble(parts[2].trim()); // hours per day

                // Calculate daily consumption in kWh
                double dailyConsumption = (powerConsumption / 1000) * hoursUsed; // Convert watts to kW
                totalDailyConsumption += dailyConsumption;

                System.out.printf("Appliance: %s, Daily Consumption: %.2f kWh%n", applianceName, dailyConsumption);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the appliances.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing power consumption or hours used.");
            e.printStackTrace();
        }

        System.out.printf("Total Daily Consumption: %.2f kWh%n", totalDailyConsumption);
    }

    public void editAppliance(Scanner scanner) {
        String filename = username + "/appliances.txt";
        File inputFile = new File(filename);
        File tempFile = new File("tempfile.txt");

        if (!inputFile.exists()) {
            System.out.println("No appliances found for user '" + username + "'.");
            return;
        }

        System.out.print("Enter the name of the appliance you want to edit: ");
        String applianceNameToEdit = scanner.nextLine().trim();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split by comma
                if (parts.length < 3) {
                    writer.write(line);
                    writer.newLine();
                    continue; // Skip invalid entries
                }
                String applianceName = parts[0].trim();
                if (applianceName.equalsIgnoreCase(applianceNameToEdit)) {
                    found = true; // Found the appliance to edit
                    System.out.println(" ");
                    System.out.print("Enter new appliance name (or press Enter to keep the same): ");
                    String newApplianceName = scanner.nextLine().trim();
                    System.out.print("Enter new power consumption (watts) (or press Enter to keep the same): ");
                    String newPowerConsumption = scanner.nextLine().trim();
                    System.out.print("Enter new hours used per day (or press Enter to keep the same): ");
                    String newHoursUsed = scanner.nextLine().trim();

                    // Update the appliance details
                    if (!newApplianceName.isEmpty()) {
                        applianceName = newApplianceName;
                    }
                    if (!newPowerConsumption.isEmpty()) {
                        parts[1] = newPowerConsumption; // Update power consumption
                    }
                    if (!newHoursUsed.isEmpty()) {
                        parts[2] = newHoursUsed; // Update hours used
                    }

                    // Write the updated appliance details to the temp file
                    writer.write(applianceName + "," + parts[1] + "," + parts[2]);
                    writer.newLine();
                    System.out.println("Appliance '" + applianceNameToEdit + "' updated successfully.");
                } else {
                    writer.write(line); // Write the line to the temp file if it doesn't match
                    writer.newLine();
                }
            }

            if (!found) {
                System.out.println("Appliance '" + applianceNameToEdit + "' not found.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
