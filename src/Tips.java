import EnerTips.CookingAndRefrigerationTips;
import EnerTips.EnergyTip;
import EnerTips.HomeImprovementTips;
import EnerTips.LightTips;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tips {
    private List<EnergyTip> tipsList;
    private Scanner scanner; // Declare scanner as a class member

    public Tips() {
        tipsList = new ArrayList<>();
        scanner = new Scanner(System.in); // Initialize the scanner
        // Add different tips to the list
        tipsList.add(new LightTips());
        tipsList.add(new HomeImprovementTips());
        tipsList.add(new CookingAndRefrigerationTips());
    }

    public void displayAllTips() {
        System.out.println("  ");
        System.out.println("Energy Conservation Tips:");
        for (EnergyTip tip : tipsList) {
            tip.displayTip(); // Call the displayTip method on each EnergyTip object
        }

        System.out.println("Press Enter to go back...");
        scanner.nextLine();
    }
}