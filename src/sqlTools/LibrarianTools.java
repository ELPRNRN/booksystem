package sqlTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseTools;

public class LibrarianTools {
	/**
	 * @category Librarian
	 * @param nameUser
	 * @param password
	 * @return ��֤�û����������Ƿ���ȷ����ȷ����True�����󷵻�False
	 * 
	 */
	public boolean LibrarianLogin(String nameUser, String password) {
		DatabaseTools db = new DatabaseTools();
		Connection conn = db.getConn();
		try {
			String sql = "select nameUser,password from librarian where nameUser='" + nameUser + "' and password='" + password+"'";
			PreparedStatement st =conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
