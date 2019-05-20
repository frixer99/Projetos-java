package tiposRoupa;

import tiposPrecos.BasicPrice;

public class BasicClothing extends AbstractClothing {
	
	protected double shipping;
	
	public BasicClothing(String code, String description, double weight,String type, 
						 String color, String size, double unitPrice, double shipping) {
		
		super(code, description, weight, type, color, size, unitPrice);
		price = new BasicPrice(unitPrice, shipping);
	}
	
	public String toString() {
		String[] firstDescription = getDescription().split("(");
		return getCode() + "|" + firstDescription[0] + "|" + getType() + "|" + getColor() + "|" + 
			   getSize() + "|" + unitPrice() + "|" + unitShippingCost();
	}
	
}
