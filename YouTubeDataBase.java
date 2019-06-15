import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class YouTubeDataBase {
	public static void main(String[] args) throws IOException {
		HashTable<Video> data = new HashTable<Video>(75);
		BST<Video> bst = new BST<>(); 

		System.out.println("Welcome to the YouTube DataBase! ");
		System.out.println("We are now reading data from our database ...\n");
		String inputFile = "data.txt";

		File file = new File(inputFile);
		Scanner fileRead = new Scanner(file);
		// File reading Error: 0
		if (!file.exists()) {
			System.out.println("Error reading the data. Exit code: 0");
			System.exit(0);
		}

		// Data Processing
		String videoURL;
		String videoName;
		String publisher;
		String views;
		String year;

		while (fileRead.hasNext()) {
			videoURL = fileRead.nextLine();
			videoName = fileRead.nextLine();
			publisher = fileRead.nextLine();
			year = fileRead.nextLine();
			views = fileRead.nextLine();

			Video tempVideo = new Video(videoURL, videoName, publisher, year, views);
			data.insert(tempVideo);
			bst.insert(tempVideo);
		}

		// UI part
		String tempurl, tempname, temppublisher, tempyear, tempviews;
		Scanner userInput = new Scanner (System.in);
		printSelection();
		String userChoice = userInput.next();
		userInput.nextLine();

		while (!userChoice.equalsIgnoreCase("x")) {
			
			if (userChoice.equalsIgnoreCase("A")) {
				System.out.println("\nNow adding a video");
				System.out.print("Enter the URL of the video: ");
				tempurl = userInput.nextLine();
				System.out.print("Enter the name of the video: ");
				tempname = userInput.nextLine();
				System.out.print("Enter the publisher of the video: ");
				temppublisher = userInput.nextLine();
				System.out.print("Enter the year of the video: ");
				tempyear = userInput.nextLine();
				System.out.print("Enter the views of the video: ");
				tempviews = userInput.nextLine();
				Video tempVideo = new Video (tempurl, tempname, temppublisher, tempyear, tempviews);
				data.insert(tempVideo); // add to HashTable
				bst.insert(tempVideo); // add to BST
				System.out.println();
				
			} else if (userChoice.equalsIgnoreCase("D")) {
				System.out.println("\nNow deleting a video ... ");
				System.out.print("Enter the URL of the video: ");
				tempurl = userInput.nextLine();
				System.out.print("Enter the name of the video: ");
				tempname = userInput.nextLine();
				Video tempVideo = new Video (tempurl, tempname);
				
				//should call remove method to remove data
				
			} else if (userChoice.equalsIgnoreCase("S")) {
				System.out.println("");

			} else if (userChoice.equalsIgnoreCase("L")) {
				System.out.print("\n*** Here is the list ***");
				bst.inOrderPrint(); // print table to display the full list

			} else {
				// Invalid input
				System.out.println("\nInvalid Input!");

			}
			printSelection();
			userChoice = userInput.next();

		}
		System.out.println("\nGood Bye!");  // user enter x to exit the program
		userInput.close();
		fileRead.close();

		PrintWriter output = new PrintWriter(new File("output.txt"));
		// write data to file
		for (int i = 0; i < data.tableSize(); i++) {
			
			output.println();
		}
		output.close();
	}

	public static void printSelection() {
		System.out.println("A. Let me add a new video! ");
		System.out.println("D. Ehhh, I want to delete a video.");
		System.out.println("S. I wanna search something ...");
		System.out.println("L. List everything for me!");
		System.out.println("X. Exit");
		System.out.print("\nPlease select your choice: ");
	}
}
