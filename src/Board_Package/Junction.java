package Board_Package;

public class Junction {
	private int numOfOption;
	private int x;
	private int y;
	private String direction = "RLUD";
	
	public int getnumOfOption() {
		return numOfOption;
	}
	public void setnumOfOption(int openDirection) {
		this.numOfOption = openDirection;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
