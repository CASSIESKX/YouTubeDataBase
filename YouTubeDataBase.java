import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class YouTubeDataBase {
public static void main (String [] args) throws IOException{
	HashTable <Video> data = new HashTable <Video> (75);
	System.out.println("Welcome to the YouTube DataBase! ");
	System.out.println("We are now reading data from our database ... ");
	String inputFile = "data.txt";
	File file = new File (inputFile);
	Scanner fileRead = new Scanner (file);
	//File reading Error: 0
	if(!file.exists()) {
		System.out.println("Error reading the data. Exit code: 0");
		System.exit(0);
	}
	
	//Data Processing
	String videoURL;
	String videoName;
	String publisher;
	String views;
	String year;
	while(fileRead.hasNext()) {
		videoURL = fileRead.nextLine();
		videoName = fileRead.nextLine();
		publisher = fileRead.nextLine();
		year = fileRead.nextLine();
		views = fileRead.nextLine();
		
		Video tempVideo = new Video(videoURL, videoName, publisher, year, views);
		data.insert(tempVideo);
	}
	
	//UI part
	String userChoice = "";
	while(!userChoice.equalsIgnoreCase("x")){
		printSelection();
		if(userChoice.equalsIgnoreCase("A")) {
			System.out.println("Now adding a video");
			String tempurl, tempname, temppublisher, tempyear, tempviews;
			Scanner userInput = new Scanner (System.in);
			System.out.println("Enter the URL of the video: ");
			tempurl = userInput.nextLine();
			System.out.println("Enter the name of the video: ");
			tempname = userInput.nextLine();
			System.out.println("Enter the publisher of the video: ");
			temppublisher = userInput.nextLine();
			System.out.println("Enter the year of the video: ");
			tempyear = userInput.nextLine();
			System.out.println("Enter the views of the video: ");
			tempviews = userInput.nextLine();
			Video tempVideo = new Video (tempurl, tempname, temppublisher, tempyear, tempviews);
			data.insert(tempVideo);
		}else if(userChoice.equalsIgnoreCase("D")) {
			
		}else if(userChoice.equalsIgnoreCase("S")) {
			
		}else if(userChoice.equalsIgnoreCase("L")) {
			
		}else if(!userChoice.equalsIgnoreCase("X")) {
			//Invalid input
			System.out.println("Invalid Input!");
		}
	}
	
}

public static void printSelection () {
	System.out.println("Please select your choice: ");
	System.out.println("A. Let me add a new video! ");
	System.out.println("D. Ehhh, I want to delete a video.");
	System.out.println("S. I wanna search something ...");
	System.out.println("L. List everything for me!");
}
}
