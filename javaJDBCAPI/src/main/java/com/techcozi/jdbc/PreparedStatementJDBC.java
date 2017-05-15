package com.techcozi.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementJDBC {

	public static void main(String[] args) {
		
		try {
			displayTheRecords(777);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void displayTheRecords(int userId) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT USER_NAME, USER_ID FROM USER WHERE USER_ID = ?";

		try {
			dbConnection = DataBaseConnection.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, userId);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				String username = rs.getString("USERNAME");
				int userid = rs.getInt("USER_ID");
				System.out.println("user id : " + userid);
				System.out.println("user name : " + username);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
}