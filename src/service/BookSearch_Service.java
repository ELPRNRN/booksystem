package service;

import sqlTools.PublisherTools;
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
		
	//单例
	public static BookSearch_Service getInstance() {
		return BookSearch_Service_Instance;
	}
	private BookSearch_Service() {};
	
	//按书信息查书（书号、书名、类型、作者名字、出版社名字，均实现模糊搜索），返回书列表；
	//所有参数必须有值，不能为NULL；如果用户没有输入某个参数，则该参数为空字符串“”；
	public List<Book> searchByBookInfo(String idBook,String nameBook,String type,String author,String publisher)
	{
		List<Book> booklist = bookTools.BookData_SearchByBookInfo(idBook, nameBook, type, author, publisher);
		return booklist;
	}

	//查询读者所借书的普通信息，参数为读者号（精确搜索），返回书列表
	public List<Book> searchByReaderID(String idReader)
	{
		List<Book> booklist = borrowTools.BookData(idReader);//获得读者所借书原有信息
		return booklist;
	}
	
	//查询读者所借书的借阅信息，参数为读者号（精确搜索），返回借阅情况列表
	public List<Borrow> searchBorrowInfo(String idReader)
	{
		UpdateOvertime(idReader);
		List<Borrow> borrowinfo=borrowTools.BorrowInfo(idReader);
		return borrowinfo;
	}
	
	//查询系统中所有图书的普通信息，返回书列表
	public List<Book> searchAllBooks()
	{
		List<Book> booklist = bookTools.BookData();
		return booklist;
	}
	
	//按书名查书，参数为书名关键字（模糊搜索），返回书列表
	public List<Book> searchByBookName(String keyword)
	{
		List<Book> booklist = bookTools.BookData(keyword);
		return booklist;
	}
	
	//按书号查书，参数为书号（精确搜索），返回书列表
	public List<Book> searchByBookID(String idBook)
	{
		List<Book> booklist = bookTools.BookData_Search_idBook(idBook);
		return booklist;
	}
	
	//按作者名字查找作者，参数为作者名（精确搜索），返回作者列表
	public List<Author> searchAuthorInfo(String keyword)
	{
		List<Author> authorlist = authorTools.AuthorData(keyword);
		return authorlist;
	}
	
	//按出版社名字查找出版社，参数为出版社名（精确搜索），返回出版社列表
	public List<Publisher> searchPublisherInfo(String keyword)
	{
		List<Publisher> publisherlist = publisherTools.PublisherData(keyword);
		return publisherlist;
	}
	
	//更新读者的图书借阅过期情况
	//对当前时间和图书的归还期限作比较，如果已过归还期限，则将过期标志设置为“是”
	public void UpdateOvertime(String idReader)
	{
		List<Borrow> borrowinfo=borrowTools.BorrowInfo(idReader);
		for (Iterator<Borrow> iterator = borrowinfo.iterator(); iterator.hasNext();) 
		{
			Borrow temp = (Borrow) iterator.next();
			
			java.util.Date javaDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());//当前时间（年-月-日）	
			int datecompare=sqlDate.compareTo(temp.getDueDate());

			if(datecompare>0)  //书本过期未还
				{
					temp.setOvertime("是");
					borrowTools.updateBorrowInfo(temp);
				}
			
		}
		
	}
}
