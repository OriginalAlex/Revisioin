package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	Parent root;
	Scene scene;
	static Stage stage;

	public void changeScene(String title, String location, int width, int height) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(location));
			stage.setScene(new Scene(root, width, height));
			stage.setTitle(title);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		try {
			this.root = FXMLLoader.load(getClass().getResource("Style.fxml"));
			this.scene = new Scene(this.root, 450, 150);
		} catch (IOException e) {
			System.out.println("IO EX");
		}
		stage.setScene(this.scene);
		stage.show();
		stage.setTitle("Revision Menu!");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
