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

	List<Book> searchByReaderID(String idReader)//��ѯĳ������������
	{
		List<Book> booklist = borrowtools.BookData(idReader);//��ö�����������Ϣ
		return booklist;
	}
	
	List<Book> searchAllBooks()
	{
		List<Book> booklist = bookTools.BookData();
		return booklist;
	}
	
	List <Book> searchBy
	
	
}
