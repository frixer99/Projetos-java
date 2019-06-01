package tiposPrecos;

import common.OrderRegion;

/**
 * @author Pedro Veiga e Ricardo Castelhano
 *
 */
public class RegionalPrice extends AbstractPrice {
	
	private double EU1;
	private double EUadd;
	private double NEU1;
	private double NEUadd;
	private double WW1;
	private double WWadd;
	private OrderRegion region;

	public RegionalPrice(double unitPrice, double EU1, double EUadd, double NEU1, double NEUadd, double WW1 ,double WWadd) {
		this.unitPrice = unitPrice;
		this.EU1 = EU1;
		this.EUadd = EUadd;
		this.NEU1 = NEU1;
		this.NEUadd = NEUadd;
		this.WW1 = WW1;
		this.WWadd = WWadd;

	}

	public double getEU1() {
		return EU1;
	}

	public double getEUadd() {
		return EUadd;
	}

	public double getNEU1() {
		return NEU1;
	}

	public double getNEUadd() {
		return NEUadd;
	}

	public double getWW1() {
		return WW1;
	}

	public double getWWadd() {
		return WWadd;
	}

	public double unitShippingCost() {
		switch(orderRegion()) {
		case EU: return EU1;
		case NEAR_EU: return NEU1;
		case WORLDWIDE: return WW1;
		default: return 0; //NAO DEVE CHEGAR AQUI
		}
	}

	public double orderShippingCost() {
		if(nrItemsOrdered() > 1) {
			switch(orderRegion()) {
			case EU: return unitShippingCost() + EUadd * (nrItemsOrdered() - 1);
			case NEAR_EU: return unitShippingCost() + NEUadd * (nrItemsOrdered() - 1);
			case WORLDWIDE: return unitShippingCost() + WWadd * (nrItemsOrdered() - 1);
			default: return 0; //NAO DEVE CHEGAR AQUI
			}
		}else {
			return unitShippingCost();
		}
	}

	public double discount() {
		return 0;
	}

	public double orderPrice() {
		return unitPrice() * nrItemsOrdered() + orderShippingCost();
	}

	public void setOrderRegion(OrderRegion region) {
		this.region = region;
	}

	public OrderRegion orderRegion() {
		return region;
	}

}
