package game_objects;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Pacman extends GameCharacter{

	private Image pacmanImage;
	private String nameOnMap;
	private int lifeLeft;
	private boolean isImmortal;

	public boolean isImmortal() {
		return isImmortal;
	}

	public void setImortal(boolean isImortal) {
		this.isImmortal = isImortal;
	}

	public String getNameOnMap() {
		return nameOnMap;
	}

	public void setNameOnMap(String nameOnMap) {
		this.nameOnMap = nameOnMap;
	}

	public Pacman(String[] args) {
		super(args);
		// TODO Auto-generated constructor stub
	}
	
	public Pacman(String image_string, int grid_x, int grid_y, int location_x, int location_y, String direction) {
		super(null);
		this.pacmanImage = new ImageIcon(image_string).getImage();
		this.setGrid_x(grid_x);
		this.setGrid_y(grid_y);
		this.setLocation_x(location_x);
		this.setLocation_y(location_y);;
		this.setDirection(direction);
		this.setLifeLeft(3);
		this.isImmortal = false;
	}
	
	public int getLifeLeft() {
		return lifeLeft;
	}

	public void setLifeLeft(int lifeLeft) {
		this.lifeLeft = lifeLeft;
	}

	public Image getPacmanImage() {
		return pacmanImage;
	}

	public void setPacmanImage(Image image) {
		this.pacmanImage = image;
	}
	
	/**
	 * This function counts the time that pacman is immortal
	 */
	public void changeToImmortal() {
		this.isImmortal = true;
		final int second = 15000;
			ActionListener task = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
						Timer t = (Timer)evt.getSource();
						t.stop();
						setImortal(false);
					}		
			};
			Timer timer = new Timer(second, task);
			timer.start();
	}
}
