package model;

public class TableData {
	public String text;
	public String color;
	
	// Constructor
	public TableData(String t) {
		text = t;
	}
	
	/* Ex3 */
	// This method returns the html string representing table data of a row of the table
	public String toHTML(){
		String col = color == null ? "" : " style=\"background: " + color + "\""; // insert style attribute if color is defined 
		return "<td" + col + ">" + text + "</td>";
	}
}
