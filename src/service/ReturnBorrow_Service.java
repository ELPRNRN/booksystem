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
	
	//����
	public static ReturnBorrow_Service getInstance() {
		return ReturnBorrow_Service_Instance;
	}
	private ReturnBorrow_Service() {};
	
	//���߽��飬����Ϊ��ţ����ߺŴ�LoginLogout_Service�л�ȡ
	//����ǰ�ȸ��¶��߽���ͼ�������Ϣ��������Ƿ��������δ��
	//�������δ���Ͳ��ܽ���
	public String BorrowBook(String idBook)
	{
		String idReader=LoginLogout_Service.getIdReader();
		UpdateOvertime(idReader);
		boolean overtime=WhetherBookOverTime(idReader);
		if(overtime==true)
			return "�������δ��";
		else 
			{
			int bookAmount=booktools.GetBookAmount(idBook);//��ȡ�������ͼ��Ŀ������
			if(bookAmount>0)//�����������0�����Խ��
			{
				int i = borrowtools.BorrowBook(idReader, idBook);
				if (i == 1) //���ĳɹ�
				{
					bookAmount=bookAmount-1;//���������1
					booktools.UpdateBookAmount(idBook, bookAmount);//�����鱾�������
					return "���ĳɹ�";
				}
				else 
					return "����ʧ��";//�Ѿ�����Ȿ�飬�����ٽ�
			}
			else
				return "û�п��";//���Ϊ0���޷����
			}
	}
	
	//���߻��飬����Ϊ��ţ����ߺŴ�LoginLogout_Service�л�ȡ
	//����ǰ�ȸ��¶��߽���ͼ�������Ϣ��������Ƿ��������δ��
	//�������δ���Ͳ��ܻ���
	public String ReturnBook(String idBook)
	{
		String idReader=LoginLogout_Service.getIdReader();
		UpdateOvertime(idReader);
		boolean overtime=WhetherBookOverTime(idReader);
		if(overtime==true)
			return "�������δ��";
		else
			{
			int i = borrowtools.ReturnBook(idReader,idBook);
			if (i == 1) //����ɹ�
			{
				int bookAmount=booktools.GetBookAmount(idBook);//��ȡ�����뻹ͼ��������
				bookAmount=bookAmount+1;//���������1
				booktools.UpdateBookAmount(idBook, bookAmount);//����ͼ��������
				return "����ɹ�";
			}
			else 
				return "����ʧ��";
			}
	}
	
	//����Աɾ�����߽��ļ�¼������Ϊ���ߺź����
	//�Ͷ��߻��鹦����������û�� ��������ڹ���δ����¼�Ͳ���ִ�л�������� ��Լ��
	//�������������δ�����޷�ִ�н��黹�����ʱ����Ҫ����Աɾ�����߹���δ���Ľ��ļ�¼�����߲��ܼ������黹��
	//���������������ԱҲ���԰������������
	public String DeleteBorrowBook(String idReader,String idBook)
	{
		int i = borrowtools.ReturnBook(idReader,idBook);
		if (i == 1) //ɾ���ɹ�
		{
			int bookAmount=booktools.GetBookAmount(idBook);//��ȡ���
			bookAmount=bookAmount+1;//����1
			booktools.UpdateBookAmount(idBook, bookAmount);//���¿��
			return "ɾ���ɹ�";
		}
		else 
			return "ɾ��ʧ��";		
	}
	
	
	//���¶��߽���ͼ���¼�Ĺ��ڱ�־������Ϊ���ߺ�
	//ͨ�����ߺŲ��Ҷ��ߵĽ�����Ϣ
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
	
	//�鿴�����Ƿ��������δ��������Ϊ���ߺ�
	//ͨ�����ߺŲ��Ҷ��ߵĽ�����Ϣ
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
