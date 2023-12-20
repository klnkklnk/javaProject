package com.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController
{
	@FXML
	private Button logInButton;
	@FXML
	private Button registrationButton;
	@FXML
	private TextField loginField;
	@FXML
	private PasswordField passwordField;
	public Button getLogInButton()
	{
		return logInButton;
	}
	
	public Button getRegistrationButton()
	{
		return registrationButton;
	}
	
	public TextField getLoginField()
	{
		return loginField;
	}
	
	public PasswordField getPasswordField()
	{
		return passwordField;
	}
}
