package test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.Author;
import model.Book;
import model.Borrow;
import model.Publisher;
import model.Reader;
import service.BookManage_Service;
import service.BookSearch_Service;
import service.LoginLogout_Service;
import service.ReaderManage_Service;
import service.ReturnBorrow_Service;
import sqlTools.BookTools;
import sqlTools.BorrowTools;

public class FunctionTest 
{
	 public static void main(String[] args) 
	 {
	    	 ReturnBorrow_Service service= ReturnBorrow_Service.getInstance();
	    	// String temp=service.
	    	 

	   
		    // System.out.println(temp);
	 }
	 
}
