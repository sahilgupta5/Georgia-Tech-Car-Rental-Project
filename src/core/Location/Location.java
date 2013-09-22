package core.Location;

/**
 * 
 * @author Rochelle Lobo
 *
 * This class represents the Location Model object and all it's attributes.
 *
 */

public class Location {
    private String locName;
    private int capacity;
    
    public Location(String locName, int capacity) {
        this.setLocName(locName);
        this.setCapacity(capacity);
    }
    
    public Location(){
        this(null, 0);
    }

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}
}
