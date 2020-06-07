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

	//��ѯĳ������������
	List<Book> searchByReaderID(String idReader)
	{
		List<Book> booklist = borrowtools.BookData(idReader);//��ö�����������Ϣ
		return booklist;
	}
	
	//��ѯ����ͼ��
	List<Book> searchAllBooks()
	{
		List<Book> booklist = bookTools.BookData();
		return booklist;
	}
	
	//����������
	List <Book> searchByBookName(String keyword)
	{
		List<Book> booklist = bookTools.BookData(keyword);
	}
	
	//����Ų���
	List <Book> searchByBookID(String idBook)
	{
		List<Book> booklist = bookTools.BookData(idBook);
	}
	
	
}
