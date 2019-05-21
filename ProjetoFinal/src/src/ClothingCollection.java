package src;

import java.util.Iterator;

import common.IClothing;
import common.IClothingCollection;

public class ClothingCollection implements IClothingCollection {

	public static final int DEFAULT_SIZE = 50;
	public static final int GROWTH = 2;
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
		Iterator<IClothing> iterator = iterator();
		boolean found = false;
		
		while(iterator.hasNext() && !found) {
			clothing = iterator.next();
			if( clothing.getCode().equals(code) ) {
				found = true;
			}	
		}
		return clothing;
	}

	public Iterator<IClothing> iterator() {	
		ClothingIterator itr = new ClothingIterator(clothes, counter);
		return itr;
	}

	/**
	 * ONLY ORDER METHODS
	 */
	
	public int totalNumberOfItems() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double sumPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double totalPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double discount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
