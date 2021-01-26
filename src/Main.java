
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Board_Package.Board;
import Game_Constants_Package.GameConstants;
import Menu_Package.LoadGameMenu;
import Menu_Package.Menu;
import Score_Package.GameScore;

public class Main extends JFrame implements ActionListener {

	private static Main gameMain;
	private Menu menu;

	public static void main(String[] args) {
		Main game_main = new Main();
		game_main.initFrame(args);
		
		//
		//Timer timer = new Timer(); 
        //TimerTask task = new Board_Package.HelperForTimer(); 
        //timer.schedule(task, 1000, 1000); 
	}

	/**
	 * This function defines and initializes the frame
	 * @param args
	 */
	private void initFrame(String[] args) {
		int height = GameConstants.SCREEN_HEIGHT;
		int width = GameConstants.SCREEN_WIDTH;
		// set the frame height and width
		this.setPreferredSize(new Dimension(width, height));
		this.setBounds(0, 0, width, height);
		this.setTitle("Pac Man Game");
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.paint(this.getGraphics());

		initMenu();
	}

	/**
	 * This function initializes the menu screen
	 */
	private void initMenu() {
		menu = new Menu();
		menu.newGame.addActionListener(this);
		menu.loadGame.addActionListener(this);
		menu.leaderBoard.addActionListener(this);
		this.add(menu);
		this.pack();
	}

	
	// EB remove override
	/**
	 * This function describes the actions that will happen if you press the main buttons
	 * @param arg0
	 */
	public void actionPerformed(ActionEvent arg0) {
		int height = GameConstants.SCREEN_HEIGHT;
		int width = GameConstants.SCREEN_WIDTH;
		if("newGame".equals(arg0.getActionCommand())) {
			this.menu.setVisible(false);
			this.remove(menu);
			System.out.println("New Game is pressed");
			Board game_board = new Board();
			//UpdatedData updated_data = new UpdatedData();
			//GameScore score = new GameScore();

			game_board.setPreferredSize(new Dimension(width,height));
			this.add(game_board, BorderLayout.PAGE_START);
			//score.setPreferredSize(new Dimension(100,30));
			//this.add(game_board, BorderLayout.CENTER);
			this.revalidate();
			//this.add(score, BorderLayout.SOUTH);
			//this.revalidate();
			this.repaint();
			this.pack();
			
		}

		else if("loadGame".equals(arg0.getActionCommand())) {
			System.out.println("Load Game is pressed");
			LoadGameMenu load_game_menu = new LoadGameMenu();
			
			
		}

		//if we press the "leaderBoard" button, a new window 
		//will open with the score table
		if("leaderBoard".equals(arg0.getActionCommand())) {
			System.out.println("Leader Board is pressed");
		}

	}

}
