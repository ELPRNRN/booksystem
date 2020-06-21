package service;

import sqlTools.ReaderTools;
import sqlTools.LibrarianTools;

import model.Reader;
import model.Librarian;

public class LoginLogout_Service 
{
	//存储当前读者或管理员信息
	private static String idReader;//读者号
	private static String nameReader;//读者名
	private static String nameUser;//管理员号
	
	public Librarian lib;
	private static LoginLogout_Service loginLogout_Service_Instance = new LoginLogout_Service();
	
	//单例
	public static LoginLogout_Service getInstance() {
		return loginLogout_Service_Instance;
	}
	private LoginLogout_Service() {};
	
	//读者登录
	//数据库中已添加读者账号
	public boolean ReaderLogin(String UserID,String Password)
	{
		ReaderTools rTools = new ReaderTools();
		
		boolean whether_login = rTools.ReaderLogin(UserID, Password);//对照数据尝试登录
		if (whether_login == true) //登陆成功
		{
			nameReader=rTools.ReaderData(UserID).get(0).getNameReader();//获取读者名并记录到nameReader
			idReader = UserID;//记录读者号到idReader
			return true;
		}
		else
			return false;
		
	}
	
	//管理员登陆
	//数据库中已添加管理员帐号
	public boolean LibLogin(String UserName,String Password)
	{
		LibrarianTools libTools = new LibrarianTools();
		
		boolean whether_login = libTools.LibrarianLogin(UserName, Password);//对照数据尝试登陆
		if (whether_login == true) //登陆成功
		{
			nameUser = UserName;//记录管理者号到nameUser中
			return true;
		}
		else
			return false;
		
	}
	
	//读者登出
	//清空读者登录信息
	public void ReaderLogout()
	{
		idReader=null;
		nameReader=null;
	}
	
	//管理员登出
	//清空管理者登录信息
	public void LibLogout()
	{
		nameUser=null;
	}

	//获取已登录读者号
	public static String getIdReader() {
		return idReader;
	}

	//获取已登录管理者号
	public static String getNameUser() {
		return nameUser;
	}

	//获取已登录读者名
	public static String getNameReader() {
		return nameReader;
	}
	
	public static void setNameUser(String nameUser) {
		LoginLogout_Service.nameUser = nameUser;
	}

	public static void setIdReader(String idReader) {
		LoginLogout_Service.idReader = idReader;
	}

	public static void setNameReader(String nameReader) {
		LoginLogout_Service.nameReader = nameReader;
	}

	
}
