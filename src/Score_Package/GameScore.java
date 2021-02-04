package score_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GameScore extends JPanel implements ActionListener {
	int score = 0;
	int powerBallCounter = 0;
	String lifeIsLost = "";
	
	public String getLifeIsLost() {
		return lifeIsLost;
	}

	public void setLifeIsLost(String lifeIsLost) {
		this.lifeIsLost = lifeIsLost;
	}

	JTextField tScore, tPbEaten, tTimer, tWarning, tLife;
	
	public int getPowerBallCounter() {
		return powerBallCounter;
	}

	public void setPowerBallCounter(int powerBallCounter) {
		this.powerBallCounter = powerBallCounter;
	}

	public GameScore() {
		setPreferredSize(new Dimension(660, 80));
        setFont(new Font("Serif", Font.BOLD, 20));
		this.setBackground(Color.black);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.VERTICAL;
		gbc.gridheight = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		tScore = new JTextField("Score: " + getScore(), 15); 
		tPbEaten = new JTextField("Power Balls Eaten: ", 15);
		tTimer = new JTextField("Time to eat the ghosts: ", 15);
		tWarning = new JTextField("Ghost is Close", 15);
		tLife = new JTextField("", 15);
		this.add(tScore, gbc);
		this.add(tPbEaten, gbc);
		this.add(tTimer, gbc);
		this.add(tWarning, gbc);
		this.add(tLife, gbc);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		tScore.setText("Score: " + getScore());
		tPbEaten.setText("Power Balls Eaten: ");
		tTimer.setText("Time to eat the ghosts: " + this.powerBallCounter);
		tWarning.setText("Ghost is Close");
		tLife.setText(this.lifeIsLost);
		
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * This function displays a note for 4 seconds when the wind has caught pacman
	 * @param message
	 */
	public void updateLifeMessage(String message) {
		final int second = 4000;
		setLifeIsLost(message);
			System.out.println("Your lost one life");
			ActionListener task = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
						Timer t = (Timer)evt.getSource();
						t.stop();
						setLifeIsLost("");
					}		
			};
			Timer timer = new Timer(second, task);
			timer.start();
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
