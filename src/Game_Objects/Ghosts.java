package Game_Objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ghosts extends GameCharacter {
	
	private Image ghostImage;
	private String nameOnMap;
	private int moveCounter;
	private String standOn;
	final String SMALL_BALL = "0";
	
	public String getStandOn() {
		return standOn;
	}

	public void setStandOn(String standOn) {
		this.standOn = standOn;
	}

	public int getMoveCounter() {
		return moveCounter;
	}

	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}

	public String getNameOnMap() {
		return nameOnMap;
	}

	public void setNameOnMap(String nameOnMap) {
		this.nameOnMap = nameOnMap;
	}

	public Ghosts(String[] args) {
		super(args);
	}
	
	public Ghosts(String image_string, int grid_x, int grid_y, int location_x, int location_y, String direction, String nameOnMap) {
		super(null);
		this.ghostImage = new ImageIcon(image_string).getImage();
		this.setGrid_x(grid_x);
		this.setGrid_y(grid_y);
		this.setLocation_x(location_x);
		this.setLocation_y(location_y);
		this.setDirection(direction);
		this.setNameOnMap(nameOnMap);
		this.setMoveCounter(0);
		this.setStandOn(SMALL_BALL);
	}
	public Image getGhostImage() {
		return ghostImage;
	}

	public void setGhostImage(Image image) {
		this.ghostImage = image;
	}
}
