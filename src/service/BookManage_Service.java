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
	
	//����
	public static BookManage_Service getInstance() {
		return BookManage_Service_Instance;
	}
	private BookManage_Service() {};
	
	//�鿴�鱾�Ƿ��ڿ�
	public String BookWhetherInStock(String idBook)
	{
		if (borrowtools.whetherInStock(idBook) == true) 
			return "�ڿ�"; 
		else 
			return "���";
	}
	
	//����ͼ�������Ϣ
	public boolean UpdateBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.AddUpdatePublisher(publisher);
		//publisherTools.AddPublisher(publisher);		
		authorTools.AddUpdateAuthor(author);
		//authorTools.AddAuthor(author);
		
		int i = bookTools.UpdateBook(book);
		if ( i == 1) 
            return true;
		 else 
			return false;
	}
	
	//�鱾ע�����
	public boolean RegisterBook(Book book,Author author,Publisher publisher)
	{
		//publisherTools.AddPublisher(publisher);
		publisherTools.AddUpdatePublisher(publisher);
		//authorTools.AddAuthor(author);
		authorTools.AddUpdateAuthor(author);
		
		int i = bookTools.AddBook(book);
		if (i == 1) 
			return true;
		else 
			return false;
	}
	
	//ɾ���鱾
	public boolean DeleteBook(String idBook)
	{
		int i = bookTools.DeleteBook(idBook);
		if (i == 1) 
			return true;
		else
			return false;
	}
	
	
}
