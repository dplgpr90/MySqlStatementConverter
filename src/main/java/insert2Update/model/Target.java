package main.java.insert2Update.model;

public class Target {

	public String schema;

	public String table;

	// Constructor
	public Target(String schema, String table) {
		this.schema = schema;
		this.table = table;
	}

	public String toString() {
		if (schema == null || schema.equals("")) {
			return table;
		}
		return schema + "." + table;
	}

}