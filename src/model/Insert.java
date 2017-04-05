package model;

public class Insert implements Statement {

	public Column[] cols;
	
	public Value[] vals;

	// Constructor
	public Insert(Column[] cols, Value[] vals) {
		this.cols = cols;
		this.vals = vals;
	}

}