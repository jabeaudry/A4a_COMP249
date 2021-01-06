//Jacinthe Beaudry (40126080)
//COMP249
//Assignment #3 
//December 4th 2020



package pack1;
import pack1.CellPhone;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;


/** 
 * 
 * This class creates CellPhone objects.
 * The inner class, CellNode,
 * 
 * @author Jacinthe Beaudry (40126080)
 * @version 1.0
 * 
 */

public class CellList {
	
	
	public class CellNode {
		
		private CellPhone cell;
		private CellNode pointer;
		/** 
		 * 
		 * the CellNode() method creates the blocks of data that will
		 * organize the Cell Phone list
		 * 
		 * @param cell Cell Phone object that holds one CellPhone
		 * @param pointer node variable
		 * 
		 */
		
	public CellNode() {
		cell = null;
		pointer = null;
	}
	
	public CellNode(CellPhone c, CellNode cn) {
		cell = c;
		pointer = cn;
	}
	
	//accessor and mutator
	//these method allow privacy leaks, since the user must change 
	//the instances of private variables
	
	public CellPhone getCell() {
		return cell;
	}
	
	public void setCell(CellPhone cell) {
		this.cell = cell;
	}
	
	public CellNode getNode() {
		return pointer;
	}
	
	public void setNode(CellNode cn) {
		pointer = cn;
	}
	
	
	
	//copy constructor
	public CellNode(CellNode cn) {
		cell = cn.cell;
		pointer = cn.pointer;
	}
	
	
	}//CellNode closes
	
	//variables
	private CellNode head;    //points the 1st node 
	private int size;           //current size of the list
	static int searches = 0;
	
	public CellList() {
		head = null;
		
	}
	//get size method
	
	public int getSize() {
		if (head==null) {
			return 0;
		}
		else {
			CellNode p = head;
			int size = 0;
			while (p!=null) {
				size++;
				p = p.pointer;
			}
			return size;
		}
	}
	
	//copy constructor
	public CellList(CellList cl) {
		if (cl.head == null) {
			head = null;
		}
		else {
			CellNode cn1 = cl.head;
			CellNode cn2 = null, cn3 = null;
			head = null;
			while(cn1 != null) {
				if (head == null) {
					cn2 = new CellNode(cn1);
					head = cn2;
				}
				else {
					cn3 = new CellNode(cn1);
					cn2.pointer = cn3;
					cn2 = cn3;
				}
				cn1 = cn1.pointer;
			}
			cn2 = cn3 = null;
		}
	}
	
	//clone method
	public CellList clone(){
	    return new CellList(this);
    }
	
	
	
	// add a CellNode and an object from CellPhone class at the top of the list;
	public void addTostart(CellPhone cp) {
		head = new CellNode(cp, head); 
		size = getSize();
	}
	
	//insert at the specified index
	
	public void insertAtIndex(CellPhone cp, int index) {
		try {
		    getSize();
			if(index > size-1 || index < 0)
				throw new NoSuchElementException();
			else {
				CellNode cn = head ;
				if (index == 0) {
					head = new CellNode(cp,cn);
				}
				else {
					for (int i = 0; i < index - 1; i++)
						cn = cn.pointer;
					cn.pointer = new CellNode(cp,cn.pointer);
				}
                cn = null;
                size = getSize();
			}
		}catch(NoSuchElementException e) {
			errorMessage();
		}
	}
		
	// deletes the node pointed by that index from the list.
		public void deleteFromIndex(int index) {
			try {
	            size = getSize();
				if (index > size - 1 || index < 0)
					throw new NoSuchElementException();
				else {
					
					CellNode cn = head ;
					
					if(index == 0) {
						head = cn.pointer;
					}
					
					else {
						for (int i = 0; i < index - 1; i++)
							cn = cn.pointer;
						if (cn.pointer.pointer == null) {
							cn.pointer = null;
						}
						else {
							cn.pointer = cn.pointer.pointer;
						}
	                    size = getSize();
					}
				}
			}catch(NoSuchElementException e) {
				errorMessage();
			}
		}	
		
		
		// deletes the first node in the list
		public boolean deleteFromStart() {
			if (head != null) {
				head = head.pointer;
				size = this.getSize();
				return true;
			}
			else {
				return false;
			}
		}
		
		//replaces the selected Cell Phone at a specific index
		public boolean replaceAtIndex(CellPhone cp, int index) {
			if (head == null)
				return false;
			else {
				if(index >= size-1 || index < 0)
					return false;
				else {
					CellNode cn = head;
					if (index==0) 
						head = new CellNode(cp,cn.pointer);
					else {
						for(int i=0; i<index-1; i++)
							cn = cn.pointer;
						if (cn.pointer.pointer == null) {
							cn.pointer = new CellNode(cp, null);
						}
						else {
							cn.pointer = new CellNode(cp,cn.pointer.pointer);
							cn = null;
						}
					}
					return true;
				}
			}
		}
		
		//searches the list for a CourseNode with the courseID
		//This method allows a privacy leak, because 
		//it returns a reference to a node (getSerial())
		// it can then be modified
		public CellNode find(long serial) {
			if(head == null) {
				//System.out.println("The list is empty.");
				return null;
			}	
			else {
				CellNode cn = head;
				searches = 0;
				boolean found = false;
				while(cn != null) {
					searches++;
					if ((cn.cell.getSerial()) == (serial)) {
					
						found = true;
						break;
					}
					cn = cn.pointer;
				}
				if (!found) {
					System.out.println("The cell phone does not exist in the list.");
					return null;	
				}	
				else {
					return cn;
				}
			}

		}
		
		//The method returns true if the a course with that courseID is in the list
		public boolean contains(long serial) {
			if (head == null) {
				//System.out.println("The list is empty.");
				return false;
			}	
			else {
				CellNode cn = head;
				boolean found = false;
				while(cn != null) {
					if(cn.cell.getSerial() == serial) {
						found = true;
						break;
					}
					cn = cn.pointer;
				}
				if (!found) {
					return false;	
				}	
				else {
					return true;
				}
			}
		}
		
		
		//The method returns true if the two lists contain similar courses
		public boolean equals(CellList cl) {
			if (getSize() != cl.getSize())
				return false;
			else {
				CellNode cn = head, cn2 = cl.head;
				boolean same = false;
				while(cn != null) {
					while(cn2 != null) {
						if (cn.cell.equals(cn2.cell)) {
							same = true;
							break;
						}
						cn2 = cn2.pointer;
					}
					if (!same) 
						break;
					System.out.println(cn.cell);
					cn = cn.pointer;
				}
				if (!same)
					return false;
				else
					return true;
			}
		}
		
		//print the objects in CourseList
		public void showContents() {
			CellNode cn = head; 
			int lineCount = 0;
			while (cn != null) {
					if (lineCount < 2) {
						System.out.print(cn.cell+" ----> ");
						cn = cn.pointer;
						lineCount++;
					}
					else {
						System.out.println("/n"+cn.cell+" ----> ");
						cn = cn.pointer;
						lineCount = 0;
				}
			}
			System.out.println("X");
		}
		
		//this method is used for the error message used throughout the class
		public void errorMessage() {
			System.out.println("Program will be terminated\n");
			System.out.println("----------------------------");
			System.out.println("Thanks for using Jacinthe's program!");
			System.exit(0);
		}
		
}
