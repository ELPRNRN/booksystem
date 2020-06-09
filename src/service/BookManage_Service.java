package service;

import javax.swing.JOptionPane;

import sqlTools.AuthorTools;
import sqlTools.BookTools;
import sqlTools.BorrowTools;
import sqlTools.PublisherTools;

public class BookManage_Service 
{
	BorrowTools borrowtools = new BorrowTools();
	BookTools bookTools = new BookTools();
	AuthorTools authorTools = new AuthorTools();
	PublisherTools publisherTools = new PublisherTools();
	
	String BookWhetherInStock(String idBook)
	{
		if (borrowtools.whetherInStock(temp.getIdBook()) == true) 
			return "ÔÚ¿â"; 
		else 
			return "½è³ö";
	}
	
	boolean UpdateBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.UpdatePublisher(publisher);
		publisherTools.AddPublisher(publisher);		
		authorTools.UpdateAuthor(author);
		authorTools.AddAuthor(author);
		
		int i = bookTools.UpdateBook(book);
		if ( i == 1) 
            return true;
		 else 
			return false;
	}
	
	boolean RegisterBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.AddPublisher(publisher);
		authorTools.AddAuthor(author);
		
		int i = bookTools.AddBook(book);
		if (i == 1) 
			return true";
		else 
			return false;
	}
	
	boolean DeleteBook(String idBook)
	{
		int i = bookTools.DeleteBook(idBook);
		if (i == 1) 
			return true;
		else
			return false;
	}
}
