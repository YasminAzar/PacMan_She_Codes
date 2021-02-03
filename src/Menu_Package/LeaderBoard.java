package menu_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import sql_package.SQLiteManager;

public class LeaderBoard extends JPanel {
	
	private static final String URL = "jdbc:sqlite:MyDB.db";
	static String []names = {"", "", "", "", ""};
	static int [] score = {-1, -1, -1, -1, -1};
	static int [] amountOfGames = {-1, -1, -1, -1, -1};
	//static ArrayList<String> names = new ArrayList<>();
	//static ArrayList<Integer> score = new ArrayList<>();
	//static ArrayList<Integer> amountOfGames = new ArrayList<>();
	JPanel p_leaderBoard;
	public JLabel l1, l2, l3, l4, l5, l6;
	//public JLabel l_names;
	//public JLabel l_score;
	//public JLabel l_amountOfGames;
	
	public LeaderBoard() {
		setPreferredSize(new Dimension(100, 300));
		//setBackground(new Color(250, 230, 180));
		setFont(new Font("Serif", Font.BOLD, 200));
		//setBorder(new EmptyBorder(5,5,5,5));
		this.setBackground(Color.white);
		//setBorder(new EmptyBorder(5,5,5,5));
		//setLayout(new GridBagLayout());
		//setLocation(new Point(500, getHeight()));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.VERTICAL;
		//gbc.gridheight = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		//gbc.anchor = GridBagConstraints.CENTER;
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		//gbc.ipadx = 25;  // add padding
		//gbc.ipady = 25;
		//constraints.weighty = .5;
		//gbc.gridheight = 2;
		//gbc.weighty = 1.0;
		
		 
       
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
			//Font f1 = new Font("BOLD", 1, 20);
			//l1.setFont(f1);
			//l1.setText("1");
		
		/*l1 = new JLabel("1");	
		System.out.print("test l1: ");
		System.out.println(l1.getText());
		//l2.setText("2");
		l2 = new JLabel("2");
		System.out.print("test l2: ");
		System.out.println(l2.getText());
			//l3.setText("3");
			//l4.setText("4");
			//l5.setText("5");
		l3 = new JLabel("3");
		l4 = new JLabel("4");
		l5 = new JLabel("5");*/
		l6 = new JLabel("Return to the menu - click here");
		
			
			this.add(l1, gbc);
			this.add(l2, gbc);
			this.add(l3, gbc);
			this.add(l4, gbc);
			this.add(l5, gbc);
			
			
			//l6.setText("Return to the menu - click here");
			System.out.println(l6.getText());
			this.add(l6, gbc);
			this.setLayout(new GridLayout(6, 1)); 
			
		
		//JTable jt=new JTable(data,column);    
		//jt.setBounds(30,800,400,400); 
		//p_leaderBoard.add(jt, gbc);
		//tScore = new JTextField("Score: " + getScore(), 15); 
		//tPbEaten = new JTextField("Power Balls Eaten: ", 15);
		//tTimer = new JTextField("Time to eat the ghosts: ", 15);
		//tWarning = new JTextField("Ghost is Close", 15);
		//tLife = new JTextField("", 15);
		//this.add(tScore, gbc);
		//this.add(tPbEaten, gbc);
		//this.add(tTimer, gbc);
		//this.add(tWarning, gbc);
		//this.add(tLife, gbc);
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
