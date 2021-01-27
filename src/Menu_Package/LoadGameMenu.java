package Menu_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoadGameMenu extends JPanel {

	public JButton firstGameOption;
	public JButton secondGameOption;
	public JButton thirdGameOption;
	public JTextField title; 

	public LoadGameMenu() {
		setBorder(new EmptyBorder(10,10,10,10));
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(30,10,0,10);
		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.BOTH;
		creatPanel(gbc);
	}
	
	//This function display the panel with the load game buttons
		public void creatPanel(GridBagConstraints gbc)  {
			firstGameOption = new JButton(new ImageIcon("src/Images/board_1_image.png"));
			secondGameOption = new JButton(new ImageIcon("src/Images/board_2_image.png"));
			thirdGameOption = new JButton(new ImageIcon("src/Images/board_3_image.png"));
			title = new JTextField("            Please select the game board:", 20); 
			firstGameOption.setActionCommand("firstGameOption");
			secondGameOption.setActionCommand("secondGameOption");
			thirdGameOption.setActionCommand("thirdGameOption");
			this.revalidate();
			this.repaint();
			gbc.gridx = 1;
			gbc.gridy = 0;
			this.add(title, gbc);
			gbc.gridx = 0;
			gbc.gridy = 2;
			this.add(firstGameOption, gbc);
			gbc.gridx = 1;
			gbc.gridy = 2;
			this.add(secondGameOption, gbc);
			gbc.gridx = 2;
			gbc.gridy = 2;
			this.add(thirdGameOption, gbc);
			this.revalidate();
			this.repaint();
		}
		
		/**
		 * This function defines a black background
		 */
		/*public void paint(Graphics g) {
			int w = getSize().width;
			int h = getSize().height;
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, w, h);
		}*/
}