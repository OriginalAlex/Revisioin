package application;

import java.io.File;

import files.Read;
import files.Write;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class Creation {
	
	@FXML
	private TextArea question;

	@FXML
	private TextArea answer;

	@FXML
	private Text status;

	private File write; // the file which the user selects
	private Write writeClass;
	private Main main = Main.getInstance();
		
	public void addQuestion() {

		String q = question.getText();
		String a = answer.getText();
		
		if (q.contains("|||") || a.contains("|||")) {
			return;
		}

		if (writeClass == null || write == null) {
			status.setText("Status: No File Set!");
		} else {
			writeClass.write(q, a.toLowerCase().trim());
		}
		
		question.setText(null); answer.setText(null);
	}
	
	public void onClick() { // Get the file to read/write to
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Files (*.txt", "*.txt");
		fc.getExtensionFilters().add(extensionFilter);
		String userDirectory = System.getProperty("user.home");
		File userDir = new File(userDirectory);
		if (!userDir.canRead()) {
			userDir = new File("c:/");
		}

		fc.setInitialDirectory(userDir);

		File chosenFile = fc.showOpenDialog(null);
		this.write = chosenFile;
		if (chosenFile != null) {
			status.setText("Status: Dir Set!");
			this.writeClass = new Write(write);
		} else {
			status.setText("Status: Wrong Dir");
		}
	}
	
	public void changeToMenu() {
		main.changeScene("Revision Menu", "Style.fxml", 450, 150);
	}

	public void changeToQuiz() {
		main.changeScene("Revise!", "quiz.fxml", 411, 291);
	}

}
