package sqlTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseTools;
import model.Book;
import model.Borrow;
/**
 * 
 * @return 按照读者编号查找借阅的图书
 */
public class BorrowTools {
	/**
	 * 
	 * @param idReader
	 * @return 多表查询，返回编号对应读者所借书目的信息
	 */
	public List<Book> BookData(String idReader) {
		String sql="select book.idBook,nameBook,price,book.kind,author,publisher,intro,amount "
				+ "from reader,borrow,book "
				+ "where book.idBook = borrow.idBook and reader.idReader = borrow.idReader "
				+ "and reader.idReader = '" + idReader + "'"
				+ "order by idBook asc";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Book> ls=new ArrayList<Book>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Book book=new Book();
				book.setIdBook(rs.getString("idBook"));
				book.setNameBook(rs.getString("nameBook"));
				book.setPrice(rs.getInt("price"));
				book.setType(rs.getString("kind"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
                book.setIntro(rs.getString("intro"));
                book.setAmount(rs.getInt("amount"));
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
    *@return 查询图书借阅情况
    */
        public List<Borrow> BorrowInfo(String idReader) {
		String sql="select borrow.idReader,idBook,lendDate,dueDate,overtime "
				+ "from borrow "
				+ "where borrow.idReader = '" + idReader + "'"
				+ "order by idBook asc";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Borrow> ls=new ArrayList<Borrow>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Borrow borrowInfo=new Borrow();
                borrowInfo.setIdReader(rs.getString("idReader"));
				borrowInfo.setIdBook(rs.getString("idBook"));
				borrowInfo.setLendDate(rs.getDate("lendDate"));
			    borrowInfo.setDueDate(rs.getDate("dueDate"));
				borrowInfo.setOvertime(rs.getString("overtime"));
				ls.add(borrowInfo);
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
	 * @return 返回该书是否在库，在库为true，借出为false
	 */
	public boolean whetherInStock(String idBook) {
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		String sql = "select * "
				+ "from borrow ";
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
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
	 * @return 进行数据插入操作，加入借阅的信息
	 * 返回值：
	 * (1) SQL 数据操作语言 (DML) 语句的行数 (2) 对于无返回内容的 SQL 语句，返回 0 
	 */
	public int BorrowBook(String idReader,String idBook) {
		int i=0;
		String sql="insert into borrow (idReader,idbook,lendDate,dueDate,overtime)values(?,?,"
				+ "GETDATE(),DATEADD(MONTH,2,GETDATE()),'否')";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);		
			st.setString(1, idReader);
			st.setString(2, idBook);
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
	 * 进行数据删除操作，删除借阅的信息，表示归还图书
	 * 返回值：
	 * (1) SQL 数据操作语言 (DML) 语句的行数 (2) 对于无返回内容的 SQL 语句，返回 0 
	 */
	public int ReturnBook(String idReader,String idBook) {
		int i=0;
		String sql="delete from borrow "
				+ "where idReader=? and idBook=?";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, idReader);
			st.setString(2, idBook);
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
	
	public int updateBorrowInfo(Borrow borrowInfo) 
	{
		int i=0;
		String sql="update borrow "
				+ "set overtime=? "
				+ "where idReader=? and idBook=?";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, borrowInfo.getOvertime());
			st.setString(2, borrowInfo.getIdReader());
			st.setString(3, borrowInfo.getIdBook());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
}


