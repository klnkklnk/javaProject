package com.javafxapp;
import com.javafxapp.database.DbService;
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
					if (!controller.getLoginField().getCharacters().toString().isEmpty())
					{
							primaryStage.close();
							primaryStage.setScene(setSuccessfulAuthScene());
							primaryStage.show();
					}
					else
					{
						primaryStage.close();
						primaryStage.setScene(setUnsuccessfulAuthScene());
						primaryStage.show();
					}
				}
				catch (IOException exception)
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
					System.out.println("registration button in auth is working");
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
		
		
		return new Scene(root, 250, 250);
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
				System.out.println("registration button is working");
				primaryStage.close();
				primaryStage.setScene(setAuthScene(primaryStage));
				primaryStage.show();
				}
				catch (Exception exception)
				{
					throw new RuntimeException(exception);
				}
			}
		});
		return new Scene(root, 250, 250);
	}
	
	public Scene setSuccessfulAuthScene() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafxapp/SuccessfulAuth.fxml"));
		Parent root = loader.load();
		return new Scene(root, 250, 250);
	}
	public Scene setUnsuccessfulAuthScene() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/javafxapp/UnsuccessfulAuth.fxml"));
		Parent root = loader.load();
		return new Scene(root, 250, 250);
	}
}