package ma.ensa.todo.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static final String username = "admin";
	private static final String password = "admin";
	private static final String url = "jdbc:mysql://localhost:3306/todolist";
	private static Connection con = null;

	private DBConnection() {

	}

	public static Connection getConnection() {
		try {
			if (con == null)
				con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			getErrorException(e);
		}
		return con;

	}

	public static void getErrorException(SQLException e) {
		System.err.println("State  : " + e.getSQLState());
		System.err.println("Code   : " + e.getErrorCode());
		System.err.println("Message: " + e.getMessage());
	}

	public static void close(Connection con, Statement stm, PreparedStatement pstm, ResultSet rs) {

		try {
			if (rs != null)
				rs.close();
			if (pstm != null)
				pstm.close();
			if (stm != null)
				stm.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			getErrorException(e);
		}
	}
	/*
	 * public static void main(String[] args){ Connection con=getConnextion();
	 * System.out.println("connexion réussie"); }
	 */
}
