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
/**
 * This class represents a Tiger in the JunglePark application
 *
 */
public class Tiger extends Animal {
  private static final int SCAN_RANGE = 100; // range dimension for scanning the neighborhood for food
  private static final String IMAGE_FILE_NAME = "images/tiger.png";
  private static int nextID = 1; // class variable that represents the identifier of the next tiger
                                 // to be created
  // Tiger's identification fields
  private static final String TYPE = "TGR"; // A String that represents the tiger type
  private final int id; // Tiger's id: positive number that represents the order of the tiger
  private int deerEatenCount; // Number of Deers that the current tiger has eaten so far

  /**
   * Creates a new Tiger object positioned at a random position of the display window
   * 
   * @param processing PApplet object that represents the display window
   */
  public Tiger(JunglePark processing) {
    // Set Tiger drawing parameters
    super(processing, IMAGE_FILE_NAME);
    // Set Tiger identification fields
    id = nextID;
    this.label = TYPE + id; // String that identifies the current tiger
    nextID++;
    deerEatenCount = 0; // Number of Deers that the current tiger has eaten initialized to zero in the constructor
  }

  /**
   * Defines Tiger's behavior in the Jungle Park
   * Scans for food at the neighborhood of the current tiger. 
   * If the Tiger founds any deer at its proximity, it hops on it, and eats it
   */
  @Override
  public void action() {
	  if(deerEatenCount > 0) {
	      displayDeerEatenCount(); // display deerEatenCount
	  }
	  if(isDragging() == false) {
		  for (int i=0; i<processing.listGUI.size(); i++){
				if(processing.listGUI.get(i) instanceof Deer) { //find Deer
				   Deer d = (Deer) processing.listGUI.get(i);
				   if(d.distance(this) < SCAN_RANGE) { 
					   hop(d);
				   }
				}
		  }
	  }
  }
			
  /**
   * Displays the number of eaten deers if any on the top of the tiger image
   */
  public void displayDeerEatenCount() {
    this.processing.fill(0); // specify font color: black
    // display deerEatenCount on the top of the Tiger's image
    this.processing.text(deerEatenCount, this.getPositionX(), this.getPositionY() - this.image.height / 2 - 4);  
  }
  
  /*
   * Method that hop on the food Deer by tiger moving forward the position of food 
   * and eats it(as the mouse is released the deer will be removed from the JunglePark listGUI)
   * 
   */
  public void hop(Deer food) { // replace x and y position of deer w/ the tiger 
		  for (int i=0; i<processing.listGUI.size(); i++){ // look for deer and replace w/ tiger
				if(processing.listGUI.get(i) == food) { //finding deer
				   setPositionX(((Animal) processing.listGUI.get(i)).getPositionX());
				   setPositionY(((Animal)processing.listGUI.get(i)).getPositionY());
				   processing.listGUI.remove(i);
				   deerEatenCount ++;
				   break;
	               }
                } 
  }

  public int getDeerEatenCount() {
	return deerEatenCount;
  }
  
}
