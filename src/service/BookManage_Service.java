package service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.Format;

import model.Author;
import model.Book;
import model.Publisher;
import sqlTools.AuthorTools;
import sqlTools.BookTools;
import sqlTools.BorrowTools;
import sqlTools.PublisherTools;

public class BookManage_Service 
{
	BorrowTools borrowtools = new BorrowTools();
	BookTools bookTools = new BookTools();
	AuthorTools authorTools = new AuthorTools();
	PublisherTools publisherTools = new PublisherTools();
    private static BookManage_Service BookManage_Service_Instance = new BookManage_Service();
	
	//����
	public static BookManage_Service getInstance() {
		return BookManage_Service_Instance;
	}
	private BookManage_Service() {};
	
	
	//�鿴�鱾�Ƿ��ڿ⣨ͨ����Ų��ң�
	public String BookWhetherInStock(String idBook)
	{
		if (borrowtools.whetherInStock(idBook) == true) 
			return "�ڿ�"; 
		else 
			return "���";
	}
	
	
	
	//����ͼ�������Ϣ������Ϊbook��author��publisher��������������Ϣ������Ϊ�գ�ǰ���жϣ�
	public boolean UpdateBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.AddUpdatePublisher(publisher);	
		authorTools.AddUpdateAuthor(author);
		
		int i = bookTools.UpdateBook(book);
		if ( i == 1) 
            return true;//���³ɹ�
		 else 
			return false;//����ʧ��
	}
	
	//�鱾ע����⣬����Ϊbook��author��publisher��������������Ϣ������Ϊ�գ�ǰ���жϣ�
	public boolean RegisterBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.AddUpdatePublisher(publisher);
		authorTools.AddUpdateAuthor(author);
		
		int i = bookTools.AddBook(book);
		if (i == 1) 
			return true;//ע��ɹ�
		else 
			return false;//ע��ʧ��,����Ѵ���
	}
	
	//ɾ���鱾��ͨ����Ų��ң�
	public boolean DeleteBook(String idBook)
	{
		int i = bookTools.DeleteBook(idBook);
		if (i == 1) 
			return true;//ɾ���ɹ�
		else
			return false;//ɾ��ʧ��
	}
	
	//�Զ�����鱾ID������ע���鱾
	//ID��¼��dat�ļ���
	public String GenerateBookID()
	{

		try(FileReader fr = new FileReader("bookID.dat");BufferedReader br = new BufferedReader(fr))
		{
			String currentID=br.readLine();
			return currentID;
		}
		
		catch(Exception e)
		{
			System.out.print(e.toString());
		}
		
		return "";
	}
	
	//����dat�ļ��е�ͼ��ID�������´��Զ���ȡIDע��ͼ��
	//������ע��ɹ�֮��ŵ��ô˺�������
	public void UpdateGenerateBookID()
	{
		String currentID=GenerateBookID();
		try(FileWriter fw = new FileWriter("bookID.dat");BufferedWriter bw = new BufferedWriter(fw))
		{
			int ID=Integer.parseInt(currentID);
			int nextID=ID+1;
			Format f1 = new DecimalFormat("000");
			String s=f1.format(nextID);
			bw.write(s);
		}
		
		catch(Exception e)
		{
			System.out.print(e.toString());
		}
	}
	
	
}
