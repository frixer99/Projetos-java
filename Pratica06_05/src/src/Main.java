package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	public static final String BASIC_FILE = "basic.csv";
	public static final String RANGED_FILE = "ranged.csv";
	public static final String REGIONAL_FILE = "regional.csv";

	public static void main(String[] args) {
		PieceCollection simplePieces= new SimpleCollection();
		load(simplePieces, BASIC_FILE);
		

	}

	private static SimplePiece simpleProcess(String line) {
		String[] items = line.split(";");
		String code = items[0];
		String description = items[1];
		double weight = Double.parseDouble(items[2]);
		String type = items[3];
		String color = items[4];
		String size = items[5];
		double unitPrice = Double.parseDouble(items[6]);
		double sendPrice = Double.parseDouble(items[7]);
		return new SimplePiece(code, description, weight, type, color, size, unitPrice, sendPrice);
	}

	private static void load(PieceCollection collection,String file) {
		int countAdd = 0;
		int countError = 0;
		AbstractPiece piece;
		try {
			Scanner scan = new Scanner(new FileReader(file));
			@SuppressWarnings("unused")
			String head = scan.nextLine();
			head = scan.nextLine();
			while (scan.hasNext()) {
				try {
					String line = scan.nextLine();
					piece = simpleProcess(line); //TODO passar para switch para funcionar com outros tipos
					boolean res = collection.add(piece);
					if(res)
						countAdd++;
				} catch (Exception e) { //NumberFormatException?
					//System.out.println(e);
					countError++;
				}
			}
			scan.close();
			System.out.println("Foram inseridos " + countAdd + " produtos");
			System.out.println("Houve erro em " + countError + " produtos");

		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel aceder o ficheiro " + file);
		}
		
	}

}
