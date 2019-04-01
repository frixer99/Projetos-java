package livrocontactos;

public class ContactIterator implements Iterator{
	
	private Contact[] contacts;
	private int counter;
	private int currentContact;
	
	public ContactIterator(Contact[] contacts, int counter) {
		this.contacts = contacts;
		this.counter = counter;
		currentContact = 0;
	}
	
	
	public boolean hasNext() {
		return currentContact < counter;
	}
	public Contact next() {
		return contacts[currentContact++];
	}
}
