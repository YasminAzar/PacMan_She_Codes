package game_objects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Lives extends GameCharacter{
	
	private Image heartImage;
	
	public Lives(String image_string, int location_x, int location_y, String status) {
		super(null);
		this.heartImage = new ImageIcon(image_string).getImage();
		this.setLocation_x(location_x);
		this.setLocation_y(location_y);
		this.setStatus(status);
	}

	public Image getHeartImage() {
		return heartImage;
	}

	public void setHeartImage(Image heartImage) {
		this.heartImage = heartImage;
	}

}
