package tiposPrecos;

import common.OrderRegion;

public class RangedPrice extends AbstractPrice {
	private static final int TIER1 = 10;
	private static final int TIER2 = 20;
	private static final int TIER3 = 50;
	private static final int TIER4 = 100;
	private static final int TIER5 = 200;
	
	private double shipping;
	
	public RangedPrice(double unitPrice, double shipping) {
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
		if(nrItemsOrdered() <= TIER1) {
			return 0.00;
		}else if(nrItemsOrdered() <= TIER2) {
			return 0.05;
		}else if(nrItemsOrdered() <= TIER3) {
			return 0.10;
		}else if(nrItemsOrdered() <= TIER4) {
			return 0.15;
		}else if(nrItemsOrdered() <= TIER5) {
			return 0.20;
		}else {
			return 0.21;
		}
	}

	public double orderPrice() {
		return (nrItemsOrdered() * unitPrice()) * discount() + orderShippingCost();
	}

	public void setOrderRegion(OrderRegion region) {
		// DUMMY
	}

	public OrderRegion orderRegion() {
		// DUMMY
		return null;
	}

}
