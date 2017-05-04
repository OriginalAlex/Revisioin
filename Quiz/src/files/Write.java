package files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Write {

	private Writer pw;
	
	public Write(File file) {
		try {
			this.pw = new BufferedWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String question, String answer) {
		try {
			pw.write(System.getProperty("line.separator") + question + "|||" + answer);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
