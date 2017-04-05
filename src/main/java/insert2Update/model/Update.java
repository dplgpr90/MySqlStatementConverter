package main.java.insert2Update.model;

public class Update implements Statement {

	public Column[] cols;

	public Value[] vals;

	public Target target;

	// Constructor
	public Update(Target target, Column[] cols, Value[] vals) {
		this.target = target;
		this.cols = cols;
		this.vals = vals;
	}

	public String toString() {
		String res = UPDATE + " " + target + SET + " ";
		for (int i = 0; i < cols.length; i++) {
			if (i > 0) {
				res += ", ";
			}
			res += cols[i] + " = " + vals[i];
		}
		res += ";";
		return res;
	}
}