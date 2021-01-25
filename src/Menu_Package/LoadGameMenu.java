package Menu_Package;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoadGameMenu extends JPanel {

	public JButton firstGameOption;
	public JButton secondGameOption;
	public JButton thirdGameOption;

	public LoadGameMenu() {
		setBorder(new EmptyBorder(10,10,10,10));
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		//creatPanel(gbc);
	}
	
	//This function display the panel with the main buttons
	/*	public void creatPanel(GridBagConstraints gbc)  {
			newGame = new JButton(new ImageIcon("src/Images/button_new_game.png"));
			loadGame = new JButton(new ImageIcon("src/Images/button_load_game.png"));
			leaderBoard = new JButton(new ImageIcon("src/Images/button_leader_board.png"));

			newGame.setActionCommand("newGame");
			loadGame.setActionCommand("loadGame");
			leaderBoard.setActionCommand("leaderBoard");
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
		}*/
}