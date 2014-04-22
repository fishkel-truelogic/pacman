package ar.com.finit.pacman.ui.ghost;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.Movible;
import ar.com.finit.pacman.ui.wall.RightTopWall;

/**
 * @author leo
 */
public abstract class Ghost extends Movible implements ActionListener {

	private static final int DELAY = 400;
	private Timer timer;
	private boolean blue = false;
	
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
				direction = randomDir();
			}
			switch(direction) {
			case LEFT: moveLeft(); break;
			case RIGHT: moveRight(); break;
			case UP: moveUp(); break;
			case DOWN: moveDown(); break;
			}
			 
		}
	}

	private int randomDir() {
		int dir = LEFT + new Random().nextInt(Math.abs(DOWN + 1 - LEFT));
		if (canMoveIn(dir) && !isOpositToDirection(dir)) {
			return dir;
		}
		return randomDir();
	}

	private boolean isOpositToDirection(int dir) {
		if (direction == LEFT) return dir == RIGHT;
		if (direction == RIGHT) return dir == LEFT;
		if (direction == UP) return dir == DOWN;
		if (direction == DOWN) return dir == UP;
		return false;
	}
	
	

}
