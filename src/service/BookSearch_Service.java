package service;

import sqlTools.PublisherTools;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import model.Author;
import model.Book;
import model.Borrow;
import model.Publisher;
import sqlTools.AuthorTools;
import sqlTools.BookTools;
import sqlTools.BorrowTools;

public class BookSearch_Service 
{
	BorrowTools borrowTools = new BorrowTools();
	BookTools bookTools = new BookTools();
	AuthorTools authorTools = new AuthorTools();
	PublisherTools publisherTools = new PublisherTools();
	private static BookSearch_Service BookSearch_Service_Instance = new BookSearch_Service();
		
	//����
	public static BookSearch_Service getInstance() {
		return BookSearch_Service_Instance;
	}
	private BookSearch_Service() {};

	//��ѯ�������������ͨ��Ϣ
	public List<Book> searchByReaderID(String idReader)
	{
		List<Book> booklist = borrowTools.BookData(idReader);//��ö���������ԭ����Ϣ
		return booklist;
	}
	
	//��ѯ����������Ľ�����Ϣ
	public List<Borrow> searchBorrowInfo(String idReader)
	{
		UpdateOvertime(idReader);
		List<Borrow> borrowinfo=borrowTools.BorrowInfo(idReader);
		return borrowinfo;
	}
	
	//��ѯ����ͼ��
	public List<Book> searchAllBooks()
	{
		List<Book> booklist = bookTools.BookData();
		return booklist;
	}
	
	//����������
	public List<Book> searchByBookName(String keyword)
	{
		List<Book> booklist = bookTools.BookData(keyword);
		return booklist;
	}
	
	//����Ų���
	public List<Book> searchByBookID(String idBook)
	{
		List<Book> booklist = bookTools.BookData_Search_idBook(idBook);
		return booklist;
	}
	
	//����������Ϣ
	public List<Author> searchAuthorInfo(String nameAuthor)
	{
		List<Author> authorlist = authorTools.AuthorData(nameAuthor);
		return authorlist;
	}
	
	//������������Ϣ
	public List<Publisher> searchPublisherInfo(String namePublisher)
	{
		List<Publisher> publisher = publisherTools.PublisherData(namePublisher);
		return publisher;
	}
	
	public void UpdateOvertime(String idReader)
	{
		List<Borrow> borrowinfo=borrowTools.BorrowInfo(idReader);
		for (Iterator<Borrow> iterator = borrowinfo.iterator(); iterator.hasNext();) 
		{
			Borrow temp = (Borrow) iterator.next();
			
			java.util.Date javaDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());//��ǰʱ�䣨��-��-�գ�	
			int datecompare=sqlDate.compareTo(temp.getDueDate());

			if(datecompare>0)  //�鱾����δ��
				{
					temp.setOvertime("��");
					borrowTools.updateBorrowInfo(temp);
				}
			
		}
		
	}
}
