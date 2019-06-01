/**
 * @author Miguel P. Monteiro
 */
package common;

public interface IPriceBuilder {
	/** 
	 * @Pre nrItems > 0 
	 */
	void setNrItemsOrdered(int nrItems);

	/**
	 * @return number of copies of this product as registered on this object.
	 */
	int nrItemsOrdered();

	/**
	 * @return the unit price for this item, not taking into account
	 * any discounts and regardless of shipping costs
	 */
	double unitPrice();

	/**
	 * @return the shipping cost for one unit of this item
	 */
	double unitShippingCost();

	/**
	 * @return the total shipping cost for the number of units of this item
	 * as registered by this object
	 */
	double orderShippingCost();

	/**
	 * @return discount given at the level of the individual type of product
	 */
	public double discount();

	/**
	 * @return discount given at the level of the whole order
	 */
	public double orderDiscount();

	/**
	 * @return total cost of an order for this item, taking into account
	 * number of items, discounts and shipping costs 
	 */
	double orderPrice();

	/**
	 * @return region to which ordered items are to be dispatched
	 * Used only for RegionalPriceBuilder. Other implementations
	 * should provide just a dummy implementation.
	 */
	public void setOrderRegion(OrderRegion region);

	/**
	 * @return region to which ordered items are to be dispatched
	 * Used only for RegionalPriceBuilder. Other implementations
	 * should simply return null.
	 */
	public OrderRegion orderRegion();
}