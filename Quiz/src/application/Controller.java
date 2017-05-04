package application;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

	@FXML
	private ImageView image;
	
	private Main main = new Main();
	

	public void initialize() {
		image.setImage(new Image("/resources/download.jpg"));
	}

	public void changeToAdd() {
		main.changeScene("Revision Creator Tool", "create.fxml", 320, 261);
	}

	public void changeToQuiz() {
		main.changeScene("Revise!", "quiz.fxml", 411, 291);
	}

}
