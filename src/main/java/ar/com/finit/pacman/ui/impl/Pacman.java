package ar.com.finit.pacman.ui.impl;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.Movible;
import ar.com.finit.pacman.ui.dot.Dot;

/**
 * @author leo
 */
public class Pacman extends Movible implements ActionListener {

	private Timer timer;
	private int nextDirection;
	private int points = 0;
	
	public Pacman(Board board) {
		super(board);
		x = 14;
		y = 23;
		direction = 0;
		timer = new Timer(250, this);
		timer.start();
	}
	
	@Override
	public Image getImage() {
		String imageName = null;
		switch (direction) {
		case LEFT: imageName = "P_LEFT.png"; break;
		case RIGHT: imageName = "P_RIGHT.png"; break;
		case UP: imageName = "P_UP.png"; break;
		case DOWN: imageName = "P_DOWN.png"; break;
		}
		ImageIcon i = new ImageIcon(imageName);
		image = i.getImage();
		return image;
	}


	public int getNextDirection() {
		return nextDirection;
	}

	public void setNextDirection(int nextDirection) {
		this.nextDirection = nextDirection;
	}
	
	public void move() {
		boolean move = false;
		if (canMoveIn(nextDirection)) {
			direction = nextDirection;
			move = true;
		} else if (canMoveIn(direction)) {
			move = true;
		}
		if (move) {
			switch(direction) {
			case LEFT: moveLeft(); break;
			case RIGHT: moveRight(); break;
			case UP: moveUp(); break;
			case DOWN: moveDown(); break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		eat();
		move();
	}

	private void eat() {
		if (board.getLabyrinth().getPixels()[y][x] instanceof Dot) {
			board.getLabyrinth().getPixels()[y][x] = null;
			points  += 100;
			
		}
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	
	
}
