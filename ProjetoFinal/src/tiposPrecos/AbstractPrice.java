package tiposPrecos;

import common.IPriceBuilder;

public abstract class AbstractPrice implements IPriceBuilder {
	
	protected double unitPrice;
	protected int nrItems;
	
	public void setNrItemsOrdered(int nrItems) {
		this.nrItems = nrItems;
	}

	public int nrItemsOrdered() {
		return nrItems;
	}

	public double unitPrice() {
		return unitPrice;
	}
	
	public double orderDiscount() {
		return discount();
	}
 
}
