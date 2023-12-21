package com.javafxapp.models;

public class User
{
	private final String login;
	private final String password;
	
	public User(String login, String password)
	{
		this.login = login.trim();
		this.password = password.trim();
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
