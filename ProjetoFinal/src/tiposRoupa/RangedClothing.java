package tiposRoupa;

import tiposPrecos.RangedPrice;

public class RangedClothing extends AbstractClothing {
		
	public RangedClothing(String code, String description, double weight, String type, 
						  String color, String size, double unitPrice, double shipping) {
		
		super(code, description, weight, type, color, size, unitPrice);
		price = new RangedPrice(unitPrice, shipping);
	}
	
	public String toString() {
		String desc;
		if(getDescription().length() > 30) {
			desc = getDescription().substring(0, 30);
		}else {
			desc = getDescription();
		}
		return getCode() + "|" + desc + "|" + getType() + "|" + getColor() + "|" + 
			   getSize() + "|" + getWeight() + "|" + unitPrice();
	}
	
}
