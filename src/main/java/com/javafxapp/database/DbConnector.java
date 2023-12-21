package com.javafxapp.database;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector
{
	private static String url;
	private static String username;
	private static String password;
	private static String driverName;
	private static String timeZone;
	private static String useSSL;
	private static String urlString;
	private static Connection connection;
	private static void defineProperties() throws Exception
	{
		Properties properties = new Properties();
		try(InputStream in = Files.newInputStream(Paths.get("src/main/resources/database/database.properties")))
		{
			properties.load(in);
		}
		url = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
		driverName = properties.getProperty("driverName");
		timeZone = properties.getProperty("timeZone");
		useSSL = properties.getProperty("useSSL");
		urlString = url + "?serverTimezone=" + timeZone + "&useSSL=" + useSSL;
	}
	public static Connection getConnection() throws SQLException
	{
		try
		{
			defineProperties();
			try
			{
				Class.forName(driverName);
				try
				{
					connection = DriverManager.getConnection(urlString, username, password);
				}
				catch (SQLException sqlException)
				{
					System.out.println("Failed to create the database connection: " + sqlException);
				}
			}
			catch (ClassNotFoundException classNotFoundException)
			{
				System.out.println("Driver not found: " + classNotFoundException);
			}
		}
		catch (Exception exception)
		{
			System.out.println("The file with the properties not found");
		}
		return connection;
	}
}
