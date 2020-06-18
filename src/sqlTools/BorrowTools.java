package sqlTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseTools;
import model.Book;
/**
 * 
 * @author lygwangyp
 * @return ���ն��߱�Ų��ҽ��ĵ�ͼ��
 */
public class BorrowTools {
	/**
	 * 
	 * @param idReader
	 * @return ����ѯ�����ر�Ŷ�Ӧ����������Ŀ
	 */
	public List<Book> BookData(String idReader) {
		String sql="select book.idBook,nameBook,price,book.kind,author,publisher ,intro from reader,borrow,book "
				+ "where book.idBook = borrow.idBook and reader.idReader = borrow.idReader "
				+ "and reader.idReader = '" + idReader + "'";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Book> ls=new ArrayList<Book>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				Book book=new Book();
				book.setIdBook(rs.getString("idBook"));
				book.setNameBook(rs.getString("nameBook"));
				book.setPrice(rs.getInt("price"));
				book.setType(rs.getString("kind"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
                                                                book.setIntro(rs.getString("intro"));
				ls.add(book);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	/**
	 * 
	 * @param idReader
	 * @return ����ѯ�����ر�Ŷ�Ӧ����������Ŀ
	 */
	public List<Book> BookData_Search_idBook(String idBook) {
		String sql="select book.idBook,nameBook,price,book.kind,author,publisher,intro from book where book.idBook = '" + idBook + "'";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Book> ls=new ArrayList<Book>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				Book book=new Book();
				book.setIdBook(rs.getString("idBook"));
				book.setNameBook(rs.getString("nameBook"));
				book.setPrice(rs.getInt("price"));
				book.setType(rs.getString("kind"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
                                                                book.setIntro(rs.getString("intro"));
				ls.add(book);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
                /**
                *
                *@param idReader
                *@return �����ѯ��
                */
                public List<Borrow> BorrowInfo(String idReader) {
		String sql="select borrow.idReader,idBook,lendDate,dueDate,overtime from borrow where borrow.idReader = '" + idReader + "'";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Borrow> ls=new ArrayList<Borrow>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				Borrow borrowInfo=new Borrow();
                                                                borrowInfo.setIdReader(rs.getString("idReader"));
				borrowInfo.setIdBook(rs.getString("idBook"));
				borrowInfo.setLendDate(rs.getDate("lendDate"));
			                borrowInfo.setDueDate(rs.getDate("dueDate"));
				borrowInfo.setOvertime(rs.getString("overtime"));
				ls.add(borrow);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
               }
	/**
	 * 
	 * @param idBook
	 * @return ���ظ����Ƿ��ڿ⣬�ڿ�Ϊtrue�����Ϊfalse
	 */
	public boolean whetherInStock(String idBook) {
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		String sql = "select * from borrow ";
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if(rs.getString("idBook")!=null){
					if(rs.getString("idBook").equals(idBook)){
						return false;
					}
				}
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * 
	 * @param idReader,idbook
	 * @return �������ݲ��������������ĵ���Ϣ
	 * ����ֵ��
	 * (1) SQL ���ݲ������� (DML) �������� (2) �����޷������ݵ� SQL ��䣬���� 0 
	 */
	public int BorrowBook(String idReader,String idbook) {
		int i=0;
		String sql="insert into borrow (idReader,idbook,lendDate,dueDate,overtime)values(?,?,"
				+ "CURRENT_DATE(),DATE_ADD(CURRENT_DATE(),INTERVAL 2 MONTH),'��')";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);		
			st.setString(1, idReader);
			st.setString(2, idbook);
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
	/**
	 * 
	 * @param idbook
	 * @return 
	 * ��������ɾ��������ɾ�����ĵ���Ϣ����ʾ�黹ͼ��
	 * ����ֵ��
	 * (1) SQL ���ݲ������� (DML) �������� (2) �����޷������ݵ� SQL ��䣬���� 0 
	 */
	public int ReturnBook(String idbook) {
		int i=0;
		String sql="delete from Borrow where idBook=?";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, idbook);
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
}
