package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

import common.ClothingKind;
import common.IClothing;
import common.IClothingCollection;
import common.OrderRegion;
import tiposRoupa.BasicClothing;
import tiposRoupa.RangedClothing;
import tiposRoupa.RegionalClothing;

/**
 * @author Pedro Veiga e Ricardo Castelhano
 *
 */
public class Main {

	public static final String BASIC_FILE_PATH = ClothingKind.BASIC.filePath();
	public static final String RANGED_FILE_PATH = ClothingKind.RANGED.filePath();
	public static final String REGIONAL_FILE_PATH = ClothingKind.REGIONAL.filePath();

	public static IClothingCollection clothes;
	public static IClothingCollection order;

	public static int encomendasConcretizadas = 0;
	public static double montanteTotal = 0;

	public static void main(String[] args) {
		clothes = new ClothingCollection();
		order = new ClothingCollection();
		int[] dataSimple = load(clothes, BASIC_FILE_PATH);
		int[] dataRanged = load(clothes, RANGED_FILE_PATH);
		int[] dataRegional = load(clothes, REGIONAL_FILE_PATH);

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
					ltp();
					break;
				case (Constants.STAT):
					printDataResults(dataSimple, dataRanged, dataRegional);
					break;
				case (Constants.ALE):
					ale(scan);
					break;
				case (Constants.EAE):
					eae();
					break;
				case (Constants.CE):
					ce();
					break;
				case (Constants.LLMB):
					llmb(scan);
					break;
				default:
					System.out.println("Comando não existe.");
				}
				System.out.print("Comando: ");
				command = scan.nextLine().toUpperCase();
			}

			System.out.println(encomendasConcretizadas + " encomendas concretizadas.");
			System.out.println("Montante total das encomendas: " + montanteTotal + ".");
			System.out.println("Sessão terminada. Obrigado pela sua preferência.");

			scan.close();

		}

	}

	private static int[] load(IClothingCollection clothes, String file) {
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

					if (file.equals(ClothingKind.BASIC.filePath())) {
						piece = simpleProcess(line);
						clothes.add(piece);
					} else if (file.equals(ClothingKind.RANGED.filePath())) {
						piece = rangedProcess(line);
						clothes.add(piece);
					} else if (file.equals(ClothingKind.REGIONAL.filePath())) {
						piece = regionalProcess(line);
						clothes.add(piece);
					} else {
						piece = null;
					}

					countAdd++;

				} catch (Exception e) {
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
			System.out.println("Ficheiro " + BASIC_FILE_PATH + " não encontrado.");
			result = false;
		}
		if (dataRanged[0] == -1) {
			System.out.println("Ficheiro " + RANGED_FILE_PATH + " não encontrado.");
			result = false;
		}
		if (dataRegional[0] == -1) {
			System.out.println("Ficheiro " + REGIONAL_FILE_PATH + " não encontrado.");
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
		System.out.println("S    Termina execução e sai.");
		System.out.println("HELP Lista elenco de comandos.");
		System.out.println("H    Lista elenco de comandos.");
		System.out.println("LTP  Listar Todos os Produtos.");
		System.out.println("STAT Contagens globais dos dados.");
		System.out.println("ALE  Acrescentar Lote à Encomenda.");
		System.out.println("CE   Concretizar a Encomenda.");
		System.out.println("LLMB Localiza Lote Mais Barato.");
		System.out.println();
	}

	private static void ltp() {
		Iterator<IClothing> itr = clothes.iterator();

		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	private static void ale(Scanner scan) {
		IClothing elem = null;
		ClothingKind pricingKind = null;
		String region = "EU";
		boolean admissible = false;

		System.out.print("Código do produto: ");
		String code = scan.nextLine();
		System.out.print("Quantidade de peças " + code + ": ");
		int nrItems = 0;
		try {
			nrItems = scan.nextInt();
			scan.nextLine();

			if (clothes.hasProduct(code)) {
				admissible = true;
				elem = clothes.getProduct(code);
				elem.setNrItemsOrdered(nrItems);
				pricingKind = elem.pricingKind();
			} else {
				System.out.println("Produto " + code + "não encontrado");
			}

		} catch (Exception e) {
			System.out.println(Constants.QUANT_ERROR);
			scan.nextLine();
		}

		if (admissible) {
			if (pricingKind.equals(ClothingKind.REGIONAL)) {
				System.out.println("Lote é do tipo regional");
				System.out.print("Zona de envio (EU, NEU, WW): ");
				region = scan.next();
				scan.nextLine();
				OrderRegion orderR = RegionalOrder(region);
				elem.setOrderRegion(orderR);
				System.out.print(elem.nrItemsOrdered() + " unidades do produto " + elem.getCode() + " para a região "
						+ elem.orderRegion() + " totalizando o montante de " + elem.orderPrice()
						+ "\nRegistar lote (S/N)? ");
				String answer = scan.next().toUpperCase();
				scan.nextLine();
				if (answer.equals("S")) {
					order.add(elem);
					System.out.println("Encomenda de lote " + elem.nrItemsOrdered() + " unidades do produto "
							+ elem.getCode() + " para a região " + elem.orderRegion() + " registada com sucesso.");
				} else {
					System.out.println("Operação cancelada.");
				}
			} else { // pricingKind.equals(ClothingKind.BASIC) ||
					 // pricingKind.equals(ClothingKind.RANGED)
				System.out.print(elem.nrItemsOrdered() + " unidades do produto " + elem.getCode()
						+ " totalizando o montante de " + elem.orderPrice() + "\nRegistar lote (S/N)? ");
				String answer = scan.next().toUpperCase();
				scan.nextLine();
				if (answer.equals("S")) {
					order.add(elem);
					System.out.println("Encomenda de lote " + elem.nrItemsOrdered() + " unidades do produto "
							+ elem.getCode() + " registada com sucesso.");
				} else {
					System.out.println("Operação cancelada.");
				}
			}
		}
	}

	private static OrderRegion RegionalOrder(String region) {
		switch (region.toUpperCase()) {
		case (Constants.EU):
			return OrderRegion.toOrderRegion("EU");
		case (Constants.NEU):
			return OrderRegion.toOrderRegion("NEAR-EU");
		case (Constants.WW):
			return OrderRegion.toOrderRegion("WORLDWIDE");
		default:
			return OrderRegion.toOrderRegion("EU");
		}
	}

	private static void eae() {
		System.out.println("Encomenda atual com " + order.size() + " lotes, num total de " + order.totalNumberOfItems()
						   + " peças e totalizando o montante de " + order.totalPrice());
	}

	private static void ce() {
		if (order.isEmpty()) {
			System.out.println("Encomenda vazia. Não é possível concretizar.");
		} else {
			eae();
			System.out.println("Concretizada com sucesso.");
			encomendasConcretizadas++;
			montanteTotal += order.totalPrice();

			order = new ClothingCollection();
		}
	}

	private static void llmb(Scanner scan) {
		System.out.print("Quantidade a usar na pesquisa: ");
		try {
			int quantidade = scan.nextInt();
			scan.nextLine();
			if (quantidade <= 0) {
				System.out.println("Este comando não funciona para quantidades zero ou  negativas");
			} else {
				double lower = Double.POSITIVE_INFINITY;
				double price;
				IClothing lowerProd = null;
				Iterator<IClothing> itr = clothes.iterator();

				while (itr.hasNext()) {
					IClothing elem = itr.next();
					ClothingKind region = elem.pricingKind();
					elem.setNrItemsOrdered(quantidade);

					if (region.equals(ClothingKind.REGIONAL)) {
						price = lowerPriceRegional(elem);

					} else {
						price = elem.orderPrice();
					}

					if (price < lower) {
						lower = price;
						lowerProd = elem;
					}
				}
				System.out.println("Para a quantidade especificada, o lote mais barato é:\n" + lowerProd);
				System.out.println("Quantidade: " + quantidade + " corresponde a um custo total de " + lower);

			}

		} catch (Exception e) {
			System.out.println(Constants.QUANT_ERROR);
			scan.nextLine();
		}
	}

	private static double lowerPriceRegional(IClothing piece) {
		double lower = Double.POSITIVE_INFINITY;
		double price;

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

		return lower;
	}

}
