package assist;

import java.io.*;
import java.sql.*;

public class SqlConnection
{
	private Connection conn=null;
	private ResultSet rs=null;
	   //Database driver
	private String DatabaseDriver="com.jdbc.mysql.Driver";
	   //Database sourse 
	private String DatabaseConnStr="jdbc:mysql://localhost:3306/flight";
	   //ID to login
	private String LogId="root";
	   //Password to login
	private String LogPass="";
	
	   //Constructor method
	public SqlConnection()
	{
		try
		{
			Class.forName(DatabaseDriver);
		}
		catch(ClassNotFoundException e)
		{
		}
	}
	   
	   //select
	public ResultSet executeQuery(String sql)
	{
		rs = null;
		try
		{
			conn = DriverManager.getConnection(DatabaseConnStr,LogId,LogPass);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		}
		catch(SQLException ex)
		{		
		}
	
		return rs;
	}
	   
	   //insert
	public int executeInsert(String sql)
	{
		int num = 0;
		try
		{
			conn = DriverManager.getConnection(DatabaseConnStr,LogId,LogPass);
			Statement stmt = conn.createStatement();
			num = stmt.executeUpdate(sql);
		}
		catch(SQLException ex)
		{
		}
		
		return num;
	} 
	   
	   //delete
	public int executeDelete(String sql)
	{
		int num = 0;
		try
		{
			conn = DriverManager.getConnection(DatabaseConnStr,LogId,LogPass);
			Statement stmt = conn.createStatement();
			num = stmt.executeUpdate(sql);
		}
		catch(SQLException e)
		{
		}
	
		return num;
	}
	
	   //update
	public int executeUpdate(String sql)
	{
		int num = 0;
		try
		{
			conn = DriverManager.getConnection(DatabaseConnStr,LogId,LogPass);
			Statement stmt = conn.createStatement();
			num = stmt.executeUpdate(sql);
		}
		catch(SQLException e)
		{
		}
	
		return num;
	}
	   
	   //close
	public void CloseDataBase()
	{
		try
		{
			conn.close();
		}
		catch(Exception e)
		{
		}
	}
}