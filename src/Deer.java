//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           JunglePark2000
// Files:           Jungle Park 2000
// Course:          CS300, Fall 2018
//
// Author:          Chaiyeen Oh
// Email:           coh26@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  Piazza (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class Deer extends Animal {
	private static final int SCAN_RANGE = 175; // scan range area to check for a threat in the neighborhood
	private static final String IMAGE_FILE_NAME = "images/deer.png";
	private static int nextID = 1; // class variable that represents the identifier of the next deer to be created
	private static final String TYPE = "DR"; // A String that represents the deer type
	private final int id; // Deer's id: positive number that represents the order of the deer
	 
/*
 *  Constructor that creates a new Deer object positioned at a random position of the display window
 *  and initializes its instance fields that identifies a deer object
 *  Overload from animal class 
 */
	public Deer(JunglePark processing) {
		// Set Deer drawing parameters
		super(processing,IMAGE_FILE_NAME);
		
		// Set Deer identification fields
		id = nextID;
		this.label = TYPE + id;
		nextID++;
		
	}
	/*
	 * Checks if there is a threat (a Tiger for instance) at the neighborhood
	 * scanRange is an integer that represents the range of the area to be scanned around the animal 
	 * and it will always be positive
	 * @ return true if there is at least one Tiger close to the deer within the scanRange distance 
	 */	
	public boolean scanForThreat(int scanRange) { 
		
		for (int i=0; i<processing.listGUI.size(); i++){
			if(processing.listGUI.get(i) != null && processing.listGUI.get(i) instanceof Tiger) { //find Tiger
			   Tiger t = (Tiger) processing.listGUI.get(i);
			   if(t.distance(this) < scanRange ) { 
				   return true;
			   }
			}
			else{
				return false;
			}
		}
		return false;
	}
	 
	// Defines the behavior of a Deer object in the Jungle park
	  @Override
	public void action() {
		  if(scanForThreat(SCAN_RANGE) == true) {
			  this.processing.fill(0); // specify font color: black
			  this.processing.text("THREAT!", this.getPositionX(), this.getPositionY() - this.image.height / 2 - 4);
		  }
	}

}