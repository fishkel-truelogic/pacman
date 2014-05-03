package ar.com.finit.pacman.ui.ghost;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.Movible;
import ar.com.finit.pacman.ui.Pixel;
import ar.com.finit.pacman.ui.impl.Pacman;

/**
 * @author leo
 */
public abstract class Ghost extends Movible implements ActionListener {

	public static final int DELAY = 150;
	public static final int BLUE_DELAY = 550;
	public static int BLUE_TIME = 100;
	private static int DIFICULTY = 0;
	private static int ATTACK_RADIO = 1;
	private Timer timer;
	protected boolean blue = false;

	public Ghost(Board board) {
		super(board);
		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		move();
	}

	private void move() {
		if (x < 0) {
			x = Board.M_WIDTH - 1;
			direction = LEFT;
			moveLeft();
		} else if (x > Board.M_WIDTH - 1) {
			x = 0;
			direction = RIGHT;
			moveRight();
		} else {
			if (board.getLabyrinth().isIntersection(x, y)) {
				direction = newDir();
			}
			if (blue) {
				direction = getPacmanOppositeDirection(board.getPacman());
			}
			switch (direction) {
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			case UP:
				moveUp();
				break;
			case DOWN:
				moveDown();
				break;
			}

		}
	}

	private int newDir() {
		if (attackPacman(board.getPacman())) {
			return getPacmanDirection(board.getPacman());
		} else if (attackPacman(board.getMissPacman())) {
				return getPacmanDirection(board.getMissPacman());
		} else {
			return randomDir();
		}
	}

	private int getPacmanOppositeDirection(Pacman pacman) {
		int dir = oppositeDirectionOf(getPacmanDirection(pacman));
		if (canMoveIn(dir)) {
			return dir;
		} else {
			return randomDir();
		}
	}

	private int randomDir() {
		int dir = LEFT + new Random().nextInt(Math.abs(DOWN + 1 - LEFT));
		if (canMoveIn(dir) && !isOppositeToDirection(dir)) {
			return dir;
		} else {
			return randomDir();
		}
	}

	private boolean attackPacman(Pacman pacman) {
		if (pacman.getLives() < 0)
			return false;
		int px = pacman.getX();
		int py = pacman.getY();
		int radioXmin = x - ATTACK_RADIO;
		int radioXmax = x + ATTACK_RADIO;
		int radioYmin = y - ATTACK_RADIO;
		int radioYmax = y + ATTACK_RADIO;
		if (px >= radioXmin && px <= radioXmax && py >= radioYmin && py <= radioYmax) {
			return true;
		}
		int n = 2 + new Random().nextInt(100 - DIFICULTY);
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	private int getPacmanDirection(Pacman pacman) {
		int px = pacman.getX();
		int py = pacman.getY();
		int axisx = RIGHT;
		int axisy = DOWN;
		if (px <= x)
			axisx = LEFT;
		if (py <= y)
			axisy = UP;
		boolean canAxisx = canMoveIn(axisx);
		boolean canAxisy = canMoveIn(axisy);
		if (canAxisx && canAxisy) {
			int xx = Math.abs(px - x);
			int yy = Math.abs(py - y);
			if (xx >= yy) {
				return axisx;
			} else {
				return axisy;
			}
		} else if (canAxisx) {
			return axisx;
		} else if (canAxisy) {
			return axisy;
		} else {
			return randomDir();
		}
	}

	private boolean isOppositeToDirection(int dir) {
		return direction == oppositeDirectionOf(dir);
	}

	private int oppositeDirectionOf(int dir) {
		if (dir == LEFT)
			return RIGHT;
		if (dir == RIGHT)
			return LEFT;
		if (dir == UP)
			return DOWN;
		if (dir == DOWN)
			return UP;
		return 0;

	}

	public boolean isBlue() {
		return blue;
	}

	public void setBlue(boolean blue) {
		this.blue = blue;
		if (blue) {
			URL imgURL = getClass().getResource(Pixel.IMAGE_PATH + "BlueGhost.png");
			ImageIcon i = new ImageIcon(imgURL);
			image = i.getImage();
			this.getTimer().setDelay(Ghost.BLUE_DELAY);
		} else {
			this.getTimer().setDelay(Ghost.DELAY);
			image = null;
		}
		this.timer.restart();
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public abstract void born();


	public static void incrementDificulty() {
		DIFICULTY += 20;
		ATTACK_RADIO ++;
		BLUE_TIME -= 10; 
	}

	public static void restartDificulty() {
		DIFICULTY = 0;
		ATTACK_RADIO = 1;
		BLUE_TIME = 100; 
	}
	
	
	
	
}
