package com.techcozi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {

	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/test?"
											+ "user=root&password=";
	//public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/test","root", "password";
	
	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet;
	
	public DataBaseConnection(){
	}
	
	public static Connection getConnection() {
		try {
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_URL);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return connection;
	}
	public void readData() throws Exception {
		try {
			connection = getConnection(); 
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from test.userbo;");
			getResultSet(resultSet);
		}finally{
			close();
		}
	}
	
	private void getResultSet(ResultSet resultSet) throws Exception {
		while(resultSet.next()){
			 
			System.out.println(" Record : "+resultSet.getString(2));
		}
	}
	
	private void close(){
		try {
			if(resultSet!=null) resultSet.close();
			if(statement!=null) statement.close();
			if(connection!=null) connection.close();
		} catch(Exception e){}
	}
}
