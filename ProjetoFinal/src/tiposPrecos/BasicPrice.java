package tiposPrecos;

import common.OrderRegion;

public class BasicPrice extends AbstractPrice {

	private double shipping;
	
	public BasicPrice(double unitPrice, double shipping) {
		this.unitPrice = unitPrice;
		this.shipping = shipping;
	}

	public double unitShippingCost() {
		return shipping;
	}

	public double orderShippingCost() {
		return shipping * nrItems;
	}

	public double discount() {
		return 0;
	}

	public double orderPrice() {
		return (nrItems * unitPrice) + orderShippingCost();
	}

	public void setOrderRegion(OrderRegion region) {
		// DUMMY

	}

	public OrderRegion orderRegion() {
		// DUMMY
		return null;
	}

}
