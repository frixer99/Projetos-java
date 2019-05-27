package tiposRoupa;

import common.ClothingKind;
import tiposPrecos.RegionalPrice;

public class RegionalClothing extends AbstractClothing {
	
	public RegionalClothing(String code, String description, double weight, String type, String color, String size,
							double unitPrice, double EU1, double EUadd, double NEU1, double NEUadd, double WW1 ,double WWadd) {
		super(code, description, weight, type, color, size, unitPrice);
		price = new RegionalPrice(unitPrice, EU1, EUadd, NEU1, NEUadd, WW1, WWadd);
	}

	public String toString() {
		String desc;
		if(getDescription().length() > 30) {
			desc = getDescription().substring(0, 30);
		}else {
			desc = getDescription();
		}
		//TODO MUDAR O CAST
		return "[" + getCode() + "/" + desc + "/" + getSize() + " > <" + unitPrice() + 
				">[" + ((RegionalPrice) price).getEU1() + ", " + ((RegionalPrice) price).getEUadd() + "][" + ((RegionalPrice) price).getNEU1() +
				", " + ((RegionalPrice) price).getNEUadd() + "][" + ((RegionalPrice) price).getWW1() + ", " + ((RegionalPrice) price).getWWadd() + 
				"]]";
	}
	
	public ClothingKind pricingKind() {
		return ClothingKind.REGIONAL;	
	}

}
