package com.javafxapp.database;

import com.javafxapp.models.HashService;
import com.javafxapp.models.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

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
		String password = HashService.generatePasswordHash(user.getPassword());
		
		if (login.isEmpty() || password.isEmpty())
		{
			throw new Exception("login or password can not be empty");
		}
		try
		{
			String query = "INSERT INTO user (login, password) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, login);
			statement.setString(2, password);
			statement.executeUpdate();
		}
		catch (SQLException sqlException)
		{
			throw new SQLException("Couldn't add a user: " + sqlException);
		}
	}
	
	public boolean verifyUser(User user)
	{
		try
		{
			String query = "SELECT password FROM user WHERE login=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getLogin());
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return HashService.validatePassword(user.getPassword(), resultSet.getString("password"));
 		}
		catch (SQLException | InvalidKeySpecException | NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}
}

