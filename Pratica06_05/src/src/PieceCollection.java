package src;

public abstract class PieceCollection{
	public static final int DEFAULT_SIZE = 50;
	public static final int GROWTH = 2;
	protected int counter;
	protected AbstractPiece[] pieces;
	
	public PieceCollection() {
		counter = 0;
		pieces = new AbstractPiece[DEFAULT_SIZE];
	}
	
	public boolean add(AbstractPiece elem) {
		if(elem == null ) //|| contains(elem)
			return false;
		if(isFull())
			resize();
		pieces[counter++] = elem; 
		return true;
	}
	
	private boolean isFull() {
		return counter == pieces.length;
	}
	
	private void resize() {
		AbstractPiece[] newArray = new AbstractPiece[pieces.length * GROWTH];
		for(int i = 0; i < counter; i++)
			newArray[i] = pieces[i];
		pieces = newArray;		
	}
	
	public int size() {
		return counter;
	}
	
	
}
