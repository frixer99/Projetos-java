package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

import common.IClothing;
import common.IClothingCollection;
import common.OrderRegion;
import tiposRoupa.BasicClothing;
import tiposRoupa.RangedClothing;
import tiposRoupa.RegionalClothing;

public class Main {

	public static final String BASIC_FILE = "basic.csv";
	public static final String RANGED_FILE = "ranged.csv";
	public static final String REGIONAL_FILE = "regional.csv";

	public static IClothingCollection simpleClothing;
	public static IClothingCollection rangedClothing;
	public static IClothingCollection regionalClothing;
	public static IClothingCollection order;

	public static void main(String[] args) {
		simpleClothing = new ClothingCollection();
		rangedClothing = new ClothingCollection();
		regionalClothing = new ClothingCollection();
		order = new ClothingCollection();
		int[] dataSimple = load(BASIC_FILE);
		int[] dataRanged = load(RANGED_FILE);
		int[] dataRegional = load(REGIONAL_FILE);
		boolean finish = false;

		boolean fileresults = printFileResults(dataSimple, dataRanged, dataRegional);
		if (fileresults) {
			printDataResults(dataSimple, dataRanged, dataRegional);
			Scanner scan = new Scanner(System.in);
			System.out.print("Comando: ");
			String command = scan.nextLine().toUpperCase();
			while (!command.equals(Constants.SAIR) && !command.equals(Constants.S) && !finish) {
				switch (command) {
				case (Constants.HELP):
				case (Constants.H):
					help();
					break;
				case (Constants.LTP):
					ltp();
					break;
				case (Constants.STAT):
					printDataResults(dataSimple, dataRanged, dataRegional);
					break;
				case (Constants.ALE):
					ale();
					break;
				case (Constants.EAE):
					eae();
					break;
				case (Constants.CE):
					finish = ce();
					break;
				case (Constants.LLMB):
					llmb();
					break;
				default:
					System.out.println("Comando não existe.");
				}

				if (!finish) {
					System.out.print("Comando: ");
					command = scan.nextLine().toUpperCase();
				}
			}
			scan.close();

		}

	}

	private static int[] load(String file) {
		int countAdd = 0;
		int countError = 0;
		int[] data = { -1, -1 };
		IClothing piece;

		try {
			Scanner scan = new Scanner(new FileReader(file));
			String line = scan.nextLine();
			line = scan.nextLine();
			while (scan.hasNextLine()) {
				try {
					line = scan.nextLine();
					switch (file) {
					case BASIC_FILE:
						piece = simpleProcess(line);
						simpleClothing.add(piece);
						break;
					case RANGED_FILE:
						piece = rangedProcess(line);
						rangedClothing.add(piece);
						break;
					case REGIONAL_FILE:
						piece = regionalProcess(line);
						regionalClothing.add(piece);
						break;
					default: // Nunca deve chegar aqui
						piece = null;
					}
					countAdd++;

				} catch (Exception e) { // NumberFormatException?
					countError++;
				}
			}
			scan.close();
			data[0] = countAdd;
			data[1] = countError;

		} catch (FileNotFoundException e) {

		}
		return data;
	}

	private static String[] generalProcess(String[] items) {
		String code = items[0];
		String description = items[1];
		String weight = items[2];
		String type = items[3];
		String color = items[4];
		String size = items[5];
		String unitPrice = items[6];
		String[] data = { code, description, weight, type, color, size, unitPrice };
		return data;
	}

	private static BasicClothing simpleProcess(String line) {
		String[] items = line.split(";");
		String[] generalData = generalProcess(items);
		double sendPrice = Double.parseDouble(items[7]);

		return new BasicClothing(generalData[0], generalData[1], Double.parseDouble(generalData[2]), generalData[3],
				generalData[4], generalData[5], Double.parseDouble(generalData[6]), sendPrice);
	}

	private static RangedClothing rangedProcess(String line) {
		String[] items = line.split(";");
		String[] generalData = generalProcess(items);
		double sendPrice = Double.parseDouble(items[7]);

		return new RangedClothing(generalData[0], generalData[1], Double.parseDouble(generalData[2]), generalData[3],
				generalData[4], generalData[5], Double.parseDouble(generalData[6]), sendPrice);
	}

	private static RegionalClothing regionalProcess(String line) {
		String[] items = line.split(";");
		String[] generalData = generalProcess(items);
		double firstEU = Double.parseDouble(items[7]);
		double afterEU = Double.parseDouble(items[8]);
		double firstNEU = Double.parseDouble(items[9]);
		double afterNEU = Double.parseDouble(items[10]);
		double firstWW = Double.parseDouble(items[11]);
		double afterWW = Double.parseDouble(items[12]);

		return new RegionalClothing(generalData[0], generalData[1], Double.parseDouble(generalData[2]), generalData[3],
				generalData[4], generalData[5], Double.parseDouble(generalData[6]), firstEU, afterEU, firstNEU,
				afterNEU, firstWW, afterWW);
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

	private static void printDataResults(int[] dataSimple, int[] dataRanged, int[] dataRegional) {
		System.out.println("Preço básico - produtos carregados: " + dataSimple[0]);
		System.out.println("Preço básico - registos com erros: " + dataSimple[1]);
		System.out.println("Preço por escalões - produtos carregados: " + dataRanged[0]);
		System.out.println("Preço por escalões - registos com erros: " + dataRanged[1]);
		System.out.println("Preço por regiões - produtos carregados: " + dataRegional[0]);
		System.out.println("Preço por regiões - registos com erros: " + dataRegional[1]);
		System.out.println(dataSimple[0] + dataRanged[0] + dataRegional[0] + " produtos carregados.\n");
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

	private static void ltp() {
		listCollection(simpleClothing);
		listCollection(rangedClothing);
		listCollection(regionalClothing);
	}

	private static void listCollection(IClothingCollection coll) {
		Iterator<IClothing> itr = coll.iterator();

		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

	}

	private static void ale() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		IClothing elem = null;
		String admissible = "";
		String region = "EU";

		System.out.print("Código do produto: ");
		String code = scan.nextLine();
		System.out.print("Quantidade de peças " + code + ": ");
		int nrItems = 0;
		try {
			nrItems = scan.nextInt();

			if (simpleClothing.hasProduct(code)) {
				elem = addBasicOrder(elem, code, nrItems);
				admissible = "basic";

			} else if (rangedClothing.hasProduct(code)) {
				elem = addRangedOrder(elem, code, nrItems);
				admissible = "ranged";

			} else if (regionalClothing.hasProduct(code)) {
				System.out.println("Lote é do tipo regional");
				System.out.print("Zona de envio (EU, NEU, WW): ");
				region = scan.next();

				elem = addRegionalOrder(region, elem, code, nrItems);
				admissible = "regional";

			} else {
				System.out.println("Produto " + code + " não encontrado.");
			}

		} catch (Exception e) {
			System.out.println(Constants.QUANT_ERROR);
		}

		if (admissible.equals("regional")) {
			System.out.println(elem.nrItemsOrdered() + " unidades do produto " + elem.getCode() + " para a região " + elem.orderRegion()
					+ " totalizando o montante de " + elem.orderPrice() + "\nRegistar lote (S/N)? ");
			String answer = scan.next().toUpperCase();
			if (answer.equals("S")) {
				order.add(elem);
				System.out.println("Encomenda de lote " + elem.nrItemsOrdered() + " unidades do produto " + elem.getCode() + " para a região "
						+ elem.orderRegion() + " registada com sucesso.");
			} else {
				System.out.println("Operação cancelada.");
			}
		} else if(admissible.equals("basic") || admissible.equals("ranged")) {
			System.out.println(elem.nrItemsOrdered() + " unidades do produto " + elem.getCode() + " totalizando o montante de " 
								+ elem.orderPrice() + "\nRegistar lote (S/N)? ");
			String answer = scan.next().toUpperCase();
			if (answer.equals("S")) {
				order.add(elem);
				System.out.println("Encomenda de lote " + elem.nrItemsOrdered() + " unidades do produto " + elem.getCode() + " registada com sucesso.");
			} else {
				System.out.println("Operação cancelada.");
			}

		}
	}

	private static IClothing addBasicOrder(IClothing elem, String code, int nrItems) {
		System.out.println("Lote é do tipo simples");
		elem = simpleClothing.getProduct(code);
		elem.setNrItemsOrdered(nrItems);
		return elem;
	}

	private static IClothing addRangedOrder(IClothing elem, String code, int nrItems) {
		System.out.println("Lote é do tipo por escalão");
		elem = rangedClothing.getProduct(code);
		elem.setNrItemsOrdered(nrItems);
		return elem;
	}

	private static IClothing addRegionalOrder(String region, IClothing elem, String code, int nrItems) {
		elem = regionalClothing.getProduct(code);
		elem.setNrItemsOrdered(nrItems);

		switch (region.toUpperCase()) {
		case (Constants.EU):
			elem.setOrderRegion(OrderRegion.toOrderRegion("EU"));
			break;
		case (Constants.NEU):
			elem.setOrderRegion(OrderRegion.toOrderRegion("NEAR-EU"));
			break;
		case (Constants.WW):
			elem.setOrderRegion(OrderRegion.toOrderRegion("WORLDWIDE"));
			break;
		default:
			elem.setOrderRegion(OrderRegion.toOrderRegion("EU"));
			break;
		}
		return elem;
	}

	private static void eae() {
		System.out.println("Encomenda atual com " + order.size() + " lotes, num total de " + order.totalNumberOfItems()
				+ " peças e totalizando o montante de " + order.totalPrice());
	}

	private static boolean ce() {
		if (order.isEmpty()) {
			System.out.println("Encomenda vazia. Não é possível concretizar.");
			return false;
		} else {
			eae();
			System.out.println("Concretizada com sucesso.");
			return true;
		}
	}

	private static void llmb() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("Quantidade a usar na pesquisa: ");
		try {
			int quantidade = scan.nextInt();
			scan.nextLine();
			if (quantidade <= 0) {
				System.out.println("Este comando não funciona para quantidades zero ou  negativas");
			} else {
				double lower = Double.POSITIVE_INFINITY;
				double lower1 = lowerPriceBasic(quantidade);
				double lower2 = lowerPriceRanged(quantidade);
				double lower3 = lowerPriceRegional(quantidade);
				String region = "";

				if (lower1 < lower2 && lower1 < lower3) {
					lower = lower1;
					region = "Básico";
				} else if (lower2 < lower1 && lower2 < lower3) {
					lower = lower2;
					region = "Por escalões";
				} else if (lower3 < lower1 && lower3 < lower2) {
					lower = lower3;
					region = "Regional";
				}

				System.out.println("Para a quantidade especificada, o lote mais barato é: " + region);
				System.out.println("Quantidade:" + quantidade + " corresponde a um custo total de " + lower);

			}

		} catch (Exception e) {
			System.out.println(Constants.QUANT_ERROR);
		}
	}

	@SuppressWarnings("unused")
	private static double lowerPrice(IClothingCollection coll, double lower) {
		Iterator<IClothing> itr = coll.iterator();

		while (itr.hasNext()) {
			double price = itr.next().orderPrice();
			if (price < lower)
				lower = price;
		}
		return lower;
	}

	private static double lowerPriceBasic(int quantidade) {
		double lower = Double.POSITIVE_INFINITY;
		Iterator<IClothing> itr = simpleClothing.iterator();
		while (itr.hasNext()) {
			IClothing piece = itr.next();
			piece.setNrItemsOrdered(quantidade);
			double price = piece.orderPrice();
			if (price < lower)
				lower = price;
		}
		return lower;
	}

	private static double lowerPriceRanged(int quantidade) {
		double lower = Double.POSITIVE_INFINITY;
		Iterator<IClothing> itr = rangedClothing.iterator();
		while (itr.hasNext()) {
			IClothing piece = itr.next();
			piece.setNrItemsOrdered(quantidade);
			double price = piece.orderPrice();
			if (price < lower)
				lower = price;
		}
		return lower;
	}

	private static double lowerPriceRegional(int quantidade) {
		double lower = Double.POSITIVE_INFINITY;
		Iterator<IClothing> itr = regionalClothing.iterator();
		double price;
		while (itr.hasNext()) {
			IClothing piece = itr.next();
			piece.setNrItemsOrdered(quantidade);

			piece.setOrderRegion(OrderRegion.EU);
			price = piece.orderPrice();
			if (price < lower)
				lower = price;
			piece.setOrderRegion(OrderRegion.NEAR_EU);
			price = piece.orderPrice();
			if (price < lower)
				lower = price;
			piece.setOrderRegion(OrderRegion.WORLDWIDE);
			price = piece.orderPrice();
			if (price < lower)
				lower = price;
		}
		return lower;
	}

}
