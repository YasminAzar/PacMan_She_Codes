package Board_Package;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;

public class PopupGameOver {
	// popup 
	Popup po; 

	// panel 
	JPanel p; 

	// popupfactory 
	PopupFactory pf;
	 
	PopupGameOver() 
	{ 
		pf = new PopupFactory(); 
		JLabel l = new JLabel("GAME OVER"); 
		JLabel l2 = new JLabel("SCORE: "); 

		try { 
			// set windows look and feel 
			UIManager.setLookAndFeel(UIManager. 
					getSystemLookAndFeelClassName()); 
		} 
		catch (Exception e) { 
		} 

		// create a panel 
		p = new JPanel(); 
		
		p.setBackground(Color.PINK); 
		// create a font 
		Font f1 = new Font("BOLD", 2, 60);
		Font f2 = new Font("BOLD", 2, 40);
		l.setFont(f1); 
		l2.setFont(f2);

		// add contents to panel 
		p.add(l); 
		p.add(l2); 
		p.setLayout(new GridLayout(2, 1)); 

	} 
}
