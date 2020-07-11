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
	    	 BookManage_Service service= BookManage_Service.getInstance();
	    	 Book book=new Book("012","三国演义",23,"文学","罗贯中","中国人民出版社","三国",5);
	    	 Author author =new Author("罗贯中","中国");
	    	 Publisher publisher=new Publisher("中国人民出版社","北京");
	    	 boolean temp=service.RegisterBook(book, author, publisher);
	    	 System.out.println(temp);
	    	 
	 }
	 
}
