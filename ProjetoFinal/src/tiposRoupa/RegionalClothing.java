package tiposRoupa;

import tiposPrecos.RegionalPrice;

public class RegionalClothing extends AbstractClothing {

	private double EU1;
	private double EUadd;
	private double NEU1;
	private double NEUadd;
	private double WW1;
	private double WWadd;
	
	public RegionalClothing(String code, String description, double weight, String type, String color, String size,
							double unitPrice, double EU1, double EUadd, double NEU1, double NEUadd, double WW1 ,double WWadd) {
		super(code, description, weight, type, color, size, unitPrice);
		price = new RegionalPrice(unitPrice, EU1, EUadd, NEU1, NEUadd, WW1, WWadd);
	}

	public String toString() {
		return "[" + getCode() + "/" + getDescription() + "/" + getSize() + " > <" + unitPrice() + 
				">[" + EU1 + ", " + EUadd + "][" + NEU1 + ", " + NEUadd + "][" +
				WW1 + ", " + WWadd + "] > " + "WHAT" + "]";
	}
}
