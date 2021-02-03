package board_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;

public class PopupGameOver extends Popup implements  ActionListener {
	public JLabel l_endGame;
	// popup 
	public Popup po; 
	// panel 
	JPanel p_endGame; 
	// popupfactory 
	PopupFactory pf;

	PopupGameOver(String msg) 
	{ 
		pf = new PopupFactory(); 
		l_endGame = new JLabel(); 

		try { 
			// set windows look and feel 
			UIManager.setLookAndFeel(UIManager. 
					getSystemLookAndFeelClassName()); 
		} 
		catch (Exception e) { 
		} 
		// create a panel 
		p_endGame = new JPanel();
		p_endGame.setBackground(Color.GREEN);
		// create a font 
		Font f1 = new Font("BOLD", 1, 60);
		l_endGame.setFont(f1);
		// add contents to panel 
		p_endGame.add(l_endGame);
		p_endGame.setLayout(new GridLayout(1, 1)); 
	}

	public void updatePopUp(String status) {
		if (status.equals("GAME OVER")) {
			p_endGame.setBackground(Color.ORANGE);
		} else {
			p_endGame.setBackground(Color.GREEN);
		}
		l_endGame.setText(status);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	} 
}
