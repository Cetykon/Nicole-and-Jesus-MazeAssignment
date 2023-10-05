package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import readingParsingFile.ReadFile;

public class MazeMain {

	public static void main(String[] args) {
		
		//file reading and setting data into Class for use___________________________________________________________________
		ReadFile readfile = new ReadFile();
		readfile.Read();
		//____________________________________________________________________________________________________________________
		
		//Path Tracker keeps track of path taken 
		Stack<Integer> pathTrackerX = new Stack<Integer>();
		Stack<Integer> pathTrackerY = new Stack<Integer>();		

		//storing possible dead end choice
		//index along with paths already chosen
		List <wasItChosen> splitSpot = new ArrayList<wasItChosen>();
		
		//boolean to check if goal was reached
		boolean goalReached = false;
		
		//add Starting position to path Tracker
		pathTrackerX.add(MazeData.getStartingX());
		pathTrackerY.add(MazeData.getStartingY());
		
	
		
		//keep track of integrations for testing
		int iterationCount=0;
		//count to pop path tracker stack if dead end is found
		int countToPop = 0;
		//boolean to check if count was previously pop
		boolean wasPop = false;
		
		boolean possibleUp = false;
		boolean possibleRight = false;
		boolean possibleDown = false;
		boolean possibleLeft = false;
		
		//a string that allows me to print the path chosen
		String pathChosen = "none";
		
		
		wasItChosen doNotBackTrack = new wasItChosen();
		
		while (goalReached == false) {
			
			iterationCount++;
		
			countToPop++;
			
			System.out.println("Begining of iteration " + iterationCount);
			
			//if was pop is true set the doNotBackTrack equal to splitSpot saved object that corresponds to the current index
			if (wasPop == true) {
				
				int foundIndex = -1;
				//get current splitSpot set do NotBackTrack equal to it
				for (int i = 0; i < splitSpot.size(); i++) {
					wasItChosen spot = splitSpot.get(i);
					//if a match is found set found index
				    if (spot.getX() == pathTrackerX.peek() && spot.getY() == pathTrackerY.peek()) {
				        
				    	 foundIndex = i;
				        break; // Exit the loop since we found a match
				    }
				}
				doNotBackTrack = splitSpot.get(foundIndex);
				
			}
	
			
			//Looking around clockwise starting by looking up and checking for possible routes_______________________________________________________	
			//Excluding paths that were already taken
			try {
				//check one up
				if (MazeData.getMazeValue(pathTrackerX.peek() - 1,pathTrackerY.peek()) == 1 && doNotBackTrack.isUp() == false) {
					System.out.println("Check up went through");
					
					possibleUp = true;
					
				}
			} catch (Exception e) {
				System.out.println("Nothing to check up");
				e.printStackTrace();
			}
			
			try {
				//check one right
				if (MazeData.getMazeValue(pathTrackerX.peek(),pathTrackerY.peek() + 1) == 1 && doNotBackTrack.isRight() == false) {
					System.out.println("Check right went through");
					
					possibleRight = true;
				
				}
			} catch (Exception e) {
				System.out.println("Nothing to check right");
				e.printStackTrace();
			}
			
			try {
				//check one down
				if (MazeData.getMazeValue(pathTrackerX.peek() + 1,pathTrackerY.peek()) == 1 && doNotBackTrack.isDown() == false) {
					System.out.println("Check down went through");
					
					possibleDown = true;
					
				}
			} catch (Exception e) {
	
					System.out.println("Nothing to check down");
					e.printStackTrace();

			}
	
			try {
				//check one left
				if (MazeData.getMazeValue(pathTrackerX.peek(),pathTrackerY.peek() - 1) == 1 && doNotBackTrack.isLeft() == false) {	
					System.out.println("Check left went through");
					
					possibleLeft = true;
					
								
				}
			} catch (Exception e) {
				System.out.println("Nothing to check Left");
				e.printStackTrace();
			}
			
			//reset doNotTrackBack
			doNotBackTrack = new wasItChosen();
			//reset was pop to false false after using it
			wasPop = false;
			
			
			//Choose which path to take and take it__________________________________________________________________________________________
			pathChosen = "none";
			
			if (possibleUp == true) {
				pathTrackerX.add(pathTrackerX.peek()- 1);
				pathTrackerY.add(pathTrackerY.peek());
				pathChosen = "up";
				//if the up was chosen it came from the down position so set to true
				doNotBackTrack.setDown(true);
				//set chosen variable to false so we can store the possible ones
				possibleUp = false;
				System.out.println("going up");
			}
			else if (possibleRight == true) {
				pathTrackerX.add(pathTrackerX.peek());
				pathTrackerY.add(pathTrackerY.peek() + 1);
				pathChosen = "right";
				//set chosen variable to false so we can store the possible ones
				doNotBackTrack.setLeft(true);
				possibleRight = false;
				System.out.println("going right");
			}
			else if (possibleDown == true) {
				pathTrackerX.add(pathTrackerX.peek() + 1);
				pathTrackerY.add(pathTrackerY.peek());
				pathChosen = "down";
				doNotBackTrack.setUp(true);
				possibleDown = false;
				System.out.println("going Down");
			}
			else if (possibleLeft == true) {
				pathTrackerX.add(pathTrackerX.peek());
				pathTrackerY.add(pathTrackerY.peek() - 1);
				pathChosen = "left";
				doNotBackTrack.setRight(true);
				possibleLeft = false;
				System.out.println("going left");
			}
			else {
				//use countTopop to pop stack 
				for (int i = 0; i< countToPop; i++) {
					pathTrackerX.pop();
					pathTrackerY.pop();
					
				}
				//reset Count To pop
				countToPop = 0;
				wasPop = true;

				System.out.println("Application pop");
			}	
	
			
			//Want to check if their was more possible values__________________________________________________________________________________________			
			//if yes store coordinates
			if (possibleUp == true || possibleRight == true || possibleDown == true || possibleLeft == true) {
				
				//pop and store index of path just chosen to get back to split spot
				int tempX = pathTrackerX.pop();
				int tempY = pathTrackerY.pop();
				
				int foundIndex = -1;
			
				//check is split spot was already stored in list 
				for (int i = 0; i < splitSpot.size(); i++) {
					wasItChosen spot = splitSpot.get(i);
					//if a match is found set found index
				    if (spot.getX() == pathTrackerX.peek() && spot.getY() == pathTrackerY.peek()) {
				        
				    	 foundIndex = i;
				        break; // Exit the loop since we found a match
				    }
				}
				
				if(foundIndex != -1) {
					
					if (pathChosen == "up") {
						
						splitSpot.get(countToPop).setUp(true);
						
					}else if (pathChosen == "right"){
						splitSpot.get(countToPop).setRight(true);
						
					}else if (pathChosen == "down"){
						splitSpot.get(countToPop).setDown(true);
						
					}else if (pathChosen == "left"){
						splitSpot.get(countToPop).setLeft(true);
					}
					
				}
				else {
					//if not add new spot to list
					wasItChosen Spot = new wasItChosen();
					
					//we need to store both the path taken and the path we came from 
					//so when we pop we are not using this directions as the possibilities. 
					Spot = doNotBackTrack;
					if (pathChosen == "up") {
						
						Spot.setUp(true);
						
					}else if (pathChosen == "right"){
						Spot.setRight(true);
						
					}else if (pathChosen == "down"){
						Spot.setDown(true);
						
					}else if (pathChosen == "left"){
						Spot.setLeft(true);
					}
					
					//store split spot location
					Spot.setX(pathTrackerX.peek());  
					Spot.setY(pathTrackerY.peek());
					//add our spot object to the ArrayList of splitSpots
					splitSpot.add(Spot);
					System.out.println("splitSpot added " + Spot.getX() +" "+ Spot.getY());
				}
				//add current index back to pathTracker
				pathTrackerX.add(tempX);
				pathTrackerY.add(tempY);
			}

			//Reset possibilities
			possibleUp = false;
			possibleRight = false;
			possibleDown = false;
			possibleLeft = false;
			
			
			//Check if goal was reached and end loop______________________________________________________________________________________________________
			if (pathTrackerX.peek() == MazeData.getEndingX() && pathTrackerY.peek() == MazeData.getEndingY()) {
				//set goal reached to true and print out path
				goalReached = true;
				System.out.println("Goal Reached");
				System.out.println("Path Taken:");
				while (pathTrackerX.empty() != true) {
					
					System.out.println(pathTrackerX.pop() + " " + pathTrackerY.pop());
				}
				
			}
			
			
			
			//Testing print
			System.out.println(pathTrackerX.peek());
			System.out.println(pathTrackerY.peek());
			
			
			System.out.println("End of iteration " + iterationCount);
			

		}
	
	}

}
