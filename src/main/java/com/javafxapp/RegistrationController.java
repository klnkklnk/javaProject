package com.javafxapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController
{
	@FXML
	private Button registrationButton;
	@FXML
	private TextField loginField;
	@FXML
	private PasswordField passwordField;
	
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
