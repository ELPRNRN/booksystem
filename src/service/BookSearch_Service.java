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
		
	//单例
	public static BookSearch_Service getInstance() {
		return BookSearch_Service_Instance;
	}
	private BookSearch_Service() {};

	//查询读者所借书的普通信息
	List<Book> searchByReaderID(String idReader)
	{
		List<Book> booklist = borrowtools.BookData(idReader);//获得读者所借书信息
		return booklist;
	}
	
	//查询读者所借书的借阅信息
	List<Borrow> searchBorrowInfo(String idReader)
	{
		UpdateOvertime(idReader);
		List<Borrow> borrowinfo=borrowtools.BorrowInfo(idReader);
		return borrowinfo;
	}
	
	//查询所有图书
	List<Book> searchAllBooks()
	{
		List<Book> booklist = bookTools.BookData();
		return booklist;
	}
	
	//按书名查书
	List<Book> searchByBookName(String keyword)
	{
		List<Book> booklist = bookTools.BookData(keyword);
		return booklist;
	}
	
	//按书号查书
	List<Book> searchByBookID(String idBook)
	{
		List<Book> booklist = borrowtools.BookData_Search_idBook(idBook);
		//borrowtools函数应改到bookTools
		return booklist;
	}
	
	//搜索作者信息
	List<Author> searchAuthorInfo(String nameAuthor)
	{
		List<Author> authorlist = authorTools.AuthorData(nameAuthor);
		return authorlist;
	}
	
	//搜索出版社信息
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
			Date date=new Date();//当前时间
			
			int datecompare=date.compareTo(temp.getDueDate());

			if(datecompare>0)  //书本过期未还
				temp.setOvertime("是");
			else  //书本未过期限
				temp.setOvertime("否");
		}
		
	}
}
