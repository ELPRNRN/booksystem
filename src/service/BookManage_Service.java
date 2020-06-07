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
			return "在库"; 
		else 
			return "借出";
	}
	
	void updateBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.UpdatePublisher(publisher);
		publisherTools.AddPublisher(publisher);		
		authorTools.UpdateAuthor(author);
		authorTools.AddAuthor(author);
		
		int i = bookTools.UpdateBook(book);
		if ( i == 1) 
            return "成功更新图书信息！";
		 else 
			return "更新图书信息失败！";
	}
}
