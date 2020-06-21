package service;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import model.Borrow;
import sqlTools.BookTools;
import sqlTools.BorrowTools;

public class ReturnBorrow_Service 
{
	BorrowTools borrowtools =new BorrowTools();
	BookTools booktools=new BookTools();
	private static ReturnBorrow_Service ReturnBorrow_Service_Instance = new ReturnBorrow_Service();
	
	//单例
	public static ReturnBorrow_Service getInstance() {
		return ReturnBorrow_Service_Instance;
	}
	private ReturnBorrow_Service() {};
	
	//读者借书，参数为书号，读者号从LoginLogout_Service中获取
	//借书前先更新读者借阅图书过期信息，并检查是否有书过期未还
	//有书过期未还就不能借书
	public String BorrowBook(String idBook)
	{
		String idReader=LoginLogout_Service.getIdReader();
		UpdateOvertime(idReader);
		boolean overtime=WhetherBookOverTime(idReader);
		if(overtime==true)
			return "有书过期未还";
		else 
			{
			int bookAmount=booktools.GetBookAmount(idBook);//获取读者想借图书的库存数量
			if(bookAmount>0)//库存数量大于0，可以借出
			{
				int i = borrowtools.BorrowBook(idReader, idBook);
				if (i == 1) //借阅成功
				{
					bookAmount=bookAmount-1;//库存数量减1
					booktools.UpdateBookAmount(idBook, bookAmount);//更新书本库存数量
					return "借阅成功";
				}
				else 
					return "借阅失败";//已经借过这本书，不能再借
			}
			else
				return "没有库存";//库存为0，无法借出
			}
	}
	
	//读者还书，参数为书号，读者号从LoginLogout_Service中获取
	//还书前先更新读者借阅图书过期信息，并检查是否有书过期未还
	//有书过期未还就不能还书
	public String ReturnBook(String idBook)
	{
		String idReader=LoginLogout_Service.getIdReader();
		UpdateOvertime(idReader);
		boolean overtime=WhetherBookOverTime(idReader);
		if(overtime==true)
			return "有书过期未还";
		else
			{
			int i = borrowtools.ReturnBook(idReader,idBook);
			if (i == 1) //还书成功
			{
				int bookAmount=booktools.GetBookAmount(idBook);//获取读者想还图书库存数量
				bookAmount=bookAmount+1;//库存数量加1
				booktools.UpdateBookAmount(idBook, bookAmount);//更新图书库存数量
				return "还书成功";
			}
			else 
				return "还书失败";
			}
	}
	
	//管理员删除读者借阅记录，参数为读者号和书号
	//和读者还书功能区别在于没有 “如果存在过期未还记录就不能执行还书操作” 的约束
	//当读者有书过期未还，无法执行借书还书操作时，需要管理员删除读者过期未还的借阅记录，读者才能继续借书还书
	//除上述情况，管理员也可以帮读者正常还书
	public String DeleteBorrowBook(String idReader,String idBook)
	{
		int i = borrowtools.ReturnBook(idReader,idBook);
		if (i == 1) //删除成功
		{
			int bookAmount=booktools.GetBookAmount(idBook);//获取库存
			bookAmount=bookAmount+1;//库存加1
			booktools.UpdateBookAmount(idBook, bookAmount);//更新库存
			return "删除成功";
		}
		else 
			return "删除失败";		
	}
	
	
	//更新读者借阅图书记录的过期标志，参数为读者号
	//通过读者号查找读者的借阅信息
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
	
	//查看读者是否有书过期未还，参数为读者号
	//通过读者号查找读者的借阅信息
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
