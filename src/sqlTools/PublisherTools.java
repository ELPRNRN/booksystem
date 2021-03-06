package sqlTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseTools;
import model.Publisher;

public class PublisherTools {
	/**
	 * 
	 * @param name
	 * @return 返回特定编号的出版社，List<Publisher>获得查找到的对象，存在Java类集list中，并返回list。
	 */
	public List<Publisher> PublisherData(String keyword) {
		String sql="select name,address "
				+ "from publisher "
				+ "where name = '" + keyword + "'";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Publisher> ls=new ArrayList<Publisher>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Publisher publisher=new Publisher();
				publisher.setName(rs.getString("name"));
				publisher.setAddress(rs.getString("address"));
				ls.add(publisher);
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
	 * @return 返回全部出版社，List<Publisher>，获得查找到的对象，存在Java类集list中，并返回list。
	 * 
	 */
	public List<Publisher> PublisherData() {
		String sql="select name,address "
				+ "from publisher";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Publisher> ls=new ArrayList<Publisher>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Publisher publisher=new Publisher();
				publisher.setName(rs.getString("name"));
				publisher.setAddress(rs.getString("address"));
				ls.add(publisher);
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
	 * @param publisher
	 * @return 进行数据插入操作，插入对应的Publisher对象。
	 * 返回值：
	 * (1) SQL 数据操作语言 (DML) 语句的行数 (2) 对于无返回内容的 SQL 语句，返回 0 
	 */
	/*
	public int AddPublisher(Publisher publisher) {
		int i=0;
		String sql="insert into publisher (name,address)values(?,?)";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);		
			st.setString(1, publisher.getName());
			st.setString(2, publisher.getAddress());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
	*/
	/**
	 * 
	 * @param publisher
	 * @return 进行数据更新操作，更新对应的Publisher对象。
	 * 返回值：
	 * (1) SQL 数据操作语言 (DML) 语句的行数 (2) 对于无返回内容的 SQL 语句，返回 0 
	 */
	public int AddUpdatePublisher(Publisher publisher) {
		int i=0;
		String sql="if not exists (select name from publisher where name = ?)" + 
				"   	INSERT INTO publisher VALUES (?,?)" + 
				"else"+
				"   UPDATE publisher SET name = ?, address = ?" + 
				"   WHERE name = ? ";
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, publisher.getName());
			st.setString(2, publisher.getName());
			st.setString(3, publisher.getAddress());
			st.setString(4, publisher.getName());
			st.setString(5, publisher.getAddress());
			st.setString(6, publisher.getName());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
}
