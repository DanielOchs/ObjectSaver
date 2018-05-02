package json.io.datareader;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class DataReader {
	// use this class to read message content from JSON files
	public String getFileWithUtil(String fileName) throws IOException {
		String result = "";
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName), "ISO-8859-1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
