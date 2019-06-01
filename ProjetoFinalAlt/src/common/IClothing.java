/**
 * @author Miguel P. Monteiro
 */
package common;

public interface IClothing extends IPriceBuilder {
	String getCode();

	String getDescription();

	String getType();

	String getSize();

	String getColor();

	double getWeight();

	/**
	 * @return a ClothingKind value stating the the price mode used
	 */
	public ClothingKind pricingKind();
}