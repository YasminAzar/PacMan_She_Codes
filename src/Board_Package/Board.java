package Board_Package;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import Game_Constants_Package.GameConstants;
import Game_Objects.Ghosts;
import Game_Objects.Lives;
import Game_Objects.Pacman;
import Game_Objects.Power_Ball;
import Log_Package.PacmanLog;
import Menu_Package.Menu;
import Score_Package.GameScore;

class Positions {
	int x;
	int y;
	String name;
	String direction;
	public void setData(int x, int y, String name, String direction) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.direction = direction;
	}
}

public class Board extends JPanel implements ActionListener{

	private int boardOffset;
	private int blockWidth;
	private int blockHeight;
	private int pbIndex1, pbIndex2, pbIndex3, pbIndex4, pbIndex5,pbIndex6;
	private int boardWidth, boardHeight;
	Ghosts redGhost, blueGhost, pinkGhost, orangeGhost;
	Pacman pacman;
	Power_Ball powerBall_1, powerBall_2, powerBall_3, powerBall_4;
	Lives firstLife, secondLife, thirdLife;
	GameScore gameScore;
	String [][]map;
	//= Game_Constants_Package.GameConstants.BOARD_OPTION_1.clone() ;
	final String UP = "U";
	final String DOWN = "D";
	final String LEFT = "L";
	final String RIGHT = "R";
	final String BLUE = "1";
	final String WHITE = "0";
	final String EMPTY = "E";
	final String EXISTS = "exists";
	final String NOT_EXIST = "not exist";
	final String SMALL_BALL = "small_ball";
	final String POWER_BALL = "power_ball";
	int timerCounter;
	int ghost_offset;
	String direction;
	GridBagConstraints gbc;
	Positions pacman_postion;
	int [] cubeSize = new int[2];
	int [] ballsLocation = new int[2];
	int [] pbLocation = new int[6];
	int [] locInArray = new int[2];
	int [] redGhostLoc = new int[2];
	int [] blueGhostLoc = new int[2];
	int [] pinkGhostLoc = new int[2];
	int [] orangeGhostLoc = new int[2];
	int [] pacmanLoc = new int[2];
	private Menu menu;

	public Board(int board_number) {
		if (board_number == 1) {	
			map = Game_Constants_Package.GameConstants.BOARD_OPTION_1.clone() ;	
		} 
		else if (board_number == 2) {	
			map = Game_Constants_Package.GameConstants.BOARD_OPTION_2.clone() ;	
		} 
		else {	
			map = Game_Constants_Package.GameConstants.BOARD_OPTION_3.clone() ;	
		}
		boardWidth = (int)(GameConstants.SCREEN_WIDTH * GameConstants.BOARD_PERCENT);
		boardHeight = (int)(GameConstants.SCREEN_HEIGHT * GameConstants.BOARD_PERCENT);
		blockWidth = calcBlockSize(map.length, boardWidth,boardHeight)[0];
		blockHeight = calcBlockSize(map.length, boardWidth,boardHeight)[1];
		boardOffset = (GameConstants.SCREEN_WIDTH - boardWidth)/2;
		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		setLayout(new GridBagLayout());
		scorePanel();
		createPowerBalls();
		callPowerBalls();
		callGhosts();
		callPacman();
		callLives();
		addKeyBoard();	
	}

	/**
	 * This function places the power balls on the map
	 */
	private void createPowerBalls() {
		pbLocation = findPBLocation(map);
		pbIndex1 = pbLocation[0]; 
		pbIndex2 = pbLocation[1];
		pbIndex3 = pbLocation[2];
		pbIndex4 = pbLocation[3];
		pbIndex5 = pbLocation[4];
		pbIndex6 = pbLocation[5];
	}

	/**
	 * This function creates the selected game board
	 * @param g2d
	 */
	private void createBoard(Graphics2D g2d) {
		int w = getSize().width;
		int h = getSize().height;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, w, h);
		PacmanLog.log("creatBoard","map.length "+map.length+" map[0].length "+map[0].length);
		int index = 0;
		// EB back to 15
		int size = 15;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				PacmanLog.log("creatBoard","i "+i+" j "+j + " index " + index);
				index += 1;
				if(map[i][j] == "1") {
					//Draws and paints the block
					g2d.setColor(Color.BLUE);
					g2d.fillRect(j*blockWidth+boardOffset, i*blockHeight, blockWidth, blockHeight);
					g2d.setStroke(new BasicStroke(8f));
					PacmanLog.log("creatBoard","DRAW");
					g2d.setStroke(new BasicStroke(5));
					g2d.setColor(Color.BLUE);
				}
				else if(map[i][j] == "0") {
					//Draws and paints the white balls
					g2d.setColor(Color.WHITE);
					//Addition and subtraction to place the balls in the middle of the block
					g2d.fillOval(j*blockWidth+boardOffset+(int)(blockWidth*0.4), 
							i*blockHeight+(int)(blockWidth*0.4), (int)(blockWidth*0.25),
							(int)(blockHeight*0.25));
					g2d.setStroke(new BasicStroke(8f));
					PacmanLog.log("creatBoard","DRAW");
					g2d.setStroke(new BasicStroke(5));
					g2d.setColor(Color.WHITE);
				}
			}
		}
	}

	/**
	 * This function draws the new status on the screen in each new frame
	 * @param g
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// this is the transform I was using when I found the bug.
		createBoard(g2);
		
		updateGhost(redGhost);
		drawGhost(g2, redGhost);
		updateGhost(blueGhost);
		drawGhost(g2, blueGhost);
		updateGhost(pinkGhost);
		drawGhost(g2, pinkGhost);
		updateGhost(orangeGhost);
		drawGhost(g2, orangeGhost);
		drawPacman(g2, pacman);
		drawPowerBall(g2, powerBall_1);
		drawPowerBall(g2, powerBall_2);
		drawPowerBall(g2, powerBall_3);
		drawPowerBall(g2, powerBall_4);
		drawLives(g2, firstLife);
		drawLives(g2, secondLife);
		drawLives(g2, thirdLife);
		
	}

	/**
	 * This function prints the map
	 */
	private void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
			}
		}
	}

	/**
	 * This function calculates the size of the blocks that make up the walls
	 * @param arraySize
	 * @param boardWidth
	 * @param boardHeight
	 * @return cubeSize - an array of block size in pixels
	 */
	private int[] calcBlockSize(int arraySize, int boardWidth, int boardHeight) {
		blockWidth = boardWidth/arraySize;
		blockHeight = boardHeight/arraySize;
		cubeSize[0] = blockWidth;
		cubeSize[1] = blockHeight;
		return cubeSize;	
	}

	/**
	 * This function finds the initial indexes of ghosts and pacman
	 * @return array type Positions of the indexes (x,y), name and direction
	 */
	private Positions[] findIndexes() {
		int j = 0;
		int [] rows = {1, 5, 7, 9, 13};
		Positions positions[]= new Positions[5];
		for (int i = 0; i < rows.length; i++) {
			positions[i] = new Positions();
			if(rows[i] == 1) {
				// check from row 1 where i can go down
				for (j = 3; j < map.length; j++) {
					if(map[rows[i]][j].equals(WHITE) && map[rows[i]+1][j].equals(WHITE)) {
						//I get down
						positions[i].setData(rows[i], j, "rg", DOWN);
						break;
					}
				}
			}
			else if(rows[i] == 5) {
				for (j = 4; j < map.length; j++) {
					if(map[rows[i]][j].equals(WHITE) && map[rows[i]][j+1].equals(WHITE)) {
						//I get right
						positions[i].setData(rows[i], j, "bg", RIGHT);
						break;
					}
				}
			}
			else if(rows[i] == 7) {
				for (j = map.length/2; j < map.length; j++) {
					if(map[rows[i]][j].equals(WHITE)) {
						//I get right
						positions[i].setData(rows[i], j, "pac", RIGHT);
						pacman_postion = new Positions();
						pacman_postion.setData(rows[i], j, "pac", RIGHT);
						break;
					}
				}
			}
			else if(rows[i] == 9) {
				// TODO j start at 1
				for (j = 8; j < map.length; j++) {
					if(map[rows[i]][j].equals(WHITE) && map[rows[i]][j-1].equals(WHITE)) {
						//I get left
						positions[i].setData(rows[i], j, "pg", LEFT);
						break;
					}
				}
			}
			else if(rows[i] == 13) {
				for (j = 9; j < map.length; j++) {
					if(map[rows[i]][j].equals(WHITE) && map[rows[i]-1][j].equals(WHITE)) {
						//I get up
						positions[i].setData(rows[i], j, "og", UP);
						break;
					}
				}
			}
		}
		return positions;
	}

	/**
	 * This function find the power balls indexes
	 * @param gameBoard
	 * @return indexPB - index of the power ball on the game board
	 */
	private int[] findPBLocation(String[][] gameBoard) {
		int count = 0;
		ArrayList<Integer> row_with_2_empty_blocks = new ArrayList<Integer>();
		int min_i = gameBoard.length-1;
		int max_i = 0;
		int min_j_first_row = gameBoard.length-1;
		int max_j_first_row = 0;
		int min_j_last_row = gameBoard.length-1;
		int max_j_last_row = 0;
		int [] index_pb = new int[6];
		//Keeps the rows with at least 2 empty spaces in the arrayList 
		for (int i = 0; i < gameBoard.length; i++) {
			count = 0;
			for (int j = 0; j < gameBoard.length; j++) {
				if(gameBoard[i][j] == "0") {
					count++;
					if(count > 1 && !row_with_2_empty_blocks.contains(i)) {
						row_with_2_empty_blocks.add(i);
					}
				}
			}
		}
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard.length; j++) {
				//if we are in a empty block and in a row with at least 2 empty blocks
				if(gameBoard[i][j] == "0" && row_with_2_empty_blocks.contains(i)) {
					//Saves the indexes to the top balls
					if(i < min_i) {
						min_i = i;
						index_pb[0] = i;
						if(j < min_j_first_row) {
							index_pb[1] = j;
							min_j_first_row = j;
						}
					}
					else if(i == min_i){
						if(j > max_j_first_row) {
							index_pb[2] = j;
							max_j_first_row = j;
						}
					}
					//Saves the indexes to the bottom balls
					if(i > max_i) {
						max_i = i;
						index_pb[3] = i;
						min_j_last_row = gameBoard.length-1;
						if(j < min_j_last_row) {
							index_pb[4] = j;
							min_j_last_row = j;
						}
					}
					if(i >= max_i) {
						max_j_last_row = 0;
						if(j > max_j_last_row) {
							index_pb[5] = j;
							max_j_last_row = j;
						}
					}
				}
			}
		}
		return index_pb;
	}

	/**
	 * This function calls the ghosts to enter the game
	 */
	private void callGhosts() {
		Positions positions[] = findIndexes();
		// calculate ghost offset
		Image image_for_size = new ImageIcon("src/Images/redGhostGIF.gif").getImage();
		ghost_offset =(int)( blockWidth/2 - image_for_size.getHeight(null)/2);
		int x = positions[0].x;
		int y = positions[0].y;
		String direction = positions[0].direction;
		redGhost = new Ghosts("src/Images/redGhostGIF.gif", x, y, x*blockHeight+ghost_offset, 
				(int)(y*blockWidth+ghost_offset+boardOffset), direction, "rg");
		mapUpdater(redGhost.getGrid_x(), redGhost.getGrid_y(), redGhost.getNameOnMap());
		x = positions[1].x;
		y = positions[1].y;
		direction = positions[1].direction;
		blueGhost = new Ghosts("src/Images/blueGhostGIF.gif", x, y, x*blockHeight+ghost_offset, 
				(int)(y*blockWidth+ghost_offset+boardOffset), direction, "bg");
		mapUpdater(blueGhost.getGrid_x(), blueGhost.getGrid_y(), blueGhost.getNameOnMap());
		x = positions[3].x;
		y = positions[3].y;
		direction = positions[3].direction;
		pinkGhost = new Ghosts("src/Images/pinkGhostGIF.gif", x, y, x*blockHeight+ghost_offset, 
				(int)(y*blockWidth+ghost_offset+boardOffset), direction, "pg");
		mapUpdater(pinkGhost.getGrid_x(), pinkGhost.getGrid_y(), pinkGhost.getNameOnMap());
		x = positions[4].x;
		y = positions[4].y;
		direction = positions[4].direction;
		orangeGhost = new Ghosts("src/Images/orangeGhostGIF.gif", x, y, x*blockHeight+ghost_offset, 
				(int)(y*blockWidth+ghost_offset+boardOffset), direction, "og");
		mapUpdater(orangeGhost.getGrid_x(), orangeGhost.getGrid_y(), orangeGhost.getNameOnMap());
	}

	/**
	 * This function calls the pacman to enter the game
	 */
	private void callPacman() {
		Image pacman_image_for_size = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
		double pacman_offset = blockWidth/2 - pacman_image_for_size.getHeight(null)/2;
		int x = pacman_postion.x;
		int y = pacman_postion.y;
		String direction = pacman_postion.direction;
		pacman = new Pacman("src/Images/pacman_rightGIF.gif", x,y,x*blockHeight + (int)pacman_offset,
				(int)((y)*blockWidth+pacman_offset+boardOffset),
				direction);
		pacman.setNameOnMap("pac");
		mapUpdater(pacman.getGrid_x(), pacman.getGrid_y(), pacman.getNameOnMap());
	}

	/**
	 * This function calls the power balls to enter the game
	 */
	private void callPowerBalls() {
		Image power_ball_image_for_size = new ImageIcon("src/Images/powerball.png").getImage();
		double offsetPowerBall_w_h = blockWidth/2 - power_ball_image_for_size.getHeight(null)/2;
		powerBall_1 = new Power_Ball("src/Images/powerball.png", pbIndex1, pbIndex2, 
				pbIndex1*blockHeight + (int)offsetPowerBall_w_h, 
				(int)(pbIndex2*blockWidth+boardOffset+offsetPowerBall_w_h), 
				EXISTS, "pb1");
		mapUpdater(powerBall_1.getGrid_x(), powerBall_1.getGrid_y(), powerBall_1.getNameOnMap());
		powerBall_2 = new Power_Ball("src/Images/powerball.png", pbIndex1, pbIndex3, 
				pbIndex1*blockHeight + (int)offsetPowerBall_w_h, 
				(int)(pbIndex3*blockWidth+boardOffset+offsetPowerBall_w_h), 
				EXISTS, "pb2");
		mapUpdater(powerBall_2.getGrid_x(), powerBall_2.getGrid_y(), powerBall_2.getNameOnMap());
		powerBall_3 = new Power_Ball("src/Images/powerball.png", pbIndex4, pbIndex5, 
				pbIndex4*blockHeight + (int)offsetPowerBall_w_h, 
				(int)(pbIndex5*blockWidth+boardOffset+offsetPowerBall_w_h), 
				EXISTS, "pb3");
		mapUpdater(powerBall_3.getGrid_x(), powerBall_3.getGrid_y(), powerBall_3.getNameOnMap());
		powerBall_4 = new Power_Ball("src/Images/powerball.png", pbIndex4, pbIndex6, 
				pbIndex4*blockHeight + (int)offsetPowerBall_w_h, 
				(int)(pbIndex6*blockWidth+boardOffset+offsetPowerBall_w_h), 
				EXISTS, "pb4");
		mapUpdater(powerBall_4.getGrid_x(), powerBall_4.getGrid_y(), powerBall_4.getNameOnMap());
	}

	/**
	 * This function calls the lives (hearts) to enter the game
	 */
	private void callLives() {
		Image heart_image_for_size = new ImageIcon("src/Images/heart.png").getImage();
		double heart_offset = blockWidth/2 - heart_image_for_size.getHeight(null)/2;
		firstLife = new Lives("src/Images/heart.png",
				(int)(GameConstants.SCREEN_HEIGHT/2 - heart_image_for_size.getHeight(null)*4),
				(int)(boardOffset/2-heart_offset), EXISTS);
		secondLife = new Lives("src/Images/heart.png",
				(int)(GameConstants.SCREEN_HEIGHT/2- heart_image_for_size.getHeight(null)),
				(int)(boardOffset/2-heart_offset), EXISTS);
		thirdLife = new Lives("src/Images/heart.png",
				(int)(GameConstants.SCREEN_HEIGHT/2 + heart_image_for_size.getHeight(null)*2),
				(int)(boardOffset/2-heart_offset), EXISTS);
	}

	/**
	 * This function draws the ghosts on the game board
	 * @param g2d
	 * @param ghost
	 */
	private void drawGhost(Graphics2D g2d, Ghosts ghost) {
		g2d.drawImage(ghost.getGhostImage(), ghost.getLocation_y(), ghost.getLocation_x(), this);
	}

	/**
	 * This function draws the pacman on the game board
	 * @param g2d
	 * @param player
	 */
	private void drawPacman(Graphics2D g2d, Pacman player) {
		g2d.drawImage(player.getPacmanImage(), player.getLocation_y(), player.getLocation_x(), this);
	}

	/**
	 * This function draws the power balls on the game board
	 * @param g2d
	 * @param powerBall
	 */
	private void drawPowerBall(Graphics2D g2d, Power_Ball powerBall) {
		if(powerBall.getStatus() == EXISTS) {
			g2d.drawImage(powerBall.getPowerBallImage(), powerBall.getLocation_y(), 
					powerBall.getLocation_x(), this);
		}
	}

	/**
	 * This function draws the hearts on the game screen
	 * @param g2d
	 * @param lives
	 */
	private void drawLives(Graphics2D g2d, Lives lives) {
		if(lives.getStatus() == EXISTS) {
			g2d.drawImage(lives.getHeartImage(), lives.getLocation_y(), 
					lives.getLocation_x(), this);
		}
	}

	/**
	 * This function checks whether the position is inside the game board
	 * @param x
	 * @param y
	 * @return true - if the position is inside the game board
	 */
	private void sanityCheck(int x, int y) {
		int image_size = redGhost.getGhostImage().getWidth(null);
		double ghost_offset = (int)blockWidth/2 - image_size/2;
		if ((y >= boardOffset+ghost_offset-1) && (y <= boardOffset+blockWidth*map.length-ghost_offset)) {
			if  ((x >= ghost_offset) && (x <= blockWidth*map.length-ghost_offset)) {
				System.out.println("OK " + " x " + x + " y " + y);
			}
		}
		else
			System.out.println("NOT OK " + " x " + x + " y " + y);
	}

	/**
	 * This function listens to the buttons that are pressed on the keyboard 
	 * and sends commands according to each button
	 */
	private void addKeyBoard(){
		Image pacman_image_for_size = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
		double pacman_offset = blockWidth/2 - pacman_image_for_size.getHeight(null)/2;
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch(arg0.getKeyCode()) {
				case KeyEvent.VK_UP:
					if(isFree(pacman.getGrid_x()-1,pacman.getGrid_y()) == true){
						Image pacman_image_up = new ImageIcon("src/Images/pacman_upGIF.gif").getImage();
						pacman.setDirection(UP);
						mapUpdater(pacman.getGrid_x(), pacman.getGrid_y(), EMPTY);
						pacman.setGrid_x(pacman.getGrid_x()-1);
						pacman.setGrid_y(pacman.getGrid_y());
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_up);
						isThePacmanEatAllBalls();
						checkPowerBallTimer();
						isItGhostLocation(pacman.getGrid_x(), pacman.getGrid_y());
						if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							updateScore(SMALL_BALL);
						}
						mapUpdater(pacman.getGrid_x(), pacman.getGrid_y(), pacman.getNameOnMap());
					}
					break;
				case KeyEvent.VK_DOWN:
					if(isFree(pacman.getGrid_x()+1,pacman.getGrid_y()) == true){
						Image pacman_image_down = new ImageIcon("src/Images/pacman_downGIF.gif").getImage();
						pacman.setDirection(DOWN);
						mapUpdater(pacman.getGrid_x(), pacman.getGrid_y(), EMPTY);
						pacman.setGrid_x(pacman.getGrid_x()+1);
						pacman.setGrid_y(pacman.getGrid_y());
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_down);
						isThePacmanEatAllBalls();
						checkPowerBallTimer();
						isItGhostLocation(pacman.getGrid_x(), pacman.getGrid_y());
						if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							updateScore(SMALL_BALL);
						}
						mapUpdater(pacman.getGrid_x(), pacman.getGrid_y(), pacman.getNameOnMap());
					}
					break;
				case KeyEvent.VK_LEFT:
					if(isFree(pacman.getGrid_x(),pacman.getGrid_y()-1) == true){
						Image pacman_image_left = new ImageIcon("src/Images/pacman_leftGIF.gif").getImage();
						pacman.setDirection(LEFT);
						mapUpdater(pacman.getGrid_x(), pacman.getGrid_y(), EMPTY);
						pacman.setGrid_x(pacman.getGrid_x());
						pacman.setGrid_y(pacman.getGrid_y()-1);
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_left);
						isThePacmanEatAllBalls();
						checkPowerBallTimer();
						isItGhostLocation(pacman.getGrid_x(), pacman.getGrid_y());
						if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							updateScore(SMALL_BALL);
						}
						mapUpdater(pacman.getGrid_x(), pacman.getGrid_y(), pacman.getNameOnMap());
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(isFree(pacman.getGrid_x(),pacman.getGrid_y()+1) == true){
						Image pacman_image_right = new ImageIcon("src/Images/pacman_rightGIF.gif").getImage();
						pacman.setDirection(RIGHT);
						mapUpdater(pacman.getGrid_x(), pacman.getGrid_y(), EMPTY);
						pacman.setGrid_x(pacman.getGrid_x());
						pacman.setGrid_y(pacman.getGrid_y()+1);
						pacman.setLocation_x(pacman.getGrid_x()*blockHeight+(int)pacman_offset);
						pacman.setLocation_y(boardOffset+(pacman.getGrid_y()*blockWidth)+(int)pacman_offset);
						pacman.setPacmanImage(pacman_image_right);
						isThePacmanEatAllBalls();
						checkPowerBallTimer();
						isItGhostLocation(pacman.getGrid_x(), pacman.getGrid_y());
						if(isItSmallBallLocation(pacman.getGrid_x(), pacman.getGrid_y())) {
							updateScore(SMALL_BALL);
						}
						mapUpdater(pacman.getGrid_x(), pacman.getGrid_y(), pacman.getNameOnMap());
					}
					break;
				default:
					System.out.println("You did not press the correct button");
					//TODO defult and case key == KeyEvent.VK_ESCAPE && timer.isRunning
				}
			}
		});
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	/**
	 * This function checks if the location is available (not a wall)
	 * @param x
	 * @param y
	 * @return true - if it is available
	 */
	private boolean isFree(int x, int y) { 	
		if ((x >= 0 && x <= map.length) && (y >= 0 && y < map.length)) { 			
			if (!map[x][y].equals(BLUE)) {
				return true;	
			}
		}
		return false;
	}

	/**
	 * This function updates the score according to what the pacman ate
	 * @param ballType
	 * @return the updated score
	 */
	private void updateScore(String ballType) {
		int small_ball_points = 10;
		int power_ball_points = 50;
		if(ballType == SMALL_BALL) {
			gameScore.setScore(gameScore.getScore() + small_ball_points);
		}
		else if(ballType == POWER_BALL) {
			gameScore.setScore(gameScore.getScore() + power_ball_points);
			pacman.changeToImortal();
		}
		System.out.println("Score: " + gameScore.getScore());
	}

	//This function check if there is not more balls on the board
	private void isThePacmanEatAllBalls() {
		//boolean isTheBoardCealn = false;
		boolean thereIsBalls = false;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[i][j].equals(WHITE)) {
					thereIsBalls = true;
				}	
			}	
		}
		if(thereIsBalls == false){
			PopupGameOver popup_game_over = new PopupGameOver();
			menu = new Menu();
			popup_game_over.po = popup_game_over.pf.getPopup(this, popup_game_over.pwin, boardWidth/3, boardHeight/2); 
			popup_game_over.po.show();
			
		}
		/*if(ballsCounter == map.length*map.length) {
			PopupGameOver popup_game_over = new PopupGameOver();
			popup_game_over.po = popup_game_over.pf.getPopup(this, popup_game_over.pwin, boardWidth/3, boardHeight/3); 
			popup_game_over.po.show();
		}*/
	}
	private void isItGhostLocation(int x, int y) {
		if(pacman.isImortal() == true) return;
		if(map[x][y].equals("rg") || map[x][y].equals("bg") || 
				map[x][y].equals("pg") || map[x][y].equals("og")) {
			System.out.println("The Ghost eat the pacman");
			pacman.setLifeLeft(pacman.getLifeLeft()-1);
			gameScore.updateLifeMessage("Your lost a life " + pacman.getLifeLeft() + " left");
			switch(pacman.getLifeLeft()) {
			case 2:
				firstLife.setStatus(NOT_EXIST);
				break;
			case 1:
				secondLife.setStatus(NOT_EXIST);
				break;
			case 0:
				thirdLife.setStatus(NOT_EXIST);
				PopupGameOver popup_game_over = new PopupGameOver();
				menu = new Menu();
				popup_game_over.po = popup_game_over.pf.getPopup(this, popup_game_over.p, boardWidth/3, boardHeight/2); 
				popup_game_over.po.show();
				break;
			}
		}
	}

/**
 * This function checks if the Pacman has reached the power ball
 * @param x
 * @param y
 * @return True - if the Pacman got to the power ball
 */
private boolean isItPbLocatin(int x, int y) {
	if(map[x][y] == "pb1") { 
		powerBall_1.setStatus(NOT_EXIST);
		return true;
	}
	if(map[x][y] == "pb2") {
		powerBall_2.setStatus(NOT_EXIST);
		return true;
	}
	if(map[x][y] == "pb3") {
		powerBall_3.setStatus(NOT_EXIST);
		return true;
	}
	if(map[x][y] == "pb4") {
		powerBall_4.setStatus(NOT_EXIST);
		return true;
	}
	return false;
}

/**
 * This function checks if the Pacman has reached the small ball
 * @param x
 * @param y
 * @return True - if the Pacman got to the small ball
 */
private boolean isItSmallBallLocation(int x, int y) {
	if(map[x][y].equals(WHITE)) { 
		return true;
	}
	return false;
}

private void scorePanel() {
	GridBagConstraints constraints = new GridBagConstraints( );
	gameScore = new GameScore();
	constraints.ipadx = 25;  // add padding
	constraints.ipady = 25;
	//constraints.weighty = .5;
	constraints.gridheight = 2;
	constraints.weighty = 1.0;
	constraints.anchor = GridBagConstraints.PAGE_END;
	this.add(gameScore, constraints);//e, BorderLayout.PAGE_END
	this.invalidate();
	this.repaint();
}

/**
 * This function checks whether the pacman has reached the power ball,
 * and as soon as he has eaten it, a timer of 15 seconds starts, until it reaches to 0
 */
private void checkPowerBallTimer() {
	final int second = 1000;
	if(isItPbLocatin(pacman.getGrid_x(), pacman.getGrid_y())) {
		System.out.println("Eat PB");
		timerCounter = GameConstants.GHOST_IN_ACTIVE_TIME;
		ActionListener task = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(timerCounter < 0) {
					Timer t = (Timer)evt.getSource();
					t.stop();
				}
				else {
					gameScore.setPowerBallCounter(timerCounter);
					timerCounter -= 1;
				}		
			}
		};
		Timer timer = new Timer(second, task);
		timer.start();
		updateScore(POWER_BALL);		
	}			
}

/**
 * This function checks whether pacman is in the given location
 * @param x
 * @param y
 * @return true - if pacman is in this location
 */
private boolean isItPacmanLocation(int x, int y) {
	if(map[x][y].equals("pac")) { 
		pacman.setStatus(NOT_EXIST);
		return true;
	}
	return false;
}

/**
 * This function checks if the some ghost has eat the pacman 
 * and how many times it has already happened
 * if pacman is eaten 3 times, the game is over
 * @param currentGhost
 */
private void isGhostEatPacman(Ghosts currentGhost){
	if(pacman.isImortal() == true) return;
	if(isItPacmanLocation(currentGhost.getGrid_x(), currentGhost.getGrid_y()) ){
		System.out.println("The Ghost eat the pacman");
		pacman.setLifeLeft(pacman.getLifeLeft()-1);
		gameScore.updateLifeMessage("Your lost a life " + pacman.getLifeLeft() + " left");
		switch(pacman.getLifeLeft()) {
		case 2:
			firstLife.setStatus(NOT_EXIST);
			break;
		case 1:
			secondLife.setStatus(NOT_EXIST);
			break;
		case 0:
			thirdLife.setStatus(NOT_EXIST);
			PopupGameOver popup_game_over = new PopupGameOver();
			menu = new Menu();
			popup_game_over.po = popup_game_over.pf.getPopup(this, popup_game_over.p, boardWidth/3, boardHeight/3); 
			popup_game_over.po.show();
			//add(popup_game_over.menu);
			//this.pack();
			//this.setVisible(false);
			//this.remove(this);
			//this.revalidate();
			//this.repaint();
			//menu.createPanel(gbc);
			//this.menu.setVisible(true);
			//menu.newGame.addActionListener(this);
			//menu.loadGame.addActionListener(this);
			//menu.leaderBoard.addActionListener(this);
			//this.revalidate();
			//this.repaint();
			//this.add(menu);
			//this.revalidate();
			//this.repaint();
			//this.pack();

			//popup_game_over.po.hide();
			break;
		}
	}
}

/**
 * This function updates the map
 * @param gridX
 * @param gridY
 * @param nameOnMap
 */
private void mapUpdater(int gridX, int gridY, String nameOnMap) {
	map[gridX][gridY] = nameOnMap;
	printMap();
}

/**
 * This function updates the position of the ghosts on the game board
 * @param current
 */
private void updateGhost(Ghosts current) {
	int number_of_steps = blockWidth;
	System.out.println("moveCounter of " + current.getNameOnMap() + ": " + current.getMoveCounter());

	if(current.getMoveCounter() < number_of_steps) {
		current.setMoveCounter(current.getMoveCounter()+1);
		//per direction update location
		if(current.getDirection().equals(UP) ) {
			current.setLocation_x(current.getLocation_x()-1);
		}
		else if(current.getDirection().equals(DOWN)) {
			current.setLocation_x(current.getLocation_x()+1);
		}
		else if(current.getDirection().equals(RIGHT)) {
			current.setLocation_y(current.getLocation_y()+1);
		}
		else if(current.getDirection().equals(LEFT)) {
			current.setLocation_y(current.getLocation_y()-1);
		}
		//checking to changing map location and grid
		if(current.getMoveCounter() == (int)(number_of_steps/2)) {
			//save white balls and power balls if they was exist
			int x_prev = current.getGrid_x();
			int y_prev = current.getGrid_y();
			String last_value_for_map = current.getStandOn();
			if(current.getDirection().equals(UP) && !map[current.getGrid_x()-1][current.getGrid_y()].equals(BLUE) ) {
				current.setGrid_x(current.getGrid_x()-1);	
			}
			else if(current.getDirection().equals(DOWN) && !map[current.getGrid_x()+1][current.getGrid_y()].equals(BLUE)) {
				current.setGrid_x(current.getGrid_x()+1);
			}
			else if(current.getDirection().equals(RIGHT) && !map[current.getGrid_x()][current.getGrid_y()+1].equals(BLUE)) {
				current.setGrid_y(current.getGrid_y()+1);
			}
			else if(current.getDirection().equals(LEFT) && !map[current.getGrid_x()][current.getGrid_y()-1].equals(BLUE)) {
				current.setGrid_y(current.getGrid_y()-1);
			}
			current.setStandOn(map[current.getGrid_x()][current.getGrid_y()]);
			isGhostEatPacman(current);
			mapUpdater(x_prev, y_prev, last_value_for_map);
			mapUpdater(current.getGrid_x(), current.getGrid_y(), current.getNameOnMap());
		}
	}
	else if(current.getMoveCounter() >= number_of_steps) {
		//make sure we are in the right position
		int x = current.getGrid_x()*blockHeight+ghost_offset;
		int y = current.getGrid_y()*blockWidth+ghost_offset+boardOffset;
		current.setLocation_x(x);
		current.setLocation_y(y);
		current.setMoveCounter(0);
		current.setDirection(getGhostDirection(current.getGrid_x(),current.getGrid_y(), current.getDirection()));
		current.setMoveCounter(current.getMoveCounter()+1);
	}
	sanityCheck(current.getLocation_x(), current.getLocation_y());
}

/**
 * This function updates the direction of the ghosts
 * @param x
 * @param y
 * @param prev_direction
 * @return the updated direction
 */
private String getGhostDirection(int x, int y, String prev_direction) {
	direction = "";
	int index_to_remove;
	char char_to_remove;
	if(x-1 >= 0 && !map[x-1][y].equals(BLUE)) {
		direction = direction.concat(UP);
	}
	if(x+1 < map.length && !map[x+1][y].equals(BLUE)) {
		direction = direction.concat(DOWN);
	}
	if(y-1 >= 0 && !map[x][y-1].equals(BLUE)) {
		direction = direction.concat(LEFT);
	}
	if(y+1 < map.length && !map[x][y+1].equals(BLUE)) {
		direction = direction.concat(RIGHT);
	}
	//check if I need to remove the direction I came from
	if(prev_direction.equals(LEFT)) {
		index_to_remove = direction.indexOf(RIGHT);
		char_to_remove = direction.charAt(index_to_remove);
		direction = direction.replace(char_to_remove, ' ');
		System.out.println("x=" + x +" y=" + y + " = " + direction);	
	}
	if(prev_direction.equals(RIGHT)) {
		index_to_remove = direction.indexOf(LEFT);
		char_to_remove = direction.charAt(index_to_remove);
		direction = direction.replace(char_to_remove, ' ');
		System.out.println("x=" + x +" y=" + y + " = " + direction);
	}
	if(prev_direction.equals(UP)) {
		index_to_remove = direction.indexOf(DOWN);
		char_to_remove = direction.charAt(index_to_remove);
		direction = direction.replace(char_to_remove, ' ');
		System.out.println("x=" + x +" y=" + y + " = " + direction);
	}
	if(prev_direction.equals(DOWN)) {
		index_to_remove = direction.indexOf(UP);
		char_to_remove = direction.charAt(index_to_remove);
		direction = direction.replace(char_to_remove, ' ');
		System.out.println("x=" + x +" y=" + y + " = " + direction);
	}
	if(direction.length() == 0) {
		System.out.println("no direction");
	}
	String temp = "";
	for (int i = 0; i < direction.length(); i++) {
		if(direction.charAt(i) != ' ') {
			temp = temp.concat(String.valueOf(direction.charAt(i)));
		}
	}
	//TODO check if there is ghost at this direction near to it- if there is- go to another direction
	if(temp.length() == 1) {
		return temp;
	}
	else {
		int r = (int) Math.random()*temp.length();
		return String.valueOf(temp.charAt(r));
	}
}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
}
}