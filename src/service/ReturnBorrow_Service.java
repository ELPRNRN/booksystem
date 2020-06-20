package service;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import model.Borrow;
import sqlTools.BorrowTools;

public class ReturnBorrow_Service 
{
	BorrowTools borrowtools =new BorrowTools();
	private static ReturnBorrow_Service ReturnBorrow_Service_Instance = new ReturnBorrow_Service();
	
	//单例
	public static ReturnBorrow_Service getInstance() {
		return ReturnBorrow_Service_Instance;
	}
	private ReturnBorrow_Service() {};
	
	//有书过期未还就不能借书
	//读者借书（通过书号）
	public String BorrowBook(String idBook)
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
	
	//有书过期未还就不能还书
	//读者还书（通过书号）
	public String ReturnBook(String idBook)
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
	
	//管理员删除读者所借书（通过书号）
	public String DeleteBorrowBook(String idBook)
	{
		int i = borrowtools.ReturnBook(idBook);
		if (i == 1) 
			return "删除成功";
		else 
			return "删除失败";		
	}
	
	
	//更新读者借阅图书的过期标志（通过读者号查找读者借阅的书）
	public void UpdateOvertime(String idReader)
	{
		List<Borrow> borrowinfo=borrowtools.BorrowInfo(idReader);
		for (Iterator<Borrow> iterator = borrowinfo.iterator(); iterator.hasNext();) 
		{
			Borrow temp = (Borrow) iterator.next();
			
			java.util.Date javaDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());//当前时间（年-月-日）	
			int datecompare=sqlDate.compareTo(temp.getDueDate());

			if(datecompare>0)  //书本过期未还
				{
					temp.setOvertime("是");//修改书本过期标志
					borrowtools.updateBorrowInfo(temp);//更新借阅情况
				}
		}
		
	}
	
	//查看读者是否有书过期未还（通过读者号查找读者借阅的书）
	public boolean WhetherBookOverTime(String idReader)
	{
		boolean overtime=false;
		List<Borrow> borrowinfo=borrowtools.BorrowInfo(idReader);
		for (Iterator<Borrow> iterator = borrowinfo.iterator(); iterator.hasNext();) 
		{
			Borrow temp = (Borrow) iterator.next();
			if(temp.getOvertime().equals("是"))
				overtime=true;
		}
		
		return overtime;
	}
}
