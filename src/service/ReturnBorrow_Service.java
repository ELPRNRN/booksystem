package service;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import model.Author;
import model.Book;
import model.Borrow;
import model.Publisher;
import sqlTools.BorrowTools;

public class ReturnBorrow_Service 
{
	Borrowtools borrowtools =new Borrowtools();
	private static ReturnBorrow_Service ReturnBorrow_Service_Instance = new ReturnBorrow_Service();
	
	//单例
	public static ReturnBorrow_Service getInstance() {
		return ReturnBorrow_Service_Instance;
	}
	private ReturnBorrow_Service() {};
	
	//读者借书
	String BorrowBook(String idBook)
	{
		UpdateOvertime(LoginLogout_Service.getIdReader());
		boolean overtime=WhetherBookOverTime(LoginLogout_Service.getIdReader());
		if(overtime==true)
			return "有书过期未还";
		else 
			{
			String idReader=LoginLogout_Service.getIdReader();
			int i = borrowtools.BorrowBook(idReader, idBook);
			if (i == 1) 
				return "借阅成功";
			else 
				return "借阅失败";
			}
	}
	
	//读者还书
	String ReturnBook(String idBook)
	{
		UpdateOvertime(LoginLogout_Service.getIdReader());
		boolean overtime=WhetherBookOverTime(LoginLogout_Service.getIdReader());
		if(overtime==true)
			return "有书过期未还";
		else
			{
			int i = borrowtools.ReturnBook(idBook);
			if (i == 1) 
				return "还书成功";
			else 
				return "还书失败";
			}
	}
	
	//管理员删除读者所借书
	String DeleteBorrowBook(String idBook)
	{
		int i = borrowtools.ReturnBook(idBook);
		if (i == 1) 
			return "删除成功";
		else 
			return "删除失败";		
	}
	
	
	//更新读者借阅图书的过期标志
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
	
	//查看读者是否有书过期未还
	boolean WhetherBookOverTime(String idReader)
	{
		List<Borrow> borrowinfo=borrowtools.BorrowInfo(idReader);
		for (Iterator<Borrow> iterator = borrowinfo.iterator(); iterator.hasNext();) 
		{
			Borrow temp = (Borrow) iterator.next();
			if(temp.getOvertime()=="是")
				return true;
		}
		
		return false;
	}
}
