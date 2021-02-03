package menu_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql_package.SQLiteManager;

public class LeaderBoard extends JPanel {
	
	JPanel p_leaderBoard;
	public JLabel l1, l2, l3, l4, l5, l6;
	
	public LeaderBoard() {
		setPreferredSize(new Dimension(700, 700));
		Font f1 = new Font("BOLD", 1, 30);
		this.setBackground(Color.white);
		setBorder(new EmptyBorder(10,10,10,10));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(30,0,30,0);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.BOTH;
		
		ArrayList<String> get_data = getData();
		int i = 0;
		System.out.println("Test the data");
		for(String data : get_data) {
			System.out.println(data + ", ");
		}
		
		for(String data : get_data) {
			i++;
			switch(i) {
			case 1:
				l1 = new JLabel(data);
				break;
			case 2:
				l2 = new JLabel(data);
				break;
			case 3:
				l3 = new JLabel(data);
				break;
			case 4:
				l4 = new JLabel(data);
				break;
			case 5:
				l5 = new JLabel(data);
				break;
			}
		}
		
		l6 = new JLabel("Return to the menu - click here");
		
		l1.setFont(f1);
		l1.setBackground(Color.RED);
		l1.setOpaque(true);
		l1.setForeground(Color.WHITE);
		l2.setFont(f1);
		l2.setBackground(Color.BLUE);
		l2.setOpaque(true);
		l2.setForeground(Color.WHITE);
		l3.setFont(f1);
		l3.setBackground(Color.PINK);
		l3.setOpaque(true);
		l3.setForeground(Color.WHITE);
		l4.setFont(f1);
		l4.setBackground(Color.ORANGE);
		l4.setOpaque(true);
		l4.setForeground(Color.WHITE);
		l5.setFont(f1);
		l5.setBackground(Color.RED);
		l5.setOpaque(true);
		l5.setForeground(Color.WHITE);
		l6.setFont(f1);
		l6.setBackground(Color.LIGHT_GRAY);
		l6.setOpaque(true);
		l6.setForeground(Color.BLACK);
		
			this.add(l1, gbc);
			this.add(l2, gbc);
			this.add(l3, gbc);
			this.add(l4, gbc);
			this.add(l5, gbc);
			this.add(l6, gbc);
			
	}
	private ArrayList<String> getData() {
		SQLiteManager.createNewDatabase();
		//SQLiteManager.createPlayersTable();
			//SQLiteManager.insertPlayerData("Dany", "2500", "3");
			//SQLiteManager.insertPlayerData("Omer", "2000", "3");
			//SQLiteManager.insertPlayerData("Adi", "1500", "3");
			//SQLiteManager.insertPlayerData("Tal", "1000", "3");
			//SQLiteManager.insertPlayerData("Or", "500", "3");

		ArrayList<String> players_data= SQLiteManager.selectAllUsers();
		System.out.println("players data is working");
		return players_data;
	}
	

}
