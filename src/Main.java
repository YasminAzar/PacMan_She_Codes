
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFrame;

import board_package.Board;
import game_constants_package.GameConstants;
import menu_package.LeaderBoard;
import menu_package.LoadGameMenu;
import menu_package.Menu;
import score_package.GameScore;

public class Main extends JFrame implements ActionListener {

	private Menu menu;
	private LoadGameMenu loadGameMenu;
	private Board game_board;
	private LeaderBoard leaderBoard;

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
		this.revalidate(); 
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
			game_board = new Board(1);
			game_board.setPreferredSize(new Dimension(width,height));
			game_board.popupGameOver.l_endGame.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					//send back to lose the board
					replayGame();
				}
			});
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

			this.add(loadGameMenu);
			this.revalidate();
			this.repaint();
			this.pack();
		}

		//if we press the "leaderBoard" button, a new window 
		//will open with the score table
		if(arg0.getActionCommand().equals("leaderBoard")) {
			this.menu.setVisible(false);
			this.remove(menu);
			System.out.println("Leader Board is pressed");
			//GridBagConstraints constraints = new GridBagConstraints( );
			leaderBoard = new LeaderBoard();
			
			//constraints.ipadx = 25;  // add padding
			//constraints.ipady = 25;
			//constraints.weighty = .5;
			//constraints.gridheight = 2;
			//constraints.weighty = 1.0;
			//constraints.anchor = GridBagConstraints.PAGE_END;
			this.add(leaderBoard, BorderLayout.PAGE_START);
			this.invalidate();
			this.repaint();
		}
	}

	private void replayGame() {
		this.game_board.popupGameOver.po.hide();
		this.game_board.setVisible(false);
		this.remove(game_board);
		initMenu();
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
			game_board = new Board(board_number);
			game_board.setPreferredSize(new Dimension(width,height));
			game_board.popupGameOver.l_endGame.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					// send back to close the board
					replayGame();

				}
			});
			add(game_board, BorderLayout.PAGE_START);
			revalidate();
			repaint();
			pack(); 
		}
	}
}
