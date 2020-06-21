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
	
	
	//查看书本是否在库（通过书号查找）
	public String BookWhetherInStock(String idBook)
	{
		if (borrowtools.whetherInStock(idBook) == true) 
			return "在库"; 
		else 
			return "借出";
	}
	
	
	
	//更新图书相关信息，参数为book、author、publisher对象，三者所有信息均不能为空（前端判断）
	public boolean UpdateBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.AddUpdatePublisher(publisher);	
		authorTools.AddUpdateAuthor(author);
		
		int i = bookTools.UpdateBook(book);
		if ( i == 1) 
            return true;//更新成功
		 else 
			return false;//更新失败
	}
	
	//书本注册入库，参数为book、author、publisher对象，三者所有信息均不能为空（前端判断）
	public boolean RegisterBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.AddUpdatePublisher(publisher);
		authorTools.AddUpdateAuthor(author);
		
		int i = bookTools.AddBook(book);
		if (i == 1) 
			return true;//注册成功
		else 
			return false;//注册失败,书号已存在
	}
	
	//删除书本（通过书号查找）
	public boolean DeleteBook(String idBook)
	{
		int i = bookTools.DeleteBook(idBook);
		if (i == 1) 
			return true;//删除成功
		else
			return false;//删除失败
	}
	
	
}
