import java.util.ArrayList;
import java.util.List;

class Building {
    private List<Apartment> apartments;
    private double maintenanceCost;

    public Building(double maintenanceCost) {
        this.apartments = new ArrayList<>();
        this.maintenanceCost = maintenanceCost;
    }

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }

    public double calculateProfit() {
        double totalRent = 0;
        for (Apartment apartment : apartments) {
            totalRent += apartment.getRent();
        }
        return totalRent - maintenanceCost;
    }
}
