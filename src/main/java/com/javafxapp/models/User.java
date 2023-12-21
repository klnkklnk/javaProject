package com.javafxapp.models;

public class User
{
	private final String login;
	private final String password;
	
	public User(String login, String password)
	{
		this.login = login.trim();
		this.password = HashService.convertTextToHash(password.trim());
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public String getPassword()
	{
		return password;
	}
}
