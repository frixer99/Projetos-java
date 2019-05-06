/**
 * @author Miguel P. Monteiro
 */
package common;

public enum ClothingKind {
	BASIC("basic"), RANGED("ranged"), REGIONAL("regional");

	private String _fname;
	ClothingKind(String fname) {
		_fname = fname;
	}
	public String fname() { return _fname; }

	static final String SEP = ";";
	static final String DIR_PATH = "dat\\poob1819";
	static final String CSV_EXTENSION = "csv";

	public String filePath() {
		return DIR_PATH + "\\" + fname() + "." + CSV_EXTENSION;
	}
}
