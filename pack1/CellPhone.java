//Jacinthe Beaudry (40126080)
//COMP249
//Assignment #3 
//December 4th 2020



package pack1;
import java.util.InputMismatchException;
import java.util.Scanner;
/** 
 * 
 * This class creates CellPhone objects.
 * 
 * @author Jacinthe Beaudry (40126080)
 * @version 1.0
 * 
 */

public class CellPhone {
	
	private long serialNum;
	private String brand;
	private int year;
	private double price;
	
/** 
 * 
 * This main method CellPhone() creates cell phone objects.
 * @param serialNum long that stores a unique serial number
 * @param brand String that stores the brand
 * @param year int that stores the manufacturing year
 * @param price double that stores the price
 * 
 */
	
	
	public CellPhone() {
		this.serialNum = 0;
		this.brand = null;
		this.year = 0;
		this.price = 0;
	}
	
	public CellPhone(long serialNum, String brand,  double price, int year) {
		this.serialNum = serialNum;
		this.brand = brand;
		this.price = price;
		this.year = year;
	}
	
	//mutators and accessors
	
	public long getSerial() {
		return serialNum;
	}
	
	public void setSerial(long se) {
		serialNum = se;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String br){
		brand = br;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int ye) {
		year = ye;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double pr) {
		price = pr;
	}
	
	//copy constructor
	public CellPhone(CellPhone c, long newSerial) {
		this.serialNum = newSerial;
		this.brand = c.brand;
		this.price = c.price;
		this.year= c.year;
	}
	
	//clone method
	//includes a user prompt to enter a new serial number
	//needs to be a valid long
	public CellPhone clone() {
		Scanner cIn = new Scanner(System.in);
		boolean correct = false;
		long newSerial = 0;
		
		while (!correct) {
		try
		{
		System.out.println("Please create a new cell phone serial number: ");
		
		newSerial = cIn.nextLong();
		correct = true;
		}catch (InputMismatchException e) {
			System.out.println("Please enter a valid serial number. Try again...");

			if (cIn.hasNextLine())
			{
				cIn.nextLine();
			}
		}
		
		}
		return new CellPhone(this, newSerial);
	}

	
	//toString method, returns a String with the cell phone's characteristic
	public String toString() {
		return ("["+getSerial()+": "+getBrand()+" "+getPrice()+"$ "+getYear()+"] ");
	}
	
	//equals() method, compares brand, price and year
	public boolean equals(Object o) {
		if(o == null || getClass() != o.getClass())
			return false;
		else {
			CellPhone cp = (CellPhone) o;
		
                return (brand.equals(cp.brand) && (price == cp.price) && (year == cp.year));
            
		
			}
			
	
	
	}

}
