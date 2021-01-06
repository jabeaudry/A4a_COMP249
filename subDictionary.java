/**
 * 
 * @author Jacinthe Beaudry (40126080)
 * COMP249	
 * Assignment #4
 * Part 1
 * Due Date: December 4th 2020
 *
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/** 
 * This class reads a text input that the user submits in the main method.
 * The words from the file are separated and all punctuation is removed.
 * Numbers, symbols and single letter words (except A and I) are removed.
 * The words are then capitalized and sorted in alphabetical order.
 * The class finally outputs a subDictionary.txt file with the words in alphabetical order.
 * All the word Strings are stored in ArrayList objects of type String. 
 * 
 * @author Jacinthe Beaudry (40126080)
 * @version 1.0
 * 
 */

public class subDictionary {
	
	
	/** 
	 * This method creates takes a String as input, which represents a file name.
	 * The method inputReader then opens the selected file and scans the words. 
	 * The punctuation is then removed, as well as numbers and symbols.
	 * The function returns an ArrayList object that contains Strings in alphabetical order.
	 */
	
	public static ArrayList<String> inputReader(String selectedFile){
		

		
		ArrayList<String> inWords = new ArrayList<String>();     //array that words the word as they appear in the text file
		ArrayList<String> orderedArray = new ArrayList<String>();    //array after it was sorted
		Scanner in = null;   //creates the scanner that will read the files
		//tries to open the file
		try {
			in = new Scanner(new FileInputStream(selectedFile));      //creates the scanner 
		}
		catch (FileNotFoundException e) {  //error
			System.out.println("File "+selectedFile+" was not found or "
					+ "couldn't be opened.");
			System.exit(0);
		}
		
		//reads every word and stores it as a String in the Arraylist
		while (in.hasNext()) {
			inWords.add(in.next());
		  }
		//removes the punctuation
		for (int j = 0; j < inWords.size(); j++) {
			String currentWord = inWords.get(j);
			currentWord = currentWord.toUpperCase();
			//checks for punctuation, removes if it applies
			if (currentWord.contains(",") || (currentWord.contains(".")) || (currentWord.contains("!"))|| (currentWord.contains("?")) || (currentWord.contains(":"))|| (currentWord.contains(";") || (currentWord.indexOf(',') != -1)) ){
			currentWord = currentWord.substring(0, (currentWord.length()-1));
			}
			
			//checks for '
			if (currentWord.contains("’")){
				currentWord = currentWord.substring(0, (currentWord.length()-2));
			}
	
			
			
			//sets the modified word, if applicable
			inWords.set(j, currentWord);
			
			//checks for numbers, removes any number
			if (((currentWord.indexOf('1') != -1) || (currentWord.indexOf('2') != -1) || (currentWord.indexOf('3')!= -1) || (currentWord.indexOf('4') != -1) || (currentWord.indexOf('5') != -1) || (currentWord.indexOf('6') !=-1) || (currentWord.indexOf('7') != -1) || (currentWord.indexOf('8') != -1) || (currentWord.indexOf('9') != -1) || (currentWord.indexOf('0') != -1))){
				inWords.remove(j);	
			}
			
			//check for the char = and removes it from the list
			
			if ((currentWord.contains("="))) {
				inWords.remove(j);
			}
			
			//checks for single letter words, only keeps A and I
			if ((currentWord.length() == 1) && !((currentWord.equals("A"))||(currentWord.equals("I")))) {
				inWords.remove(j);
			}
		
			
		inWords.trimToSize();
		}
		in.close(); //closes the scanner stream

		
		
		orderedArray = orderOperation(inWords);          //calls the method that sorts the ArrayList
		
		return orderedArray;
	}
	
	
	
	/** 
	 * This method creates takes an ArrayList and outputs it in a .txt file
	 * 
	 */
	
	
	public static void fileOutput(ArrayList<String> arrInput) {
		
		PrintWriter o = null; //creates the printwriter that will output the file
		//tries to  create the  output file
		try {
			o = new PrintWriter(new FileOutputStream("subDictionary.txt"));
		}
		catch (FileNotFoundException e) { //error
			System.out.println("The output file could not be created.");
			System.exit(0);
		}
		o.println("The document produced this sub-dictionary, which includes "+ arrInput.size() +" entries.\n\nA\n= =");  //prints out the first line which includes the word amount
		String previousChar = "a";    
		 for (String current : arrInput) { //cycles through the words
			 if (current.equals(arrInput.get(0))) {   //prints out the first word
				 o.println(arrInput.get(0));
			 }
			 else {   //prints out the rest of the words
				 if (current.substring(0,1).equals(previousChar)) {    //checks the first letter of each word
					 o.println(current);    //if the word has the same letter as the previous word, prints it on the next line
				 }
				 else {
					 o.println("\n"+current.substring(0,1) + "\n= =\n"+current);    //next letter, adds a letter seperation
				 }
			 }
			 previousChar = current.substring(0,1);      //changes the current first letter
		 }
		 System.out.println("The subDictionary.txt file has been created.");
		o.close();
	}
	
	/** 
	 * This method takes an ArrayList object of type String and alphabetically sorts it
	 * Returns the sorted ArrayList.
	 * 
	 */
	
	public static ArrayList<String> orderOperation(ArrayList<String> unordered){
		ArrayList<String> ordered = new ArrayList<String>();       //this ArrayList will store the ordered words
		for (String k : unordered) {   //cycles through the unordered arraylist
			String currentStr;  //this variable stores the current word being examined
			currentStr = k.toUpperCase();  //capitalizes each word
			if (currentStr.indexOf(',') != -1) {    //removes any other comma
				currentStr = currentStr.substring(0, (currentStr.length()-1));
			}
			if (ordered.size() != (0)) {    //if the ordered arraylist contains at least one word
				for (int m = 0; m < ordered.size();m++) {
					if (currentStr.contains("=")) {    //removes any = sign
						break;
					}
					
					String comparable = ordered.get(m);
					//if a String matches another String, it is not added to the ordered list twice
					if (currentStr.equals(comparable)) {
						break;
					}
					
					else if (comparable.compareTo(currentStr) < 0) {   //if the current String comes after a word in the ordered list
						
						if (m+1 == (ordered.size())) {
							ordered.add(currentStr);     //if this is the last word, it is placed at the end
							break;
						}
						continue;      //
						
					}
					else if (comparable.compareTo(currentStr) > 0) {    //if the current String comes before a word in the ordered list
						ordered.add(m,currentStr);    //the word is added at the current index
						break;
					}
					else {
						break;
					}
				}
			}
			else {
				ordered.add(currentStr);
			}
		}
		return ordered;
	}
	

	
	public static void main(String[] args) {
		//variables
		Scanner s = new Scanner(System.in); //scanner variable
		String fileToRead = null;    //will store the file name
		ArrayList<String> outputWords = new ArrayList<String>();
		
		
		
		System.out.println("----------------------------------------\n"
		+ "  Welcome to Jacinthe's Sub-Dictionary!\n"
		+ "----------------------------------------\n\n"
		+ "Please enter the name of the file you wish to read: ");
		fileToRead = s.next();   //stores the user input
		outputWords = inputReader(fileToRead); 
		fileOutput(outputWords);
		s.close();    //closes the scanner stream
		System.out.println("Thank you for using Jacinthe's Sub-Dictionary tool!"); //exit message
	}

}

