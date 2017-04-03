package model;

public class Row {
	public TableData[] tds;
	
	// Constructor
	public Row(TableData[] tds) {
		this.tds = tds;
	}
	
	/* Ex3 */
	// This method returns the html string representing a row of the table
	public String toHTML(){
		String tdsHTML = "";
		for(TableData td : tds)
			tdsHTML += td.toHTML();
		return "<tr>" + tdsHTML + "</tr>";
	}
}
