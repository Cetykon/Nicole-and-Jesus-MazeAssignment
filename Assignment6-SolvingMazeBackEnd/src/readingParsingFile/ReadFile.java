package readingParsingFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.MazeData;

public class ReadFile {

	public void Read() {

		try {
			File temp  = new File("src/maze2.txt");
			Scanner file = new Scanner(temp);
			
			MazeData.setDimensionX(file.nextInt());
			MazeData.setDimensionY(file.nextInt());
			//Creates multi-dimensional array with the dimension from file
			MazeData.setMazeDimension();
			
			MazeData.setStartingX(file.nextInt());
			MazeData.setStartingY(file.nextInt());
			
			MazeData.setEndingX(file.nextInt());
			MazeData.setEndingY(file.nextInt());
			
			//Printing Dimensions
			System.out.println(MazeData.getDimensionX() + " " + MazeData.getDimensionY());
			
			//Printing Starting point
			System.out.println(MazeData.getStartingX() + " " + MazeData.getStartingY());
			
			//Printing Ending point
			System.out.println(MazeData.getEndingX() + " " + MazeData.getEndingY());
			
			for (int i = 0; i < MazeData.getMaze().length; ++i) {
			      for(int j = 0; j < MazeData.getMaze()[i].length; ++j) {
			    	
			    	MazeData.insertMazevalue(i, j, file.nextInt());
			    	
			    	//Printing to console code
			        System.out.print(MazeData.getMazeValue(i, j) + " ");
			        if (j == 9) {
			        	System.out.println();
			        }
			      }
			    }
			
		
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("Couldn't read file");
		}
		
	}
	

}
