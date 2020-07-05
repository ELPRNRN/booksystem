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
	
	//单例
	public static BookManage_Service getInstance() {
		return BookManage_Service_Instance;
	}
	private BookManage_Service() {};
	
	
	//查看书本是否在库（通过书号查找）
	public String BookWhetherInStock(String idBook)
	{
		if (borrowtools.whetherInStock(idBook) == true) 
			return "在库"; 
		else 
			return "借出";
	}
	
	
	
	//更新图书相关信息，参数为book、author、publisher对象，三者所有信息均不能为空（前端判断）
	public boolean UpdateBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.AddUpdatePublisher(publisher);	
		authorTools.AddUpdateAuthor(author);
		
		int i = bookTools.UpdateBook(book);
		if ( i == 1) 
            return true;//更新成功
		 else 
			return false;//更新失败
	}
	
	//书本注册入库，参数为book、author、publisher对象，三者所有信息均不能为空（前端判断）
	public boolean RegisterBook(Book book,Author author,Publisher publisher)
	{
		publisherTools.AddUpdatePublisher(publisher);
		authorTools.AddUpdateAuthor(author);
		
		int i = bookTools.AddBook(book);
		if (i == 1) 
			return true;//注册成功
		else 
			return false;//注册失败,书号已存在
	}
	
	//删除书本（通过书号查找）
	public boolean DeleteBook(String idBook)
	{
		int i = bookTools.DeleteBook(idBook);
		if (i == 1) 
			return true;//删除成功
		else
			return false;//删除失败
	}
	
	//自动获得书本ID，用于注册书本
	//ID记录在dat文件中
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
	
	//更新dat文件中的图书ID，用于下次自动获取ID注册图书
	//当本次注册成功之后才调用此函数更新
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
