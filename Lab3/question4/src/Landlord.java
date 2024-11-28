package lesson3.labs.prob4;

import javax.sound.sampled.Port;
import java.util.ArrayList;

// Create Landlord class to describe associations
public class Landlord {
    private String name;
    private ArrayList<Property> properties = new ArrayList<>();

    public Landlord(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }
}
