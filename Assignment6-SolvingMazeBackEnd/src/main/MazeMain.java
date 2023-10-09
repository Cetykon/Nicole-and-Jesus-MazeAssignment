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
		Stack<wasItChosen> splitSpot = new Stack<wasItChosen>();	
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
		boolean startPopCount = false;
		
		boolean possibleUp = false;
		boolean possibleRight = false;
		boolean possibleDown = false;
		boolean possibleLeft = false;
		
		//a string that allows me to print the path chosen
		String pathChosen = "none";
		
		
		wasItChosen doNotBackTrack = new wasItChosen();
		
		while (goalReached == false) {
			
			iterationCount++;
			
			//reset chosen path
			String iComeFrom = pathChosen;
			pathChosen = "none";
			
			
			
			
			
			System.out.println("Begining of iteration " + iterationCount);
			
			//if was pop is true set the doNotBackTrack equal to splitSpot saved object that corresponds to the current index
			if (wasPop == true) {
				
				doNotBackTrack = splitSpot.pop();

			}
			//reset was pop to false false after using it
			wasPop = false;
			
			//Looking around clockwise starting by looking up and checking for possible routes_______________________________________________________	
			//Excluding paths that were already taken
			try {
				//check one up
				if (MazeData.getMazeValue(pathTrackerX.peek() - 1,pathTrackerY.peek()) == 1 && doNotBackTrack.isUp() == false) {
					System.out.println("Check up if went through");
					
					possibleUp = true;
					
				}
			} catch (Exception e) {
				System.out.println("Nothing to check up");
				e.printStackTrace();
			}
			
			try {
				//check one right
				if (MazeData.getMazeValue(pathTrackerX.peek(),pathTrackerY.peek() + 1) == 1 && doNotBackTrack.isRight() == false) {
					System.out.println("Check right if went through");
					
					possibleRight = true;
				
				}
			} catch (Exception e) {
				System.out.println("Nothing to check right");
				e.printStackTrace();
			}
			
			try {
				//check one down
				if (MazeData.getMazeValue(pathTrackerX.peek() + 1,pathTrackerY.peek()) == 1 && doNotBackTrack.isDown() == false) {
					System.out.println("Check down if went through");
					
					possibleDown = true;
					
				}
			} catch (Exception e) {
	
					System.out.println("Nothing to check down");
					e.printStackTrace();

			}
	
			try {
				//check one left
				if (MazeData.getMazeValue(pathTrackerX.peek(),pathTrackerY.peek() - 1) == 1 && doNotBackTrack.isLeft() == false) {	
					System.out.println("Check left if went through");
					
					possibleLeft = true;
					
								
				}
			} catch (Exception e) {
				System.out.println("Nothing to check Left");
				e.printStackTrace();
			}
			
			//reset doNotTrackBack
			doNotBackTrack = new wasItChosen();
			
			
			
			//Choose which path to take and take it__________________________________________________________________________________________
			
			if (possibleUp == true) {
				
				pathTrackerX.add(pathTrackerX.peek()- 1);
				pathTrackerY.add(pathTrackerY.peek());
				if (startPopCount == true) {
					countToPop++;
				}
				pathChosen = "up";
				//if the up was chosen it came from the down position so set to true
				doNotBackTrack.setDown(true);
				//set chosen variable to false so we can store the possible ones
				System.out.println("going up");
			}
			else if (possibleRight == true) {
				
				pathTrackerX.add(pathTrackerX.peek());
				pathTrackerY.add(pathTrackerY.peek() + 1);
				if (startPopCount == true) {
					countToPop++;
				}
				pathChosen = "right";
				//set chosen variable to false so we can store the possible ones
				doNotBackTrack.setLeft(true);
				System.out.println("going right");
			}
			else if (possibleDown == true) {
				
				pathTrackerX.add(pathTrackerX.peek() + 1);
				pathTrackerY.add(pathTrackerY.peek());
				if (startPopCount == true) {
					countToPop++;
				}
				pathChosen = "down";
				doNotBackTrack.setUp(true);
				System.out.println("going Down");
			}
			else if (possibleLeft == true) {
				pathTrackerX.add(pathTrackerX.peek());
				pathTrackerY.add(pathTrackerY.peek() - 1);
				if (startPopCount == true) {
					countToPop++;
				}
				pathChosen = "left";
				doNotBackTrack.setRight(true);
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
			if ((possibleUp && possibleRight) || 
				    (possibleUp && possibleDown) || 
				    (possibleUp && possibleLeft) || 
				    (possibleRight && possibleDown) || 
				    (possibleRight && possibleLeft) || 
				    (possibleDown && possibleLeft)) {
				System.out.println("More than one possibility found.");
				
				//Change later for 
				countToPop = 1;
				startPopCount = true;
				
				//Stack implementation to keep track of used paths
				wasItChosen temp2 =  new wasItChosen();
				//set temp2 to doNotBackTrack to acc
				//temp2 =  doNotBackTrack;
				
				if (iComeFrom == "up") {
					
					temp2.setDown(true);
					System.out.println(temp2.isUp());
					
				}
				if (iComeFrom == "right"){
					
					temp2.setLeft(true);
					System.out.println(temp2.isRight());
					
				}
				if (iComeFrom == "down"){
					
					temp2.setUp(true);
					System.out.println(temp2.isDown());
					
				}
				if (iComeFrom == "left"){
					
					temp2.setRight(true);
					System.out.println(temp2.isLeft());
				}
				
				
				if (possibleUp == true && pathChosen == "up") {
				
					temp2.setUp(true);
					System.out.println(temp2.isUp());
					
				}
				if (possibleRight == true && pathChosen == "right"){
					
					temp2.setRight(true);
					System.out.println(temp2.isRight());
					
				}
				if (possibleDown == true && pathChosen == "down"){
					
					temp2.setDown(true);
					System.out.println(temp2.isDown());
					
				}
			
				if (possibleLeft == true && pathChosen == "left"){
					
					temp2.setLeft(true);
					System.out.println(temp2.isLeft());
				}
				splitSpot.add(temp2);
				
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
