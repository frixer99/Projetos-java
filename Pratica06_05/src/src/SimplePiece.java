package src;

public class SimplePiece extends AbstractPiece {
	
	public SimplePiece(String code, String description, double weight, String type, 
					   String color, String size, double unitPrice, double shipping) {
		super(code, description, weight, type, color, size, unitPrice, shipping);
	}
	
	public String toString() {
		return code + "| " + description + "| " + type + "| " + color + "| " + size + "| " + unitPrice + "| " + shipping;
	}
	
	public double unitShippingCost() {
		return shipping;
	}
	
}
