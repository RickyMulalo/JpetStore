package Scripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Read_Data {

	public static  Map<String, String> readUserDetailsFromFile(String filePath) {
	     Map<String, String> userDetails = new HashMap<>();
	     try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	         String line;
	         while ((line = br.readLine()) != null) {
	             String[] parts = line.split("=");
	             if (parts.length >= 2) {
	                 String key = parts[0];
	                 String value = parts[1];
	                 userDetails.put(key, value);
	             }
	         }
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     return userDetails;
	 }
}