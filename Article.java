
import java.util.*;
import java.util.Scanner;
import java.io.*;

/* This program uses Red-Black tree to store and print data because Red-Black Tree is more 
	efficient in processing data than List Tree*/
	
public class Article {
    public static void main(String[] args) throws Exception {
		//Asks user for file and uses a scanner to read the file. 
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter file ending with .txt: ");
		String newsFile = keyboard.nextLine();
		File file = new File(newsFile); 
		Scanner newsReader = new Scanner(file);
		//Creates arraylists, Red-Black-Map Tree and variable to be used  in the program later. 
		MyRedBlackTreeMap<String, String> Map= new MyRedBlackTreeMap<String, String>();
		List<String> listString = new ArrayList<String>();
		List<String> integer = new ArrayList<String>();
		ArrayList<String> nonDuplicate = new ArrayList<String>();
		ArrayList<String> Duplicate = new ArrayList<String>();
		String body= new String();
		String number= new String();
		//Starts reading file using the scanner 
		while (newsReader.hasNextLine()) {
			String newString= (newsReader.nextLine());
			Scanner newScan = new Scanner(newString);
		/*Checks to see if the line of the file starts with <ID> or ends with </ID>
		if it does than add the integer in that line to the number varaible to be used 
		in th program later*/
			if (newString.startsWith("<ID>") && newString.endsWith("</ID>")) {
				while (newScan.hasNextLine()) {
					String nString= (newScan.nextLine());
					number=nString.replaceAll("[^0-9]", "");
				
				}
			/*Checks to see if the line of the file starts with <BODY> or ends with </BODY>
		if it does than add the all the words in that line to the arraylist, then iterate over 
		the arraylist and add the word to the newBody variable and add the newBody and number to 
		the Map, which gives the id number of each word*/
			} else if (newString.startsWith("<BODY>") || newString.endsWith("</BODY>")){
				while (newScan.hasNext()) {
					body= newScan.next();
					body = body.replace(",", "");
					body = body.replace("^\"|\"$", "");
					body = body.replaceAll("[\\s.]", "");
					listString.add(body);
					String newBody= new String();
					Iterator<String> iter = listString.iterator();
					while(iter.hasNext()){
						newBody = iter.next();
					}
					Map.put(newBody, number);
				} 
			/* If the string does not start or end with ID or BODY, than just iterate over the arraylist
			and add the string to Map along with number(or current id)*/
			} else {
				while (newScan.hasNext()) {
					body= newScan.next();
					body = body.replace(",", "");
					body = body.replaceAll("[\\s.]", "");
					body = body.replace("^\"|\"$", "");
					listString.add(body);
					String newBody= new String();
					Iterator<String> iter = listString.iterator();
					while(iter.hasNext()){
						newBody = iter.next();
					}
					Map.put(newBody, number);
				}
			}
		}
		/*Go through the listString arraylist and remove all the non-repetitive words 
		into a new arraylist*/
		String Word= new String();
		int count= 0;
		Iterator<String> itr = listString.iterator();
		while(itr.hasNext()){
			Word = itr.next();
			if(nonDuplicate.contains(Word)){
				count++;
			} else {
				nonDuplicate.add(Word);
			}
		}
		/* Compare the new araylist(with no duplicates) with the old arraylist(cotaining duplicates)
		and check how many times each word is repeated, store that in a string and print it. So the
		string printed containes the word along with its frequency*/
		System.out.println("EACH WORD FROM BODY WITH FREQUENCY");
		for(String Duplicates : nonDuplicate) {
			int counter = Collections.frequency(listString, Duplicates);
			String st= (Duplicates + ":" + counter);
			System.out.println(st);
		}
		//Iterate over the Map and print it which would be printed as word along with its ID
		System.out.println("EACH WORD FROM BODY WITH ID");
		Iterator<MapEntry<String, String>> iterate = Map.iterator();
		while(iterate.hasNext()) {
			System.out.println(iterate.next());
		}
	}
	
}