package EnerTips;

public class CookingAndRefrigerationTips implements EnergyTip {
    @Override
    public void displayTip() {
        System.out.println("Cooking and Refrigeration tips:");
        System.out.println("Turn off lights when you leave a room to save energy.");
        System.out.println("opt for energy-efficient appliances like microwaves, toaster ovens  ");
        System.out.println("well-stocked fridge and freezer retain cold better, reducing energy consumption.  ");
        System.out.println("Keep your refrigerator at 37°F (3°C) and freezer at 0°F (-18°C) for energy efficiency.  ");

        System.out.println("  ");
    }
}