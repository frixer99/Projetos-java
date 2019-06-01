package tiposRoupa;

import common.IClothing;
import common.IPriceBuilder;
import common.OrderRegion;

/**
 * @author Pedro Veiga e Ricardo Castelhano
 *
 */
public abstract class AbstractClothing implements IClothing {
	protected String code;
	protected String description;
	protected double weight;
	protected String type;
	protected String color;
	protected String size;
	protected double unitPrice;
	protected int nrItems;
	protected IPriceBuilder price;

	public AbstractClothing(String code, String description, double weight,
							String type, String color, String size, double unitPrice) {
		this.code = code;
		this.description = description;
		this.weight = weight;
		this.type = type;
		this.color = color;
		this.size = size;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public double getWeight() {
		return weight;
	}

	public String getType() {
		return type;
	}

	public String getColor() {
		return color;
	}

	public String getSize() {
		return size;
	}
	
	public double unitPrice() {
		return price.unitPrice();
	}
	
	public void setNrItemsOrdered(int nrItems) {
		price.setNrItemsOrdered(nrItems);
	}

	public int nrItemsOrdered() {
		return price.nrItemsOrdered();
	}
	
	public double unitShippingCost() {
		return price.unitShippingCost();
	}
	
	public double orderShippingCost() {
		return price.orderShippingCost();
	}
	
	public double orderDiscount() {
		return discount();
	}

	public double discount() {
		return price.discount();
	}

	public double orderPrice() {
		return price.orderPrice();
	}

	public void setOrderRegion(OrderRegion region) {
		price.setOrderRegion(region);
	}

	public OrderRegion orderRegion() {
		return price.orderRegion();
	}

}
