package src;

import java.util.Iterator;

import common.IClothing;

/**
 * @author Pedro Veiga e Ricardo Castelhano
 *
 */
public class ClothingIterator implements Iterator<IClothing>{
	
	private IClothing[] clothes;
	private int counter;
	private int currentClothing;
	
	public ClothingIterator(IClothing[] clothes, int counter) {
		this.clothes = clothes;
		this.counter = counter;
		currentClothing = 0;
	}
	
	public boolean hasNext() {
		return currentClothing < counter;
	}
	
	public IClothing next() {
		return clothes[currentClothing++];
	}
}
