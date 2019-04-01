package livrocontactos;

import java.util.Scanner;

public class Main {
	/** Constantes de comandos**/
	public static final String ADD_CONTACT = "AC";
	public static final String REMOVE_CONTACT = "RC";
	public static final String GET_CONTACT = "GC";
	public static final String GET_PHONE = "GP";
	public static final String GET_EMAIL = "GE";
	public static final String SET_PHONE = "SP";
	public static final String SET_EMAIL = "SE";
	public static final String LIST_CONTACTS = "LC";
	public static final String HELP = "H";
	public static final String QUIT = "Q";
	
	/** Constantes de mensagens **/
	public static final String WRONG_COMM = "Invalid Command.";
	public static final String CONTACT_EXISTS =	"Contact already exists.";
	public static final String CANNOT_REMOVE =	"Cannot remove contact.";
	public static final String NAME_NOT_EXIST =	"Contact does not exist.";
	public static final String CONTACT_ADDED = "Contact added.";
	public static final String CONTACT_REMOVED = "Contact removed";
	public static final String BYE = "Goodbye.";

	/** Interpretador de comandos **/
	private static String getCommand (Scanner in) {
		System.out.println("Introduza o seu comando:");
		String command = in.nextLine();
		return command.toUpperCase();
	}
	
	private static void help() {
		System.out.println("AC (adiciona um contacto)");
		System.out.println("RC (remove um contacto)");
		System.out.println("GP (consulta o telefone de um contacto)\r\n");
		System.out.println("GE (consulta o e-mail de um contacto)");
		System.out.println("SP (actualiza o telefone de um dado contacto)");
		System.out.println("SE (actualiza o e-mail de um dado contacto)");
		System.out.println("LC (lista todos os contactos existentes na agenda)");
		System.out.println("Q (sair)");
	}
	
	private static void addContact(ContactBook cb, Scanner in) {
		System.out.println("Adicionar contacto");
		System.out.println("Nome:");
		String name = in.nextLine();
		
		if(!cb.hasContact(name)) {
			System.out.println("Email:");
			String email = in.nextLine();
			System.out.println("Numero de telemovel:");
			int phone = in.nextInt();
			in.nextLine();
			
			cb.addContact(new Contact(name, phone, email));
			System.out.println(CONTACT_ADDED);
		}else
			System.out.println(CONTACT_EXISTS);
	}
	
	private static void removeContact(ContactBook cb, Scanner in) {
		System.out.println("Remover um contacto");
		System.out.println("Nome:");
		String name = in.nextLine();
		if(!cb.hasContact(name)) {
			cb.removeContact(name);	
			System.out.println(CONTACT_REMOVED);
		}else
			System.out.println(CANNOT_REMOVE);
	}
	
	private static void getContact(ContactBook cb, Scanner in) {
		System.out.println("Apresentar contacto");
		System.out.println("Nome:");
		String name = in.nextLine();
		if(!cb.hasContact(name)) {
			System.out.println("Email: " + cb.getEmail(name));
			System.out.println("Telemovel: " + cb.getPhone(name));
		}else
			System.out.println(NAME_NOT_EXIST);
	}
	
	private static String listContacts(ContactBook cb) {
		String result = "";
		Iterator cit = cb.Iterator();
		while(cit.hasNext()) {
			Contact c = cit.next();
			result += "\n" + c;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);		
		ContactBook cBook = new ContactBook();
		String cmd = null;
		/**
		Iterator cit = cb.Iterator();
		while (cit.hasNext()){
			Contact c = cit.next();
			System.out.println(c);
		}
		**/
		
		do { cmd = getCommand(in);
			if(!cmd.equals(QUIT)) {
				switch(cmd) {
				case ADD_CONTACT:
					addContact(cBook, in);
					break;
				case REMOVE_CONTACT:
					removeContact(cBook, in);
					break;
				case GET_CONTACT:
					getContact(cBook, in);
					break;
				/**case GET_EMAIL:
					getEmail(cBook, in);
					break;
				case GET_PHONE:
					getPhone(cBook, in);
					break;
				case SET_EMAIL:
					setEmail(cBook, in);
					break;
				case SET_PHONE:
					setPhone(cBook, in);
					break;
					**/
				case LIST_CONTACTS:
					System.out.println(listContacts(cBook));
					break;
				case HELP:
					help();
					break;
				
				}
			}
		}while(!cmd.equals(QUIT));
		
		System.out.println(BYE);
		in.close();
	}

}
