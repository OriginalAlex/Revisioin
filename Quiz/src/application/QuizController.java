package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import files.Read;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class QuizController {
	
	@FXML
	private Text questionsAsked;
	
	@FXML
	private Text questionsRight;
	
	@FXML
	private Text questionsWrong;
	
	@FXML
	private TextArea answer;
	
	@FXML
	private Text question;
	
	@FXML
	private Text time;
	
	private long timeSinceLastAnswer = 0;
	private String questionBeingAsked = null;
	private Main main = Main.getInstance();
	private Map<String, String> questions;
	
	
	public void nextQuestion() { // Assumes questions has been initialized...
		Random r = new Random();
		List<String> randomQuestion = new ArrayList<String>(questions.keySet());
		String rand = randomQuestion.get(r.nextInt(randomQuestion.size()));
		questionBeingAsked = rand;
		question.setText(questionBeingAsked);
		timeSinceLastAnswer = System.currentTimeMillis();
	}

	public void clear() {
		question.setText("Text");
		questionsWrong.setText("Questions Wrong: 0");
		questionsRight.setText("Questions Correct: 0");
		questionsAsked.setText("Questions Asked: 0");
		time.setText("Time Taken To Answer Last Question: 0ms");	
	}
	
	public void onSubmit() {
		String questionAsked = question.getText();
		String actualAnswer = questions.get(questionAsked);
		String input = answer.getText();
		long timeTaken = System.currentTimeMillis() - timeSinceLastAnswer;
		int rightAnswers = Integer.parseInt(questionsRight.getText().split(" ")[2]);
		int wrongAnswers = 	Integer.parseInt(questionsWrong.getText().split(" ")[2]);
		
		if (input.trim().toLowerCase().equals(actualAnswer)) { // they got it right...
			questionsRight.setText("Questions Correct: " + (rightAnswers+1));
		} else {
			questionsWrong.setText("Questions Wrong: " + (wrongAnswers+1));
		}
		questionsAsked.setText("Questions Asked: " + (rightAnswers+wrongAnswers+1));
		time.setText("Time Taken To Answer Last Question: " + timeTaken + "ms");
	}
	
	public void getQuestions(File file) {
		if (file == null) return;
		Read r = new Read(file);
		questions = r.qAndA();
		nextQuestion();
	}
	
	
	
	
	
	/*
	 * Quite behind the scene stuff
	 * Goes below
	 */
	

	
	
	
	public void onClick() { // Get the file to read/write to
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
		fc.getExtensionFilters().add(extensionFilter);
		String userDirectory = System.getProperty("user.home");
		File userDir = new File(userDirectory);
		clear();
		if (!userDir.canRead()) {
			userDir = new File("c:/");
		}

		fc.setInitialDirectory(userDir);

		File chosenFile = fc.showOpenDialog(null);
		if (chosenFile != null) {
			getQuestions(chosenFile);
		} 
	}
	
	public void changeToMenu() {
		main.changeScene("Revision Menu", "Style.fxml", 450, 150);
	}

	public void changeToAdd() {
		main.changeScene("Revision Creator Tool", "create.fxml", 320, 261);
	}

}
