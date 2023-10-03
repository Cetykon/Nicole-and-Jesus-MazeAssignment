package main;

import java.util.Stack;

import readingParsingFile.ReadFile;

public class MazeMain {

	public static void main(String[] args) {
		//file reading and setting data into Class for use
		ReadFile readfile = new ReadFile();
		readfile.Read();
		
		Stack<Integer> pathTrackerX = new Stack<Integer>();
		Stack<Integer> pathTrackerY = new Stack<Integer>();
		
		boolean goalReached = false;
		
		//add Starting position
		pathTrackerX.add(MazeData.getStartingX());
		pathTrackerY.add(MazeData.getStartingY());
		
		int countToPop = 0;

		while (goalReached == false) {
			
			countToPop++;
			boolean isNextPathChosen = false;
			boolean possibleUp = false;
			boolean possibleRight = false;
			boolean possibleDown = false;
			boolean possibleLeft = false;
			
				
			try {
				//check one up
				if (MazeData.getMazeValue(pathTrackerX.peek() - 1,pathTrackerY.peek()) == 1) {
					
					if(isNextPathChosen = false) {
						
						pathTrackerX.add(pathTrackerX.peek()- 1);
						pathTrackerY.add(pathTrackerY.peek());
						isNextPathChosen = true;
					}
					else {

						possibleUp = true;
						
					}
					
				}
			} catch (Exception e) {
				System.out.println("Nothing to check up");
			}
			
			try {
				//check one right
				if (MazeData.getMazeValue(pathTrackerX.peek(),pathTrackerY.peek() + 1) == 1) {
					
					if(isNextPathChosen = false) {
						
						pathTrackerX.add(pathTrackerX.peek());
						pathTrackerY.add(pathTrackerY.peek() + 1);
						isNextPathChosen = true;
					}
					else {
						
						possibleRight = true;
						
					}
					
				}
			} catch (Exception e) {
				System.out.println("Nothing to check right");
			}
			
			try {
				//check one down
				if (MazeData.getMazeValue(pathTrackerX.peek() + 1,pathTrackerY.peek()) == 1) {
					
					if(isNextPathChosen = false) {
						
						pathTrackerX.add(pathTrackerX.peek() + 1);
						pathTrackerY.add(pathTrackerY.peek());
						isNextPathChosen = true;
					}
					else {
						
						possibleDown = true;
						
					}
					
				}
			} catch (Exception e) {
				System.out.println("Nothing to check down");
			}
			
			try {
				//check one left
				if (MazeData.getMazeValue(pathTrackerX.peek(),pathTrackerY.peek() - 1) == 1) {
					
					if(isNextPathChosen = false) {
						
						pathTrackerX.add(pathTrackerX.peek());
						pathTrackerY.add(pathTrackerY.peek() - 1);
						isNextPathChosen = true;
					}
					else {
						
						possibleLeft = true;
						
					}
				}
			} catch (Exception e) {
				System.out.println("Nothing to check Left");
			}
			
			//if not path is available
			if (isNextPathChosen = false) {
				//use countTopop to pop stack 
				for (int i = 0; i< countToPop-1; i++) {
					pathTrackerX.pop();
					 pathTrackerY.pop();
					
				}
				//reset Count To pop
				countToPop = 0;
			}
		
			pathTrackerX.add(MazeData.getEndingX());
			pathTrackerY.add(MazeData.getEndingY());
			//if path has reached the goal
			if (pathTrackerX.peek() == MazeData.getEndingX() && pathTrackerY.peek() == MazeData.getEndingY()) {
				//set goal reached to true and print out path
				goalReached = true;
				System.out.println("Goal Reached");
				System.out.println("Path Taken:");
				while (pathTrackerX.empty() != true) {
					
					System.out.println(pathTrackerX.pop() + " " + pathTrackerY.pop());
				}
				
			}	

		}
	
	}

}
