package service;

import sqlTools.ReaderTools;
import sqlTools.LibrarianTools;

import java.util.List;

import model.Book;
import model.Reader;
import sqlTools.BookTools;
import sqlTools.BorrowTools;

public class BookSearch_Service 
{
	BorrowTools borrowtools = new BorrowTools();
	BookTools bookTools = new BookTools();

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
	}
	
	//按书号查书
	List <Book> searchByBookID(String idBook)
	{
		List<Book> booklist = bookTools.BookData(idBook);
	}
	
	
}
