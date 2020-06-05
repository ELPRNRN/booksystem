package service;
import javax.swing.JOptionPane;

import sqlTools.BorrowTools;

public class ReturnBorrow_Service 
{
	Borrowtools borrowtools =new Borrowtools();
	
	String BorrowBook(String idBook)
	{
		String idReader=Login_Service.idReader;
		int i = borrowtools.BorrowBook(idReader, idbook);
		if (i == 1) 
			return "ΩË‘ƒ≥…π¶";
		 else 
			return "ΩË‘ƒ ß∞‹";
	}
	
	
}
