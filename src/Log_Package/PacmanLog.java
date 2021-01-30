package log_package;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PacmanLog {

	/*private static void createLogFile() {
		// EB move here
		String logFileName = "src\\log_files\\log_messages.txt";
		try {
		     File myObj = new File(logFileName);
		     if (myObj.createNewFile()) {
		       System.out.println("File created: " + myObj.getName());
		     } else {
		       System.out.println("File already exists.");
		     }
		   } catch (IOException e) {
		     System.out.println("An error occurred "+e.getMessage());
		     e.printStackTrace();
		   }
		}*/
	//EB
	static Boolean isFirst = true;

	/**
	 * This function writes into a text file: the name of the function and the message it passes
	 * @param functionName
	 * @param msg
	 */
	public static void log(String functionName, String msg) {
		FileWriter my_writer;
		// EB move here too and also move to a log package
		String log_file_name = "src\\log_files\\log_messages.txt";
		if(isFirst == true) {
			try {
				my_writer = new FileWriter(log_file_name);
				//BufferedWriter write = new BufferedWriter(my_writer); // this is the type so we can write to it.
				my_writer.write("");
				my_writer.close();
				my_writer = new FileWriter(log_file_name);
				BufferedWriter write = new BufferedWriter(my_writer);
				write.write(""); // write empty string to delete everything   ​
				write.close(); 
				isFirst = false;
			} catch (IOException e) {
				System.out.println("An error occurred when emptying log file");
				e.printStackTrace();
			}
		}
		try {
			my_writer = new FileWriter(log_file_name, true);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			//System.out.println(dtf.format(now));
			my_writer.write(dtf.format(now) + ":" + functionName + ' ' + msg);
			my_writer.write("\n");
			my_writer.close();
			//System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred when writing log file");
			e.printStackTrace();
		}
	}
}
