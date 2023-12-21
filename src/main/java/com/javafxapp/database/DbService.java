package com.javafxapp.database;

import com.javafxapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbService
{
	private final Connection connection;
	
	public DbService() throws SQLException
	{
		this.connection = DbConnector.getConnection();
	}
	
	public void addUserToDb(User user) throws Exception, SQLException
	{
		String login = user.getLogin();
		String password = user.getPassword();
		if (login.isEmpty() || password.isEmpty())
		{
			throw new Exception("login or password can not be empty");
		}
		try
		{
			String query = "INSERT INTO user(login, password) VALUES ('" + login + "', '" + password + "')";
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
		}
		catch (SQLException sqlException)
		{
			throw new SQLException("Couldn't add a user: " + sqlException);
		}
	}
}
