package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Read {
	
	private File file;
	
	public Read(File file) {
		this.file = file; 
	}
	
	public Map<String, String> qAndA() {
		Map<String, String> pairs = new HashMap<String, String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) { // I suppose we can split the question/answer pair with a triple vertical bar
				if (line.contains("|||")) {
					String[] parts = line.split("\\|\\|\\|");
					pairs.put(parts[0], parts[1]);
				}
			}
		} catch (IOException e) {
			System.out.println("io");
		}
		return pairs;
	}

}
