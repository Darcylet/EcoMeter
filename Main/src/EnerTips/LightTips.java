package EnerTips;

public class LightTips implements EnergyTip {
    @Override
    public void displayTip() {
        System.out.println("  ");
        System.out.println("Light saving tips");
        System.out.println("Turn off lights when you leave a room to save energy.");
        System.out.println("Replace incandescent and CFL bulbs with energy-efficient LEDs.  ");
        System.out.println("Take advantage of daylight by opening curtains and blinds during the day.  ");
        System.out.println("Install dimmers to control light intensity and reduce energy use.  ");

        System.out.println("  ");
    }
}