/**
 * @author Miguel P. Monteiro
 */
package common;

public enum OrderRegion {
	EU("EU"), NEAR_EU("NEAR-EU"), WORLDWIDE("WORLDWIDE");

	private String _string;
	OrderRegion(String designation) {
		_string = designation;
	}
	public String toString() {
		return _string;
	}
	public static OrderRegion toOrderRegion(String str) {
		switch(str) {
			case "EU": return EU;
			case "NEAR-EU": return NEAR_EU;
			case "WORLDWIDE": return WORLDWIDE;
		}
		return EU; //shouldn't reach this point
	}
}
