package lesson3.labs.prob4;

public class Admin {
	public static double computeTotalRent(Landlord landlord) {
		double totalRent = 0;
		for (Property prop : landlord.getProperties()) {
			totalRent += prop.computeRent();
		}
		return totalRent;
	}
}
