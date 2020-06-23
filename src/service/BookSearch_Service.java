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
		
	//����
	public static BookSearch_Service getInstance() {
		return BookSearch_Service_Instance;
	}
	private BookSearch_Service() {};
	
	//������Ϣ���飨��š����������͡��������֡����������֣���ʵ��ģ�����������������б�
	//���в���������ֵ������ΪNULL������û�û������ĳ����������ò���Ϊ���ַ���������
	public List<Book> searchByBookInfo(String idBook,String nameBook,String type,String author,String publisher)
	{
		List<Book> booklist = bookTools.BookData_SearchByBookInfo(idBook, nameBook, type, author, publisher);
		return booklist;
	}

	//��ѯ�������������ͨ��Ϣ������Ϊ���ߺţ���ȷ���������������б�
	public List<Book> searchByReaderID(String idReader)
	{
		List<Book> booklist = borrowTools.BookData(idReader);//��ö���������ԭ����Ϣ
		return booklist;
	}
	
	//��ѯ����������Ľ�����Ϣ������Ϊ���ߺţ���ȷ�����������ؽ�������б�
	public List<Borrow> searchBorrowInfo(String idReader)
	{
		UpdateOvertime(idReader);
		List<Borrow> borrowinfo=borrowTools.BorrowInfo(idReader);
		return borrowinfo;
	}
	
	//��ѯϵͳ������ͼ�����ͨ��Ϣ���������б�
	public List<Book> searchAllBooks()
	{
		List<Book> booklist = bookTools.BookData();
		return booklist;
	}
	
	//���������飬����Ϊ�����ؼ��֣�ģ�����������������б�
	public List<Book> searchByBookName(String keyword)
	{
		List<Book> booklist = bookTools.BookData(keyword);
		return booklist;
	}
	
	//����Ų��飬����Ϊ��ţ���ȷ���������������б�
	public List<Book> searchByBookID(String idBook)
	{
		List<Book> booklist = bookTools.BookData_Search_idBook(idBook);
		return booklist;
	}
	
	//���������ֲ������ߣ�����Ϊ����������ȷ�����������������б�
	public List<Author> searchAuthorInfo(String keyword)
	{
		List<Author> authorlist = authorTools.AuthorData(keyword);
		return authorlist;
	}
	
	//�����������ֲ��ҳ����磬����Ϊ������������ȷ�����������س������б�
	public List<Publisher> searchPublisherInfo(String keyword)
	{
		List<Publisher> publisherlist = publisherTools.PublisherData(keyword);
		return publisherlist;
	}
	
	//���¶��ߵ�ͼ����Ĺ������
	//�Ե�ǰʱ���ͼ��Ĺ黹�������Ƚϣ�����ѹ��黹���ޣ��򽫹��ڱ�־����Ϊ���ǡ�
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
