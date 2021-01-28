
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Board_Package.Board;
import Game_Constants_Package.GameConstants;
import Menu_Package.LoadGameMenu;
import Menu_Package.Menu;

public class Main extends JFrame implements ActionListener {

	private static Main gameMain;
	private Menu menu;
	private LoadGameMenu loadGameMenu;

	public static void main(String[] args) {
		Main game_main = new Main();
		game_main.initFrame(args);

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
		if(arg0.getActionCommand().equals("newGame")) {
			this.menu.setVisible(false);
			this.remove(menu);
			System.out.println("New Game is pressed");
			Board game_board = new Board(1);
			game_board.setPreferredSize(new Dimension(width,height));
			this.add(game_board, BorderLayout.PAGE_START);
			this.revalidate();
			this.repaint();
			this.pack();

		}
		else if(arg0.getActionCommand().equals("loadGame")) {
			this.menu.setVisible(false);
			this.remove(menu);
			System.out.println("Load Game is pressed");
			loadGameMenu = new LoadGameMenu();
			loadGameMenu.firstGameOption.addActionListener(new LoadGameActionListener());
			loadGameMenu.secondGameOption.addActionListener(new LoadGameActionListener());
			loadGameMenu.thirdGameOption.addActionListener(new LoadGameActionListener());
			this.add(loadGameMenu/*, BorderLayout.PAGE_START*/);
			this.revalidate();
			this.repaint();
			this.pack();	
		}

		//if we press the "leaderBoard" button, a new window 
		//will open with the score table
		if(arg0.getActionCommand().equals("leaderBoard")) {
			System.out.println("Leader Board is pressed");
		}
	}

	class LoadGameActionListener implements ActionListener  {
		public void actionPerformed(ActionEvent e) {
			//do something usefull
			//.....
			int height = GameConstants.SCREEN_HEIGHT;
			int width = GameConstants.SCREEN_WIDTH;
			System.out.println("LoadGameActionListener "+e.getActionCommand());
			System.out.println("LoadGameActionListener "+e.getActionCommand());
			int board_number;
			if(e.getActionCommand().equals("firstGameOption")) {
				board_number = 1;
			} 
			else if(e.getActionCommand().equals("secondGameOption")) {
				board_number = 2;
			} 
			else {
				board_number = 3;
			}

			loadGameMenu.setVisible(false);
			loadGameMenu.remove(menu);
			Board game_board = new Board(board_number);
			game_board.setPreferredSize(new Dimension(width,height));
			add(game_board, BorderLayout.PAGE_START);
			revalidate();
			repaint();
			pack(); 
		}
	}

}
