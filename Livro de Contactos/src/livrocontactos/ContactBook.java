package livrocontactos;

public class ContactBook {
	private static final int INICIAL_SIZE = 50;
	private static final int GROWTH_FACTOR = 2;
	
	private int counter;
	private Contact[] contacts; //Vetor da classe Contact chamado contacts
	
	/** Construtor **/
	public ContactBook() {
		counter = 0;
		contacts = new Contact[INICIAL_SIZE];
	}
	
	/** Metodos auxiliares **/
	private int searchIndex(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		
		while(i < counter && !found) {
			if(contacts[i].getName().equals(name)) {
				result = i;
				found = true;
			}else 
				i++;
		}
		return result;
	}
	
	private boolean atCapacity() {
		return counter == contacts.length;
	}
	
	private void grow() {
		Contact[] temp = new Contact[GROWTH_FACTOR * size()];
		
		for(int i = 0; i < size(); i++)
			temp[i] = contacts[i];
		
		contacts = temp;
	}
	
	
	/** Indica se existe na agenda (true ou false)
	um contacto com o nome dado **/
	public boolean hasContact(String name) {
		return searchIndex(name) >= 0;
	}
	
	/** Indica o número de contactos na agenda **/
	public int size() {
		return counter;
	}
	
	/** Adiciona um novo contacto na agenda
		@pre: !hasContact(contact.getName()) **/
	public void addContact(Contact contact) {
		if(atCapacity())
			grow();
		contacts[counter] = contact;
		counter++;
	}
	
	/** Remove o contacto da agenda, cujo nome é o dado
		@pre: hasContact(name) **/
	public void	removeContact(String name) {
		contacts[searchIndex(name)] 
	}
	
	/** Consulta o telefone do contacto associado a um dado nome
		@pre: hasContact(name) **/
	public int getPhone(String name) {
		
	}
	
	/** Consulta o e-mail do contacto associado a um dado nome
		@pre: hasContact(name) **/
	public String getEmail(String name) {
		
	}
	
	/** Altera o telefone do contacto com o nome dado
		@pre: hasContact(name) **/
	public void setPhone(String name, int phone) {
		
	}
	
	/** Altera o e-mail do contacto com o nome dado
		@pre: hasContact(name) **/
	public void setEmail(String name, String email) {
		
	}
	
}
