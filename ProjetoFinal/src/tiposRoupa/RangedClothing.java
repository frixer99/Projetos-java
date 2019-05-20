package tiposRoupa;

import tiposPrecos.RangedPrice;

public class RangedClothing extends AbstractClothing {
	
	protected double shipping;
	
	public RangedClothing(String code, String description, double weight, String type, 
						  String color, String size, double unitPrice, double shipping) {
		
		super(code, description, weight, type, color, size, unitPrice);
		price = new RangedPrice(unitPrice, shipping);
	}
	
	public String toString() {
		String[] firstDescription = description.split("(");
		return getCode() + "|" + firstDescription[0] + "|" + getType() + "|" + getColor() + "|" + 
		   	   getSize() + "|" + unitPrice() + "|" + unitShippingCost();
	}
	
}
