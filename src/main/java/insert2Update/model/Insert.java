package model;

public class Insert implements Statement {

	public Column[] cols;

	public Value[] vals;

	public Target target;

	// Constructor
	public Insert(Target target, Column[] cols, Value[] vals) {
		this.target = target;
		this.cols = cols;
		this.vals = vals;
	}

}