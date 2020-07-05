package service;
import sqlTools.ReaderTools;
import model.Reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;


public class ReaderManage_Service 
{
	ReaderTools readerTools = new ReaderTools();
	private static ReaderManage_Service ReaderManage_Service_Instance = new ReaderManage_Service();
	
	//单例
	public static ReaderManage_Service getInstance() {
		return ReaderManage_Service_Instance;
	}
	private ReaderManage_Service() {};
	
	//通过读者号查找读者，参数为读者号（精确搜索），返回读者列表
	public List<Reader> SearchReaderByID(String idReader)
	{
		List<Reader> readerlist = readerTools.ReaderData(idReader);
		return readerlist;
	}
	
	//查找系统中所有读者，返回读者列表
	public List<Reader> SearchAllReaders()
	{
		List<Reader> readerlist = readerTools.ReaderData();
		return readerlist;
	}
	
	//通过读者姓名查找读者，参数为读者名关键字（模糊搜索），返回读者列表
	public List<Reader> SearchReaderByName(String keyword)
	{
		List<Reader> readerlist = readerTools.ReaderDataSearch(keyword);
		return readerlist;
	}
	
	//管理员更新读者信息，传入参数为reader对象，所有信息均不能为空
	public boolean UpdateReader(Reader reader)
	{
		int i = readerTools.UpdateReader(reader);
		if ( i == 1 ) 
            return true;//更新成功
		else 
            return false;//更新失败
	}
	
	//管理员新增读者，传入参数为reader对象，所有信息均不能为空
	public boolean AddReader(Reader reader)
	{
		int i = readerTools.AddReader(reader);
		if (i == 1) 
			return true;//添加成功
		else 
			return false;//此读者号已存在，无法插入数据
	}
	
	//删除读者，参数为读者号
	public boolean DeleteReader(String idReader)
	{
		int i = readerTools.DeleteReader(idReader);
		if (i == 1) 
			return true;//删除成功
		else 
			return false;//删除失败
	}
	
	//自动获得读者ID，用于注册读者
	//ID记录在dat文件中
	public String GenerateReaderID()
	{

		try(FileReader fr = new FileReader("readerID.dat");BufferedReader br = new BufferedReader(fr))
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
		
	//更新dat文件中的读者ID，用于下次自动获取ID注册读者
	//当本次注册成功之后才调用此函数更新
	public void UpdateGenerateReaderID()
	{
		String currentID=GenerateReaderID();
		try(FileWriter fw = new FileWriter("readerID.dat");BufferedWriter bw = new BufferedWriter(fw))
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
