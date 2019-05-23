package tiposRoupa;

import tiposPrecos.BasicPrice;

public class BasicClothing extends AbstractClothing {
		
	public BasicClothing(String code, String description, double weight,String type, 
						 String color, String size, double unitPrice, double shipping) {
		
		super(code, description, weight, type, color, size, unitPrice);
		price = new BasicPrice(unitPrice, shipping);
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
