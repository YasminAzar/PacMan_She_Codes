package game_objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Power_Ball extends GameCharacter {
	private Image powerBallImage;
	private String nameOnMap;

	public String getNameOnMap() {
		return nameOnMap;
	}

	public void setNameOnMap(String nameOnMap) {
		this.nameOnMap = nameOnMap;
	}

	public Power_Ball(String image_string, int grid_x, int grid_y, int location_x, int location_y, String status, String nameOnMap) {
		super(null);
		this.powerBallImage = new ImageIcon(image_string).getImage();
		this.setGrid_x(grid_x);
		this.setGrid_y(grid_y);
		this.setLocation_x(location_x);
		this.setLocation_y(location_y);
		this.setStatus(status);
		this.setNameOnMap(nameOnMap);
		// TODO Auto-generated constructor stub
	}

	public Image getPowerBallImage() {
		return powerBallImage;
	}

	public void setPowerBallImage(Image powerBallImage) {
		this.powerBallImage = powerBallImage;
	}
}
