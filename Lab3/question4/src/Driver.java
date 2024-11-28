package lesson3.labs.prob4;

public class Driver {

	public static void main(String[] args) {

		Property[] properties = { new House(9000), new Condo(2), new Trailer() };
		Landlord landlord = new Landlord("Thiha Ye Yint Aung");

		for (Property prop: properties) {
			landlord.addProperty(prop);
		}

		double totalRent = Admin.computeTotalRent(landlord);
		System.out.println("Total rent: " + totalRent + ", owned by Landlord, " + landlord.getName());
	}
}
