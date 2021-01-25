package Game_Objects;

import java.awt.Image;

import javax.swing.ImageIcon;

import Log_Package.PacmanLog;

public class GameCharacter {
	ImageIcon gameCharacterIcon;
	private Image gameCharacterImage;
	private int locationX;
	private int locationY;
	private int gridX;
	private int gridY;
	String status;
	boolean isPlayer;
	String direction;
	

	public GameCharacter(String[] args) {
		PacmanLog.log("GameCharacter: ", "Start");
		
	}
	
	public void move() {}

	public Image getGameCharacterImage() {
		return gameCharacterImage;
	}

	public void setGameCharacterImage(Image image) {
		this.gameCharacterImage = image;
	}

	public int getGrid_x() {
		return gridX;
	}

	public void setGrid_x(int grid_x) {
		this.gridX = grid_x;
	}

	public int getGrid_y() {
		return gridY;
	}

	public void setGrid_y(int grid_y) {
		this.gridY = grid_y;
	}

	public int getLocation_x() {
		return locationX;
	}

	public void setLocation_x(int location_x) {
		this.locationX = location_x;
	}

	public int getLocation_y() {
		return locationY;
	}

	public void setLocation_y(int location_y) {
		this.locationY = location_y;
	}
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
