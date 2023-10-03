package main;

public class MazeData {

	//Coordinate for maze starting position
	private static int startingX;
	private static int startingY;
	//Coordinate for maze starting ending position
	private static int endingX;
	private static int endingY;
	
	//Maze Dimensions
	private static int dimensionX;
	private static int dimensionY;

	private static int[][] Maze;


	public MazeData() {
		
	}


	public static int getStartingX() {
		return startingX;
	}


	public static void setStartingX(int startingX) {
		MazeData.startingX = startingX;
	}


	public static int getStartingY() {
		return startingY;
	}


	public static void setStartingY(int startingY) {
		MazeData.startingY = startingY;
	}


	public static int getEndingX() {
		return endingX;
	}


	public static void setEndingX(int endingX) {
		MazeData.endingX = endingX;
	}


	public static int getEndingY() {
		return endingY;
	}


	public static void setEndingY(int endingY) {
		MazeData.endingY = endingY;
	}


	public static int getDimensionX() {
		return dimensionX;
	}


	public static void setDimensionX(int dimensionX) {
		MazeData.dimensionX = dimensionX;
	}


	public static int getDimensionY() {
		return dimensionY;
	}

	
	public static void setDimensionY(int dimensionY) {
		MazeData.dimensionY = dimensionY;
	}

	//get maze value given position
	public static int getMazeValue(int x, int y) {
		int mazeValue = Maze[x][y];
		
		return mazeValue;
	}

	//The the dimensions of the multi-dimensional array
	public static void setMazeDimension() {
		Maze = new int[MazeData.getDimensionX()][MazeData.getDimensionY()];
	}
	
	public static void insertMazevalue(int x, int y, int val) {
		Maze[x][y] = val;
	}
	
	public static int[][] getMaze() {
		return Maze;
	}
}
