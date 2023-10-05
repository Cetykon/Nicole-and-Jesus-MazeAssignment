package main;

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
		Stack<Integer> pathTracker = new Stack<Integer>();
		
		//add stack to keep track of places with other possible directions
//		//storing index
//		Stack<Integer> indexWhereChoiceMadeX = new Stack<Integer>();
//		Stack<Integer> indexWhereChoiceMadeY = new Stack<Integer>();
		//storing possible dead end choice
		Stack<String> possibleDeadEnd = new Stack<String>();
		possibleDeadEnd.add("none");
		
		//boolean to check if goal was reached
		boolean goalReached = false;
		
		//add Starting position to path Traker
		pathTrackerX.add(MazeData.getStartingX());
		pathTrackerY.add(MazeData.getStartingY());
		//Direction tracker
	
		
		//for testing
		int iterationCount=0;
		//count to pop path tracker stack if dead end is found
		int countToPop = 0;
		//boolean to check if count was previously pop
		boolean wasPop = false;
		
		boolean possibleUp = false;
		boolean possibleRight = false;
		boolean possibleDown = false;
		boolean possibleLeft = false;
		
		String pathChosen = "none";
		
		
		
		while (goalReached == false) {
			
			iterationCount++;
		
			countToPop++;
			
			System.out.println("Begining of iteration " + iterationCount);
			
			
			//Looking around clockwise and checking for possible routes_______________________________________________________	
			
			try {
				//check one up
				if (MazeData.getMazeValue(pathTrackerX.peek() - 1,pathTrackerY.peek()) == 1 && pathChosen != "up") {
					
					//possibleUp = true;
					
					//Did possibility lead to a dead end
					//if yes set possible up to false
					if(possibleDeadEnd.peek() == "up" && wasPop == true) {
						
						possibleUp = false;
					}
					
				}
			} catch (Exception e) {
				System.out.println("Nothing to check up");
				e.printStackTrace();
			}
			
			try {
				//check one right
				if (MazeData.getMazeValue(pathTrackerX.peek(),pathTrackerY.peek() + 1) == 1 && pathChosen != "right") {
						
					possibleRight = true;
					
					if(possibleDeadEnd.peek() == "right" && wasPop == true) {
						
						possibleUp = false;
					}
						
				}
			} catch (Exception e) {
				System.out.println("Nothing to check right");
				e.printStackTrace();
			}
			
			try {
				//check one down
				if (MazeData.getMazeValue(pathTrackerX.peek() + 1,pathTrackerY.peek()) == 1 && pathChosen != "down") {
						
					possibleDown = true;
					
					if(possibleDeadEnd.peek() == "down" && wasPop == true) {
						
						possibleUp = false;
					}
						
				}
			} catch (Exception e) {
	
					System.out.println("Nothing to check down");
			
					// TODO Auto-generated catch block
					e.printStackTrace();
			
				
			}
	
			try {
				//check one left
				if (MazeData.getMazeValue(pathTrackerX.peek(),pathTrackerY.peek() - 1) == 1 && pathChosen != "left") {
					
										
					possibleLeft = true;
					
					if(possibleDeadEnd.peek() == "left" && wasPop == true) {
						
						possibleUp = false;
					}
								
				}
			} catch (Exception e) {
				System.out.println("Nothing to check Left");
				e.printStackTrace();
			}
			
			wasPop = false;
			//Choose which path to take and take it________________________________________________________________
			
			pathChosen = "none";
			
			if (possibleUp == true) {
				pathTrackerX.add(pathTrackerX.peek()- 1);
				pathTrackerY.add(pathTrackerY.peek());
				pathChosen = "up";
				//set chosen variable to false so we can store the possible ones
				possibleUp = false;
				System.out.println("going up");
			}
			else if (possibleRight == true) {
				pathTrackerX.add(pathTrackerX.peek());
				pathTrackerY.add(pathTrackerY.peek() + 1);
				pathChosen = "right";
				possibleRight = false;
				System.out.println("going right");
			}
			else if (possibleDown == true) {
				pathTrackerX.add(pathTrackerX.peek() + 1);
				pathTrackerY.add(pathTrackerY.peek());
				pathChosen = "down";
				possibleDown = false;
				System.out.println("going Down");
			}
			else if (possibleLeft == true) {
				pathTrackerX.add(pathTrackerX.peek());
				pathTrackerY.add(pathTrackerY.peek() - 1);
				pathChosen = "left";
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
			
			
			
			
			//Want to check if their was more possible values______________________________________________________________
			
			//if yes store coordinates
			if (possibleUp == true || possibleRight == true || possibleDown == true || possibleLeft == true) {
				
				//also store the path taken so we can set this possible false before we pop
				possibleDeadEnd.add(pathChosen);
				
			}

			//Reset possibilities
			possibleUp = false;
			possibleRight = false;
			possibleDown = false;
			possibleLeft = false;
			
			
			//Check if goal was reached and end loop_______________________________________________________________________
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
