import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class YouTubeDataBase {

	private static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	private static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	
	public static void main(String[] args) throws IOException {
		HashTable<Video> h = new HashTable<Video>(75);//HashTable by URL
		BST bst1 = new BST(true);//BST by URL
		BST bst2 = new BST(false);//BST by video name

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
			h.insert(tempVideo);
			bst1.insert(tempVideo);
			bst2.insert(tempVideo);
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
				while (true) {
					System.out.print("Enter the URL of the video: ");
					tempurl = userInput.nextLine();
					if (!tempurl.startsWith("https://www.youtube.com/watch?v=")) {
						System.out.println("The input url doesn't have youtube host and path. They are added automatically");
						tempurl = "https://www.youtube.com/watch?v=" + tempurl;
						System.out.println("The url stored in database is: " + tempurl);
					}
					System.out.print("Enter the name of the video: ");
					tempname = userInput.nextLine();
					System.out.print("Enter the publisher of the video: ");
					temppublisher = userInput.nextLine();
					System.out.print("Enter the year of the video: ");
					tempyear = userInput.nextLine();
					if (!isInteger(tempyear)) {
						System.out.println("\"Year\" needs to be a non-negative integer");
						System.out.println("Please provide info again:");
						continue;
					}
					System.out.print("Enter the views of the video: ");
					tempviews = userInput.nextLine();
					if (!isInteger(tempviews)) {
						System.out.println("\"Views\" needs to be a non-negative integer");
						System.out.println("Please provide info again:");
						continue;
					} else {
						break;
					}
				}
				Video tempVideo = new Video (tempurl, tempname, temppublisher, tempyear, tempviews);
				//Make sure primary key is unique
				if (bst1.insert(tempVideo)) {
					h.insert(tempVideo); // add to HashTable h1
					bst2.insert(tempVideo); // add to BST1
					System.out.println("Added successfully!");
				} else {
					System.out.println("URL <" + tempurl + "> already exists. Added failed");
				}
				System.out.println();
				
			} else if (userChoice.equalsIgnoreCase("D")) {
				System.out.println("\nNow deleting a video ... ");
				System.out.print("Enter the URL of the video: ");
				tempurl = userInput.nextLine();
				if (!tempurl.startsWith("https://www.youtube.com/watch?v=")) {
					System.out.println("The input url doesn't have youtube host and path. They are added automatically");
					tempurl = "https://www.youtube.com/watch?v=" + tempurl;
					System.out.println("The url to remove in database is: " + tempurl);
				}
				Video tempVideo = new Video (tempurl, "unknown name");
				//should call remove method to remove data
				Video v = h.search(tempVideo);
				if(v == null) {
					System.out.println("The video you are looking for is not in the database!");
				} else {
					bst1.remove(v);
					bst2.remove(v);
            		h.remove(v);
            		System.out.println("The video: <" + v.getName() +"> has been removed!");
				}
			} else if (userChoice.equalsIgnoreCase("S")) {
				//Sub menu here
				System.out.println("Would you like to search using URL or video name?");
				System.out.println("Enter U for URL, otherwise, the program will search by name! ");
				Video tempVideo = new Video();
				String searchResponse = userInput.nextLine();
				if(searchResponse.equalsIgnoreCase("u")) {
					System.out.println("Now searching based on URL! ");
					System.out.println("Enter the URL please :");
					String urlFromUser = userInput.nextLine();
					if (!urlFromUser.startsWith("https://www.youtube.com/watch?v=")) {
						System.out.println("The input url doesn't have youtube host and path. They are added automatically");
						urlFromUser = "https://www.youtube.com/watch?v=" + urlFromUser;
						System.out.println("The url to search in database is: " + urlFromUser);
					}
					tempVideo.setUrl(urlFromUser);
					Video v = h.search(tempVideo);
					if (v == null) {
						System.out.println("Cannot find video with url <" + urlFromUser +">");
					} else {
						System.out.println("Found video with url <" + urlFromUser + "> below:");
						System.out.println(v);
					}
				}else {
					System.out.println("Now searching based on video names! ");
					System.out.println("Enter the video name please :");
					String nameFromUser = userInput.nextLine();
					tempVideo.setName(nameFromUser);
					ArrayList<Video> al = bst2.searchByVideoName(tempVideo);
					if (al.size() == 0) {
						System.out.println("Cannot find video with name <" + nameFromUser + ">");
					} else {
						System.out.println("Found video with name <" + nameFromUser + "> below:");
						for (int i = 0; i < al.size(); i++) {
							System.out.println(al.get(i));
						}
					}
				}
				System.out.println("");

			} else if (userChoice.equalsIgnoreCase("L")) {
				System.out.println("Now printing the database! ");
				System.out.println("Enter 'U' to print unsorted data, ");
				System.out.println("'R' to print the data sorted by URL,");
				System.out.println("otherwise, the program will print data sorted by video names.");
				String printResponse = userInput.nextLine();
				if(printResponse.equalsIgnoreCase("U")) {
					h.printTable();
				}else if(printResponse.equalsIgnoreCase("R")) {
					bst1.inOrderPrint();
				}else {
					bst2.inOrderPrint();
				}
			} else if (userChoice.equalsIgnoreCase("W")){
				System.out.println("Now writing data into output.text....");
				PrintWriter output = new PrintWriter(new File("output.txt"));
				//open a file for read
				for(int i = 0; i < h.tableSize(); i++) {
					List<Video> videos = h.getElement(i);
					videos.placeIterator();
					// get through list get each video to print to file
					for(int j = 0; j < videos.getLength(); j++) {
						//write to a file
						output.println(videos.getIterator());
						videos.advanceIterator();
					}
				}
				output.close();
				System.out.println("Writing done!");
			} else {
				// Invalid input
				System.out.println("\nInvalid Input!");

			}
			printSelection();
			userChoice = userInput.next();
			userInput.nextLine();

		}
		System.out.println("\nGood Bye!");  // user enter x to exit the program
				
		PrintWriter output = new PrintWriter(new File("autosaved.txt"));
		//open a file for read
		for(int i = 0; i < h.tableSize(); i++) {
			List<Video> videos = h.getElement(i);
			videos.placeIterator();
			// get through list get each video to print to file
			for(int j = 0; j < videos.getLength(); j++) {
				//write to a file
				output.println(videos.getIterator());
				videos.advanceIterator();
			}
		}
		
		output.close();
		userInput.close();
		fileRead.close();
	}

	public static void printSelection() {
		System.out.println("A. Let me add a new video! ");
		System.out.println("D. Ehhh, I want to delete a video.");
		System.out.println("S. I wanna search something ...");
		System.out.println("L. List everything for me!");
		System.out.println("W. write everything into a file!");
		System.out.println("X. Exit");
		System.out.print("\nPlease select your choice: ");
	}
}
