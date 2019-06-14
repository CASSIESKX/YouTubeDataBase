import java.util.Scanner;
import java.io.*;

public class YouTubeDataBase {
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner input = new Scanner(new File("data.txt"));
		String fullURL, url ="", title="", publisher="";
		int year, views;
		while(input.hasNextLine()) {
			fullURL = input.nextLine();
			for(int i = 0; i < fullURL.length(); i++) {
				if (fullURL.charAt(i)==('=')) {
					url = fullURL.substring(i+1, fullURL.length() - 1);
				}
			}
			//System.out.println("URL: " + url);
			
			title = input.nextLine();
			//System.out.println("Title: " + title);
			
			publisher = input.nextLine();
			//System.out.println("Publisher: " + publisher);
			
			year = input.nextInt();
			//System.out.println("Year" + year);
			
			views = input.nextInt();
			//System.out.println("Views: " + views);
			
			if(input.hasNextLine()) {
				input.nextLine();
			}
			
			// create a new object & add data
			
		}	
		input.close();
		
		PrintWriter output = new PrintWriter(new File("output.txt"));
		// write data to file
		output.println();
		output.close();
	}

}
