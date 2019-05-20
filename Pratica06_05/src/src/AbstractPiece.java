package src;


public abstract class AbstractPiece{
	
	protected String code;
	protected String description;
	protected double weight;
	protected String type;
	protected String color;
	protected String size;
	protected double unitPrice;
	protected double shipping;
	
	public AbstractPiece(String code, String description, double weight, String type, 
						 String color, String size, double unitPrice, double shipping) {
		this.code = code;
		this.description = description;
		this.weight = weight;
		this.type = type;
		this.color = color;
		this.size = size;
		this.unitPrice = unitPrice;
		this.shipping = shipping;
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
		return unitPrice;
	}

}
