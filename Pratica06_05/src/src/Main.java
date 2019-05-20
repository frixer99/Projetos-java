package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	public static final String BASIC_FILE = "basic.csv";
	public static final String RANGED_FILE = "ranged.csv";
	public static final String REGIONAL_FILE = "regional.csv";

	public static void main(String[] args) {
		PieceCollection simplePieces = new SimpleCollection();
		int[] dataSimple = load(simplePieces, BASIC_FILE);
		int[] dataRanged = { 1, -1 };
		int[] dataRegional = { 1, -1 };
		
		boolean fileresults = printFileResults(dataSimple, dataRanged, dataRegional);
		if (fileresults) {
			printDataResults(dataSimple, dataRanged, dataRegional);

			Scanner scan = new Scanner(System.in);
			System.out.print("Comando: ");
			String command = scan.nextLine().toUpperCase();
			while (!command.equals(Constants.SAIR) && !command.equals(Constants.S)) {
				switch (command) {
				case (Constants.HELP):
				case (Constants.H):
					help();
					break;
				case (Constants.LTP):
					listAll(simplePieces);
					break;
				case (Constants.STAT):
					printDataResults(dataSimple, dataRanged, dataRegional);
					break;
				case (Constants.ALE):

					break;
				case (Constants.CE):

					break;
				case (Constants.LLMB):

					break;
				default:
					System.out.println("Comando não existe.");
				}
				System.out.print("Comando: ");
				command = scan.nextLine().toUpperCase();
			}
			scan.close();
		}

	}

	private static void help() {
		System.out.println("SAIR Termina execução e sai.");
		System.out.println("S Termina execução e sai.");
		System.out.println("HELP Lista elenco de comandos.");
		System.out.println("H Lista elenco de comandos.");
		System.out.println("LTP Listar Todos os Produtos.");
		System.out.println("STAT Contagens globais dos dados.");
		System.out.println("ALE Acrescentar Lote à Encomenda.");
		System.out.println("CE Concretizar a Encomenda.");
		System.out.println("LLMB Localiza Lote Mais Barato.");
		System.out.println();
	}

	private static void printDataResults(int[] dataSimple, int[] dataRanged, int[] dataRegional) {
		System.out.println("Preço básico - produtos carregados: " + dataSimple[0]);
		System.out.println("Preço básico - registos com erros: " + dataSimple[1]);
		System.out.println("Preço por escalões - produtos carregados: " + dataRanged[0]);
		System.out.println("Preço por escalões - registos com erros: " + dataRanged[1]);
		System.out.println("Preço por regiões - produtos carregados: " + dataRegional[0]);
		System.out.println("Preço por regiões - registos com erros: " + dataRegional[1]);
		System.out.println(dataSimple[0] + dataRanged[0] + dataRegional[0] + " produtos carregados.\n");
	}

	private static boolean printFileResults(int[] dataSimple, int[] dataRanged, int[] dataRegional) {
		boolean result = true;
		if (dataSimple[0] == -1) {
			System.out.println("Ficheiro " + BASIC_FILE + " não encontrado.");
			result = false;
		}
		if (dataRanged[0] == -1) {
			System.out.println("Ficheiro " + RANGED_FILE + " não encontrado.");
			result = false;
		}
		if (dataRegional[0] == -1) {
			System.out.println("Ficheiro " + REGIONAL_FILE + " não encontrado.");
			result = false;
		}
		return result;
	}

	private static SimplePiece simpleProcess(String line) {
		String[] items = line.split(";");
		String[] generalData = generalProcess(items);
		double sendPrice = Double.parseDouble(items[7]);

		return new SimplePiece(generalData[0], generalData[1], Double.parseDouble(generalData[2]), generalData[3],
							   generalData[4], generalData[5], Double.parseDouble(generalData[6]), sendPrice);
	}
	
	private static SimplePiece rangedProcess(String line) {
		/**
		 * TODO CLASSE RANGEDPIECE
		 */
		
		/**
		String[] items = line.split(";");
		String[] generalData = generalProcess(items);
		double sendPrice = Double.parseDouble(items[7]);

		return new SimplePiece(generalData[0], generalData[1], Double.parseDouble(generalData[2]), generalData[3],
							   generalData[4], generalData[5], Double.parseDouble(generalData[6]), sendPrice);
		*/
		return null;
	}
	
	@SuppressWarnings("unused")
	private static SimplePiece regionalProcess(String line) { 
		/**
		 * TODO CLASSE REGIONALPIECE
		 * NO FIM TIRAR @SuppressWarnings("unused")
		 */
		/**
		String[] items = line.split(";");
		String[] generalData = generalProcess(items);
		double firstEU = Double.parseDouble(items[7]);
		double afterEU = Double.parseDouble(items[8]);
		double firstNEU = Double.parseDouble(items[9]);
		double afterNEU = Double.parseDouble(items[10]);
		double firstWW = Double.parseDouble(items[11]);
		double afterWW = Double.parseDouble(items[12]);
		*/
		return null;		
		
	}
	
	private static String[] generalProcess(String[] items) {
		String code = items[0];
		String description = items[1];
		String weight = items[2];
		String type = items[3];
		String color = items[4];
		String size = items[5];
		String unitPrice = items[6];
		String[] data = {code, description, weight, type, color, size, unitPrice};
		return data;
	}

	private static int[] load(PieceCollection collection, String file) {
		int countAdd = 0;
		int countError = 0;
		int[] data = { -1, -1 };
		AbstractPiece piece;
		try {
			Scanner scan = new Scanner(new FileReader(file));
			@SuppressWarnings("unused")
			String head = scan.nextLine();
			head = scan.nextLine();
			while (scan.hasNext()) {
				try {
					String line = scan.nextLine();
					switch (file) {
					case BASIC_FILE:
						piece = simpleProcess(line);
						break;
					case RANGED_FILE:
						piece = rangedProcess(line);
						break;
					case REGIONAL_FILE:
						piece = regionalProcess(line);
						break;
					default: // Nunca deve chegar aqui
						piece = null;
					}
					boolean res = collection.add(piece);
					if (res)
						countAdd++;
				} catch (Exception e) { // NumberFormatException?
					countError++;
				}
			}
			scan.close();
			data[0] = countAdd;
			data[1] = countError;

		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro " + file + "não encontrado.");
		}
		return data;
	}

	private static void listAll(PieceCollection collection) {
		
		
		
	}
		
}
