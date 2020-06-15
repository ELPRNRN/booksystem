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
	
	//�鿴�鱾�Ƿ��ڿ�
	String BookWhetherInStock(String idBook)
	{
		if (borrowtools.whetherInStock(temp.getIdBook()) == true) 
			return "�ڿ�"; 
		else 
			return "���";
	}
	
	//����ͼ�������Ϣ
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
	
	//�鱾ע�����
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
	
	//ɾ���鱾
	boolean DeleteBook(String idBook)
	{
		int i = bookTools.DeleteBook(idBook);
		if (i == 1) 
			return true;
		else
			return false;
	}
}
