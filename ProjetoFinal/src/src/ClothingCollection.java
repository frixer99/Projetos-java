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

	@Override
	public boolean hasProduct(String code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IClothing getProduct(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<IClothing> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int totalNumberOfItems() {
		return size();
	}

	@Override
	public double sumPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double discount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
