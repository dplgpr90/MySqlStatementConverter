package model;

public class Update implements Statement {

	public Column[] cols;
	
	public Value[] vals;
	
	// Constructor
	public Update(Column[] cols, Value[] vals){
		this.cols = cols;
		this.vals = vals;
	}
	
}