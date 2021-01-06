//Jacinthe Beaudry (40126080)
//COMP249
//Assignment #3 
//December 4th 2020



package pack1;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import pack1.CellPhone;
import pack1.CellList;


/** 
 * 
 * This class creates cell phone list.
 * It reads a .txt file and outputs a list.
 * it then tests the methods from CellList.java
 * 
 * @author Jacinthe Beaudry (40126080)
 * @version 1.0
 * 
 */


public class CellListUtilization {
	
	static int counter = 0;
	/**
	 * This method reads the .txt file with a buffered reader
	 */

	
	public static void openFile(CellList list) throws FileNotFoundException, IOException{
		
		BufferedReader br;     //buffer variable
		String next;
		br = new BufferedReader(new FileReader("Cell_Info.txt"));
		next = br.readLine();
		ArrayList<String> finalList = new ArrayList<String>();            //store phones after they are sorted
		ArrayList<String> collectedPhones = new ArrayList<String>();           //stores all the phones
	     
		//the buffer reads while there are Strings left
		while(next != null) {
			collectedPhones.add(next);
			next = br.readLine();
			counter++;
		}
		br.close();     //buffer closed

	
		if(collectedPhones.isEmpty())
			System.out.println("No cell phones found.");
		else {
			for(String requestSerial : collectedPhones) {
				long collectedSerial;
				collectedSerial = list.find(Long.parseLong(requestSerial.substring(0,requestSerial.indexOf(" ")))).getCell().getSerial();
				
				if(collectedPhones.contains(collectedSerial)) {   //if the list contains the  number
					finalList.add(requestSerial);
				}
				
			}
		}
	}

// the method to search course in CourseList
public static void findCell(CellList cl) {
	int searches = 0;
	boolean done = false;
	while(!done) {
		searches++;
		System.out.println("Please enter the serial number of the phone that you wish to find: ");
		Scanner s = new Scanner(System.in);
		long serial = s.nextLong();			//for user to search the list
		if (cl.contains(serial)) {
			System.out.println("The cell phone with the serial number "+serial+" exists in the list.");
			System.out.println(searches+" searches were done before the phone was found.");
		}
		else {
			System.out.println("There is no such phone in the list.");
		}	
        System.out.println("Search again?: Y/N");
        if(s.next().equals("N"))
            done = true;
        
	}
	
}	




//main method
//reads the file and outputs a ist of cellphones

public static void main(String[] args) {
	
	CellList list = new CellList();               //stores the phones in a list
	CellPhone cp = new CellPhone();                        // cell Phone object
	BufferedReader br;
	String next;                                    //stores what the buffer will read
	Scanner s = new Scanner(System.in);
	

    //Opens the .txt file 
	try {
		br = new BufferedReader(new FileReader("Cell_Info.txt"));   //buffered reader
		next = br.readLine();
		while (next != null) {
			
			StringTokenizer nextString = new StringTokenizer(next.toString());
			if (nextString.countTokens() == 4) {    //when there are 4 tokens in the String
				cp.setSerial(Long.parseLong(nextString.nextToken()));     //assigns the serial
				cp.setBrand(nextString.nextToken());        //assigns the brand
				cp.setPrice(Double.parseDouble(nextString.nextToken()));       //assigns the price
				cp.setYear(Integer.parseInt(nextString.nextToken()));       //assigns the year
				list.addTostart(new CellPhone(cp.getSerial(), cp.getBrand(), cp.getPrice(), cp.getYear()));
			}
			
			if (next.length() == 0) {
				if (list.contains(cp.getSerial()))
					continue;
				else {
					list.addTostart(new CellPhone(cp.getSerial(), cp.getBrand(), cp.getPrice(), cp.getYear()));
				}
			}
			next = br.readLine();
		}
	} catch (FileNotFoundException e) {   //error for file nout found
		System.out.println("File is not found.");
	} catch (IOException e) {
		e.printStackTrace();
	}
	

	
	
	
	System.out.print("The current size of the list is "
			+  list.getSize() +". Here are the contents of the list.\n"
					+ "========================================\n");
	list.showContents();
	
	//only necessary if using a different file name
    // open request file, and print the outcome
	/*boolean end=false;
	while(!end) {
		try{
			openFile(list);
		}catch(FileNotFoundException e) {
			System.out.println("File is not found.");
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Request again?: Y/N");
		if (s.next().equals("N"))
			end = true;
	}
	
*/
	// search the list
	findCell(list);
	
	//
    
	//testing
    System.out.println("\nTest: parameterized constructor of the CellPhone class: ");
    CellPhone cp1 = new CellPhone(123456, "Google", 300.90, 2016);
    System.out.println(cp1);
    System.out.println("\nTest the copy constructor and clone() method of Course class");
    CellPhone cp2 = new CellPhone(127457, "Samsung", 700.90, 2018);
    CellPhone cp4 = new CellPhone (cp1, 122222);
    CellPhone cp3 = cp2.clone();
    System.out.println("Using the copy constructor: "+cp4+" \n And the clone():");
    System.out.println(cp2);
    System.out.println(cp3);
    System.out.println("\nTest equals() method of Course class:");
    if(cp2.equals(cp3))
        System.out.println("cp2 is equal to cp3");
    else
        System.out.println("cp2 is not equal to cp3");
    System.out.println("\nTest the size of CellList:");
    System.out.println(list.getSize());
    System.out.println("\nTest the copy constructor of CourseList");
    CellList list2 = new CellList(list);
    System.out.println(list.getSize());
    list2.showContents();
    System.out.println("\nTest the equals() method:");
    if(list.equals(list2))
        System.out.println("l1 is equal to l2");
    else
        System.out.println("l1 is not equal to l2");
    System.out.println("\nTest the insertAtindex() method:");
    list.insertAtIndex(cp3, 4);
    list.showContents();
    System.out.println("\nTest the deleteFromindex() method:");
    list.deleteFromIndex(4);
    list.showContents();
    System.out.println("\nTest the insertAtindex() method when index is 0:");
    list.insertAtIndex(cp3, 0);
    list.showContents();
    System.out.println("\nTest the deleteFromindex() method when index is 0:");
    list.deleteFromIndex(0);
    list.showContents();
    System.out.println("\nTest the insertAtindex() method when index is size-1:");
    list.insertAtIndex(cp3, list.getSize()-1);
    list.showContents();
    System.out.println("\nTest the deleteFromindex() method when index is size-1:");
    list.deleteFromIndex(list.getSize()-2);
    list.showContents();
    System.out.println("\nTest the deleteFromStart() method:");
    list.deleteFromStart();
    list.showContents();
    System.out.println("\nTest the replaceAtIndex() method:");
    list.replaceAtIndex(cp3,0);
    list.showContents();
    System.out.println("\nTest the replaceAtIndex() method:");
    list.replaceAtIndex(cp3,3);
    list.showContents();
   


    System.out.println("\n------------------------------");
    System.out.println("Thank you for using Jacinthe's program! ");
}

}
