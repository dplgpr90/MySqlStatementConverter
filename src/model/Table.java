package model;

public class Table {
	public Row[] rows;
	
	// Constructor
	public Table(Row[] rows){
		this.rows = rows;
	}
	
	/* Ex3 */
	// This method returns the html string representing the html file containing the table
	public String toHTML(){
		String rowsHTML = "";
		for(Row r : rows)
			rowsHTML += r.toHTML();
		return "<html><body><table>" + rowsHTML + "</table></body></html>";
	}
}