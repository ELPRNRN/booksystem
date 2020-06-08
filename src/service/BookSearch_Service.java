package service;

import sqlTools.ReaderTools;
import sqlTools.LibrarianTools;

import java.util.List;

import model.Author;
import model.Book;
import model.Publisher;
import model.Reader;
import sqlTools.BookTools;
import sqlTools.BorrowTools;

public class BookSearch_Service 
{
	BorrowTools borrowtools = new BorrowTools();
	BookTools bookTools = new BookTools();
	AuthorTools authorTools = new AuthorTools();
	PublisherTools publisherTools = new PublisherTools();

	//查询某个读者所借书
	List<Book> searchByReaderID(String idReader)
	{
		List<Book> booklist = borrowtools.BookData(idReader);//获得读者所借书信息
		return booklist;
	}
	
	//查询所有图书
	List<Book> searchAllBooks()
	{
		List<Book> booklist = bookTools.BookData();
		return booklist;
	}
	
	//按书名查书
	List <Book> searchByBookName(String keyword)
	{
		List<Book> booklist = bookTools.BookData(keyword);
		return booklist;
	}
	
	//按书号查书
	List <Book> searchByBookID(String idBook)
	{
		List<Book> booklist = borrowtools.BookData_Search_idBook(idBook);
		//borrowtools函数应改到bookTools
		return booklist;
	}
	
	List<Author> searchAuthorInfo(String nameAuthor)
	{
		List<Author> authorlist = authorTools.AuthorData(temp.getAuthor());
		return authorlist;
	}
	
	publisher searchPublisherInfo(String namePublisher)
	{
		Publisher publisher = publisherTools.PublisherData(temp.getPublisher());
		return publisher;
	}
}
