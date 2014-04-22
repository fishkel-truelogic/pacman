package ar.com.finit.pacman.ui;

import ar.com.finit.pacman.ui.dot.Dot;

/**
 * @author leo
 */
public abstract class Movible extends Pixel {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	protected int direction;
	protected Board board;
	
	
	public Movible(Board board) {
		this.board = board;
	}
	
	protected void moveDown() {
		y++;
	}

	protected void moveUp() {
		y--;
	}

	protected void moveRight() {
		x++;
	}

	protected void moveLeft() {
		x--;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	protected boolean canMoveIn(int dir) {
		int xx = new Integer(x);
		int yy = new Integer(y);
		switch(dir) {
		case LEFT: xx--; break;
		case RIGHT: xx++;break;
		case UP: yy--; break;
		case DOWN: yy++; break;
		}
		if (xx < 0) {
			x = Board.M_WIDTH;
			return true;
		}
		if (xx > Board.M_WIDTH - 1) {
			x = -1;
			return true;
		}
		
		return board.getLabyrinth().getPixels()[yy][xx] == null || board.getLabyrinth().getPixels()[yy][xx] instanceof Dot ;
	}
	
	
}
