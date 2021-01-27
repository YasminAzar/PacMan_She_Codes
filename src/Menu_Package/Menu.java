package Menu_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Board_Package.Board;

public class Menu extends JPanel implements  ActionListener  {
	static Board _board;
	// EB declare jbutton here so we can reference from main
	public JButton newGame;
	public JButton loadGame;
	public JButton leaderBoard;

	public Menu() {
		setBorder(new EmptyBorder(10,10,10,10));
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10,0,10,0);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.BOTH;
		//gbc.ipady = 150;
		//gbc.ipadx = 150;
		createPanel(gbc);
	}

	/**
	 * This function display the panel with the main buttons
	 * @param gbc
	 */
	public void createPanel(GridBagConstraints gbc)  {
		newGame = new JButton(new ImageIcon("src/Images/button_new_game.png"));
		loadGame = new JButton(new ImageIcon("src/Images/button_load_game.png"));
		leaderBoard = new JButton(new ImageIcon("src/Images/button_leader_board.png"));
		newGame.setActionCommand("newGame");
		loadGame.setActionCommand("loadGame");
		leaderBoard.setActionCommand("leaderBoard");
		this.revalidate();
		this.repaint();
		this.add(newGame, gbc);
		this.add(loadGame, gbc);
		this.add(leaderBoard, gbc);
		this.revalidate();
		this.repaint();
	}

	/**
	 * This function defines a black background
	 */
	public void paint(Graphics g) {
		int w = getSize().width;
		int h = getSize().height;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//this.dispatchEvent (e);
	}
}
