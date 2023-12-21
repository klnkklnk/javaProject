package com.javafxapp;
import com.javafxapp.database.DbService;
import com.javafxapp.models.AlertMessage;
import com.javafxapp.models.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class AuthApplication extends Application
{
	public static void main(String[] args) throws Exception
	{
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		try
		{
			InputStream iconStream = getClass().getResourceAsStream("images/icon.png");
			assert iconStream != null;
			Image image = new Image(iconStream);
			primaryStage.getIcons().add(image);
			primaryStage.setTitle("JavaFX App");
			primaryStage.setScene(setAuthScene(primaryStage));
			primaryStage.show();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public Scene setAuthScene(Stage primaryStage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafxapp/AuthApplication.fxml"));
		Parent root = loader.load();
		AuthController controller = loader.getController();
		controller.getLogInButton().setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent actionEvent)
			{
				try
				{
					AlertMessage alert = new AlertMessage();
					
					String login = controller.getLoginField().getText().trim();
					String password = controller.getPasswordField().getText().trim();
					DbService dbService = new DbService();
					
					if (login.isEmpty() || password.isEmpty())
					{
						alert.errorMessage("Все поля должны быть заполнены");
					}
					else if (!dbService.verifyUser(new User(login, password)))
					{
						alert.errorMessage("Неверный логин или пароль");
					}
					else
					{
						primaryStage.close();
						primaryStage.setScene(setSuccessfulAuthScene());
						primaryStage.show();
					}
				}
				catch (IOException | SQLException exception)
				{
					throw new RuntimeException(exception);
				}
			}
		});
		controller.getRegistrationButton().setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent actionEvent)
			{
				try
				{
					primaryStage.close();
					primaryStage.setScene(setRegistrationScene(primaryStage));
					primaryStage.show();
				}
				catch (IOException exception)
				{
					throw new RuntimeException(exception);
				}
				
			}
		});
		
		
		return new Scene(root, 500, 500);
	}
	
	public Scene setRegistrationScene(Stage primaryStage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafxapp/Registration.fxml"));
		Parent root = loader.load();
		
		RegistrationController registrationController = loader.getController();
		registrationController.getRegistrationButton().setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent actionEvent)
			{
				try
				{
					AlertMessage alert = new AlertMessage();
					String login = registrationController.getLoginField().getText().trim();
					String password = registrationController.getPasswordField().getText().trim();
					DbService dbService = new DbService();
					if (login.isEmpty() || password.isEmpty())
					{
						alert.errorMessage("Все поля должны быть заполнены");
					}
					else
					{
						try
						{
							dbService.addUserToDb(new User(login, password));
							primaryStage.close();
							primaryStage.setScene(setAuthScene(primaryStage));
							primaryStage.show();
						}
						catch (SQLException sqlException)
						{
							alert.errorMessage("Пользователь с таким логином уже существует");
						}
					}
				}
				catch (Exception exception)
				{
					throw new RuntimeException(exception);
				}
			}
		});
		return new Scene(root, 500, 500);
	}
	
	public Scene setSuccessfulAuthScene() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafxapp/SuccessfulAuth.fxml"));
		Parent root = loader.load();
		return new Scene(root, 500, 500);
	}
}