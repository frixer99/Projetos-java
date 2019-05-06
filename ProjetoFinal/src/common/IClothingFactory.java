/**
 * @author Miguel P. Monteiro
 */
package common;

import java.util.Collection;

public interface IClothingFactory {
	IPriceBuilder makeBasicPriceBuilder(double unitPrice, double shipping);
	IPriceBuilder makeRangedPriceBuilder(double unitPrice, double shipping);
	IPriceBuilder makeRegionalPriceBuilder(
			double unitPrice,
			double eu1stPrice, double euAddPrice,
			double near1stPrice, double nearAddPrice,
			double wW1stPrice, double wWAddPrice, OrderRegion region
	);
	IClothing makeClothing(ClothingKind kind, String code, String descr, double weight, String type, String color, String size,
			IPriceBuilder priceBuilder);

	Collection<IClothing> makeClothingCollection(); // either List- or Array-based
}
