package Board_Package;

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

import Menu_Package.Menu;

public class PopupGameOver implements  ActionListener {
	private Menu menu;
	// popup 
	Popup po; 

	// panel 
	JPanel p, pwin; 

	// popupfactory 
	PopupFactory pf;
	 
	PopupGameOver() 
	{ 
		pf = new PopupFactory(); 
		JLabel l = new JLabel("GAME OVER"); 
		JLabel l2 = new JLabel("SCORE: "); 
		JLabel l_win = new JLabel("YOU WIN"); 
		JLabel l2_win = new JLabel("SCORE: "); 

		try { 
			// set windows look and feel 
			UIManager.setLookAndFeel(UIManager. 
					getSystemLookAndFeelClassName()); 
		} 
		catch (Exception e) { 
		} 

		// create a panel 
		p = new JPanel(); 
		pwin = new JPanel();
		p.setBackground(Color.PINK); 
		// create a font 
		Font f1 = new Font("BOLD", 2, 60);
		Font f2 = new Font("BOLD", 2, 40);
		l.setFont(f1); 
		l2.setFont(f2);
		l_win.setFont(f1);
		l2_win.setFont(f2);
		// add contents to panel 
		p.add(l); 
		p.add(l2); 
		p.setLayout(new GridLayout(2, 1)); 
		
		pwin.add(l_win);
		pwin.add(l2_win);
		pwin.setLayout(new GridLayout(2, 1)); 
		/*menu = new Menu();
		menu.newGame.addActionListener(this);
		menu.loadGame.addActionListener(this);
		menu.leaderBoard.addActionListener(this);*/
		
		

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	} 
}
