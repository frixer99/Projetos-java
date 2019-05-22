package src;

import java.util.Iterator;

import common.IClothing;
import common.IClothingCollection;

public class ClothingCollection implements IClothingCollection {

	public static final int DEFAULT_SIZE = 50;
	public static final int GROWTH = 2;
	public static final int TIER1 = 10000;
	public static final int TIER2 = 50000;
	public static final int TIER3 = 100000;
	public static final int TIER4 = 500000;	
	
	protected int counter;
	protected IClothing[] clothes;
	
	public ClothingCollection() {
		counter = 0;
		clothes = new IClothing[DEFAULT_SIZE];
	}
	
	public void add(IClothing elem) {			
		if(isFull())
			resize();
		clothes[counter++] = elem; 
	}
	
	private boolean isFull() {
		return counter == clothes.length;
	}
	
	private void resize() {
		IClothing[] newArray = new IClothing[clothes.length * GROWTH];
		for(int i = 0; i < counter; i++)
			newArray[i] = clothes[i];
		clothes = newArray;		
	}
	
	public int size() {
		return counter;
	}
	
	public boolean isEmpty() {
		return counter == 0;
	}

	public boolean hasProduct(String code) {
		IClothing clothing;
		Iterator<IClothing> iterator = iterator();
		boolean found = false;
		
		while(iterator.hasNext() && !found) {
			clothing = iterator.next();
			if( clothing.getCode().equals(code) ) {
				found = true;
			}	
		}
		return found;
	}

	public IClothing getProduct(String code) {
		IClothing clothing = null;
		Iterator<IClothing> clothingIterator = iterator();
		boolean found = false;
		
		while(clothingIterator.hasNext() && !found) {
			clothing = clothingIterator.next();
			if( clothing.getCode().equals(code) ) {
				found = true;
			}	
		}
		return clothing;
	}

	public Iterator<IClothing> iterator() {	
		Iterator<IClothing> itr = new ClothingIterator(clothes, counter);
		return itr;
	}

	/**
	 * ONLY ORDER METHODS
	 */
	
	public int totalNumberOfItems() {
		Iterator<IClothing> clothingIterator = iterator();
		int totalItems = 0;
		
		while(clothingIterator.hasNext()) {
			totalItems += clothingIterator.next().nrItemsOrdered();
		}
		return totalItems;
	}

	public double sumPrice() {
		Iterator<IClothing> clothingIterator = iterator();
		double totalPrice = 0;
		
		while(clothingIterator.hasNext()) {
			totalPrice += clothingIterator.next().orderPrice();
		}
		return totalPrice;
	}

	public double totalPrice() {
		return sumPrice() * (1 - discount());
	}

	public double discount() {
		if(totalNumberOfItems() <= TIER1) {
			return 0.00;
		}else if(totalNumberOfItems() <= TIER2) {
			return 1;
		}else if(totalNumberOfItems() <= TIER3) {
			return 2;
		}else if(totalNumberOfItems() <= TIER4) {
			return 3;
		}else {
			return 3.5;
		}
	}
	
}
