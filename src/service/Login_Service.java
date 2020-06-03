package service;

import sqlTools.ReaderTools;
import sqlTools.LibrarianTools;
import model.Reader;
import model.Librarian;

public class Login_Service 
{
	public static String idReader;
	public static String nameReader;
	public static String nameUser;
	public Reader reader;
	public Librarian lib;

	void ReaderLogin(String UserID,String Password)
	{
		reader=new Reader();
		ReaderTools rTools = new ReaderTools();
		reader.setIdReader(UserID);
		reader.setPassword(Password);
		
		boolean whether_login = rTools.ReaderLogin(reader.getIdReader(), reader.getPassword());
		if (whether_login == true) 
		{
			nameReader=rTools.ReaderData(reader.getIdReader()).get(0).getNameReader();
			idReader = reader.getIdReader();
		}
		
	}
	
	void LibLogin(String UserName,String Password)
	{
		LibrarianTools libTools = new LibrarianTools();
		lib = new Librarian();
		lib.setNameUser(UserName);
		lib.setPassword(Password);
		
		boolean whether_login = libTools.LibrarianLogin(lib.getNameUser(), lib.getPassword());
		if (whether_login == true) 
			nameUser = lib.getNameUser();
		
	}

}
