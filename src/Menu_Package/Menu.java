package Menu_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		/*newGame = new JButton(new ImageIcon("src/Images/button_new_game.png")) {
			{
			setSize(200, 50);
            setMaximumSize(getSize());
			}
		};
		loadGame = new JButton(new ImageIcon("src/Images/button_load_game.png")){
			{
			setSize(200, 50);
            setMaximumSize(getSize());
			}
		};
		leaderBoard = new JButton(new ImageIcon("src/Images/button_leader_board.png")){
			{
			setSize(200, 50);
            setMaximumSize(getSize());
			}
		};*/

		newGame.setActionCommand("newGame");
		loadGame.setActionCommand("loadGame");
		leaderBoard.setActionCommand("leaderBoard");
		//newGame.setSize((int)(getWidth()*0.1), (int)(getHeight()*0.11));
		this.revalidate();
		this.repaint();
		// EB remove we control it from the main
		//newGame.addActionListener(this);
		//loadGame.addActionListener(this);
		//leaderBoard.addActionListener(this);

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
