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
	
	//����
	public static ReturnBorrow_Service getInstance() {
		return ReturnBorrow_Service_Instance;
	}
	private ReturnBorrow_Service() {};
	
	//�������δ���Ͳ��ܽ���
	//���߽��飨ͨ����ţ�
	public String BorrowBook(String idBook)
	{
		UpdateOvertime(LoginLogout_Service.getIdReader());
		boolean overtime=WhetherBookOverTime(LoginLogout_Service.getIdReader());
		if(overtime==true)
			return "�������δ��";
		else 
			{
			String idReader=LoginLogout_Service.getIdReader();
			int i = borrowtools.BorrowBook(idReader, idBook);
			if (i == 1) 
				return "���ĳɹ�";
			else 
				return "����ʧ��";
			}
	}
	
	//�������δ���Ͳ��ܻ���
	//���߻��飨ͨ����ţ�
	public String ReturnBook(String idBook)
	{
		UpdateOvertime(LoginLogout_Service.getIdReader());
		boolean overtime=WhetherBookOverTime(LoginLogout_Service.getIdReader());
		if(overtime==true)
			return "�������δ��";
		else
			{
			int i = borrowtools.ReturnBook(idBook);
			if (i == 1) 
				return "����ɹ�";
			else 
				return "����ʧ��";
			}
	}
	
	//����Աɾ�����������飨ͨ����ţ�
	public String DeleteBorrowBook(String idBook)
	{
		int i = borrowtools.ReturnBook(idBook);
		if (i == 1) 
			return "ɾ���ɹ�";
		else 
			return "ɾ��ʧ��";		
	}
	
	
	//���¶��߽���ͼ��Ĺ��ڱ�־��ͨ�����ߺŲ��Ҷ��߽��ĵ��飩
	public void UpdateOvertime(String idReader)
	{
		List<Borrow> borrowinfo=borrowtools.BorrowInfo(idReader);
		for (Iterator<Borrow> iterator = borrowinfo.iterator(); iterator.hasNext();) 
		{
			Borrow temp = (Borrow) iterator.next();
			
			java.util.Date javaDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());//��ǰʱ�䣨��-��-�գ�	
			int datecompare=sqlDate.compareTo(temp.getDueDate());

			if(datecompare>0)  //�鱾����δ��
				{
					temp.setOvertime("��");//�޸��鱾���ڱ�־
					borrowtools.updateBorrowInfo(temp);//���½������
				}
		}
		
	}
	
	//�鿴�����Ƿ��������δ����ͨ�����ߺŲ��Ҷ��߽��ĵ��飩
	public boolean WhetherBookOverTime(String idReader)
	{
		boolean overtime=false;
		List<Borrow> borrowinfo=borrowtools.BorrowInfo(idReader);
		for (Iterator<Borrow> iterator = borrowinfo.iterator(); iterator.hasNext();) 
		{
			Borrow temp = (Borrow) iterator.next();
			if(temp.getOvertime().equals("��"))
				overtime=true;
		}
		
		return overtime;
	}
}
