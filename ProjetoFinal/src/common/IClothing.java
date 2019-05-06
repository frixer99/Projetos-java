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
}