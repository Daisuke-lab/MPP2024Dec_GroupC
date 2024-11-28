//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create Apartments
        Apartment apt1 = new Apartment(1000);
        Apartment apt2 = new Apartment(1200);
        Apartment apt3 = new Apartment(900);
        Apartment apt4 = new Apartment(1100);

        // Create Building 1
        Building building1 = new Building(500);
        building1.addApartment(apt1);
        building1.addApartment(apt2);

        // Create Building 2
        Building building2 = new Building(400);
        building2.addApartment(apt3);
        building2.addApartment(apt4);

        // Create Landlord and add buildings
        Landlord landlord = new Landlord("Kalab");
        landlord.addBuilding(building1);
        landlord.addBuilding(building2);

        // Calculate and display total profit
        double totalProfit = landlord.calculateTotalProfit();
        System.out.println("Total Monthly Profit: $" + totalProfit);
    }
}
