package service;
import javax.swing.JOptionPane;

import sqlTools.BorrowTools;

public class ReturnBorrow_Service 
{
	Borrowtools borrowtools =new Borrowtools();
	
	boolean BorrowBook(String idBook)
	{
		String idReader=LoginLogout_Service.idReader;
		int i = borrowtools.BorrowBook(idReader, idbook);
		if (i == 1) 
			return true;
		 else 
			return false;
	}
	
	boolean ReturnBook(String idBook)
	{
		int i = borrowtools.ReturnBook(idBook);
		if (i == 1) 
			return true;
		else 
			return false;
	}
	
}
