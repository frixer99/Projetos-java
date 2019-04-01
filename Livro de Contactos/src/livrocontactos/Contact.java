package livrocontactos;

public class Contact {
	private String name;
	private int number;
	private String email;
	
	public Contact(String name, int number, String email) {
		this.name = name;
		this.number = number;
		this.email = email;
	}
	
	/** Devolve o nome do contacto: **/
	String getName() {
		return this.name;
	}
	
	/** Devolve o telefone do contacto: */
	int getPhone() {
		return this.number;
	}
	
	/** Devolve o e-mail do contacto: */
	String getEmail() {
		return this.email;
	}
	
	/** Altera o telefone do contacto para phone: */
	void setPhone(int phone) {
		this.number = phone;
	}
	
	/** Altera o telefone do contacto para e-mail: */
	void setEmail(String email) {
		this.email = email;
	}
	
	/** Devolve true, caso nome do contacto seja igual ao nome de other:
		@pre: otherContact != null */
	boolean equals(Contact other) {
		return this.getName() == other.getName();
	}
	
	/** Devolve uma representação String deste objecto */
	public String toString() {
		return this.getName() + ";" + this.getPhone() + ";" + this.getEmail();
	}
}
