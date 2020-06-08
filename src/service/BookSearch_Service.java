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
		return booklist;
	}
	
	//����Ų���
	List <Book> searchByBookID(String idBook)
	{
		List<Book> booklist = borrowtools.BookData_Search_idBook(idBook);
		//borrowtools����Ӧ�ĵ�bookTools
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
