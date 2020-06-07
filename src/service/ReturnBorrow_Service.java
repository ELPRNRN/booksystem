package service;
import javax.swing.JOptionPane;

import sqlTools.BorrowTools;

public class ReturnBorrow_Service 
{
	Borrowtools borrowtools =new Borrowtools();
	
	String BorrowBook(String idBook)
	{
		String idReader=LoginLogout_Service.idReader;
		int i = borrowtools.BorrowBook(idReader, idbook);
		if (i == 1) 
			return "借阅成功";
		 else 
			return "借阅失败";
	}
	
	String ReturnBook(String idBook)
	{
		int i = borrowtools.ReturnBook(idBook);
		if (i == 1) 
			return "还书成功";
		else 
			return "还书失败";
	}
	
}
