package service;

import sqlTools.ReaderTools;
import sqlTools.LibrarianTools;
import sqlTools.PublisherTools;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.Author;
import model.Book;
import model.Borrow;
import model.Publisher;
import model.Reader;
import sqlTools.AuthorTools;
import sqlTools.BookTools;
import sqlTools.BorrowTools;

public class BookSearch_Service 
{
	BorrowTools borrowtools = new BorrowTools();
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
	List<Book> searchByReaderID(String idReader)
	{
		List<Book> booklist = borrowtools.BookData(idReader);//��ö�����������Ϣ
		return booklist;
	}
	
	//��ѯ����������Ľ�����Ϣ
	List<Borrow> searchBorrowInfo(String idReader)
	{
		UpdateOvertime(idReader);
		List<Borrow> borrowinfo=borrowtools.BorrowInfo(idReader);
		return borrowinfo;
	}
	
	//��ѯ����ͼ��
	List<Book> searchAllBooks()
	{
		List<Book> booklist = bookTools.BookData();
		return booklist;
	}
	
	//����������
	List<Book> searchByBookName(String keyword)
	{
		List<Book> booklist = bookTools.BookData(keyword);
		return booklist;
	}
	
	//����Ų���
	List<Book> searchByBookID(String idBook)
	{
		List<Book> booklist = borrowtools.BookData_Search_idBook(idBook);
		//borrowtools����Ӧ�ĵ�bookTools
		return booklist;
	}
	
	//����������Ϣ
	List<Author> searchAuthorInfo(String nameAuthor)
	{
		List<Author> authorlist = authorTools.AuthorData(nameAuthor);
		return authorlist;
	}
	
	//������������Ϣ
	Publisher searchPublisherInfo(String namePublisher)
	{
		Publisher publisher = publisherTools.PublisherData(namePublisher);
		return publisher;
	}
	
	void UpdateOvertime(String idReader)
	{
		List<Borrow> borrowinfo=borrowtools.BorrowInfo(idReader);
		for (Iterator<Borrow> iterator = borrowinfo.iterator(); iterator.hasNext();) 
		{
			Borrow temp = (Borrow) iterator.next();
			Date date=new Date();//��ǰʱ��
			
			int datecompare=date.compareTo(temp.getDueDate());

			if(datecompare>0)  //�鱾����δ��
				temp.setOvertime("��");
			else  //�鱾δ������
				temp.setOvertime("��");
		}
		
	}
}
