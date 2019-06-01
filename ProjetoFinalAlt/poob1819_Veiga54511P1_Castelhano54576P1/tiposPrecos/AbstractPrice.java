package tiposPrecos;

import common.IPriceBuilder;

/**
 * @author Pedro Veiga e Ricardo Castelhano
 *
 */
public abstract class AbstractPrice implements IPriceBuilder {
	
	protected double unitPrice;
	protected int nrItems;
	
	public AbstractPrice() {
		this.nrItems = 0;
	}
	
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
