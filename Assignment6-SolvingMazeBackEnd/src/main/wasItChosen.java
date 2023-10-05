package main;
//class not being used at the moment it is here for experimental purposes
public class wasItChosen {

	

	private Integer x;
	private Integer y;
	
	private boolean wasUpChosen;
	private boolean wasRightChosen;
	private boolean wasDownChosen;
	private boolean wasLeftChosen;
	
	public wasItChosen(){
		
		this.wasUpChosen = false;
		this.wasRightChosen = false;
		this.wasDownChosen = false;
		this.wasLeftChosen = false;
		
		
	}

	
	public Integer getX() {
		return x;
	}


	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}


	public boolean isUp() {
		return wasUpChosen;
	}

	public void setUp(boolean wasUpChosen) {
		this.wasUpChosen = wasUpChosen;
	}

	public boolean isRight() {
		return wasRightChosen;
	}

	public void setRight(boolean wasRightChosen) {
		this.wasRightChosen = wasRightChosen;
	}

	public boolean isDown() {
		return wasDownChosen;
	}

	public void setDown(boolean wasDownChosen) {
		this.wasDownChosen = wasDownChosen;
	}

	public boolean isLeft() {
		return wasLeftChosen;
	}

	public void setLeft(boolean wasLeftChosen) {
		this.wasLeftChosen = wasLeftChosen;
	}
	
	
}
