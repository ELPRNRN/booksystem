package service;

import sqlTools.BorrowTools;

public class BookManage_Service 
{
	BorrowTools borrowtools = new BorrowTools();
	
	String BookWhetherInStock(String idBook)
	{
		if (borrowtools.whetherInStock(temp.getIdBook()) == true) 
			return "ÔÚ¿â"; 
		else 
			return "½è³ö";
	}
	
	
}
