package service;


import model.Author;
import model.Book;
import model.Publisher;
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
    private static BookManage_Service BookManage_Service_Instance = new BookManage_Service();
	
	//单例
	public static BookManage_Service getInstance() {
		return BookManage_Service_Instance;
	}
	private BookManage_Service() {};
	
	//查看书本是否在库
	String BookWhetherInStock(String idBook)
	{
		if (borrowtools.whetherInStock(idBook) == true) 
			return "在库"; 
		else 
			return "借出";
	}
	
	//更新图书相关信息
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
	
	//书本注册入库
	boolean RegisterBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.AddPublisher(publisher);
		authorTools.AddAuthor(author);
		
		int i = bookTools.AddBook(book);
		if (i == 1) 
			return true;
		else 
			return false;
	}
	
	//删除书本
	boolean DeleteBook(String idBook)
	{
		int i = bookTools.DeleteBook(idBook);
		if (i == 1) 
			return true;
		else
			return false;
	}
	
	
}
