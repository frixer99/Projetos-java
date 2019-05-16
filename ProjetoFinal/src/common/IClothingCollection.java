/**
 * @author Miguel P. Monteiro
 */
package common;

import java.util.Iterator;

public interface IClothingCollection extends Iterable<IClothing> {
	/**
	 * Does not accept null
	 */
	void add(IClothing clothe);
	int size();
	boolean isEmpty();

	/**
	 * @param the identifier or code for a given IClothing product
	 * @return true if an element with that code is found, false otherwise
	 */
	boolean hasProduct(String code);

	/**
	 * @pre: hasProduct(code)
	 * @return the product with the specified code
	 */
	IClothing getProduct(String code);

	Iterator<IClothing> iterator();

	/**
	 * @return sum of nrItemsOrdered() values of all elements in this collection 
	 */
	int totalNumberOfItems();

	/**
	 * To be used only when this collection represents an order
	 * @return amount to be charged for this order before discount
	 */
	double sumPrice();

	/**
	 * To be used only when this collection represents an order
	 * @return amount to be charged for this order and taking
	 * the global amount discount into account
	 */
	double totalPrice();

	/**
	 * To be used only when this collection represents an order
	 * @return global amount discount for this order
	 */
	double discount();
}
