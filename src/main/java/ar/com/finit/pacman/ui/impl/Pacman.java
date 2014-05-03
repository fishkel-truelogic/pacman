package ar.com.finit.pacman.ui.impl;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.Movible;
import ar.com.finit.pacman.ui.Pixel;
import ar.com.finit.pacman.ui.dot.Dot;
import ar.com.finit.pacman.ui.dot.impl.BigDot;

/**
 * @author leo
 */
public class Pacman extends Movible implements ActionListener {

	private Timer timer;
	private int nextDirection;
	private int points = 0;
	private Image liveImage;
	private int lives;
	private String imageName;
	private static final int DELAY = 150;
	
	public Pacman(Board board, int firstDir, String imageName) {
		super(board);
		born(firstDir);
		this.imageName = imageName;
		lives = 3;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void born(int firstDir) {
		x = 14;
		y = 23;
		direction = firstDir;
		nextDirection = firstDir;
		
	}
	
	public Image getLiveImage() {
		if (liveImage == null) {
			URL imgURL = getClass().getResource(Pixel.IMAGE_PATH + imageName + "_RIGHT.png");
			ImageIcon i = new ImageIcon(imgURL);
			liveImage = i.getImage();
		}
		return liveImage;
	}
	
	@Override
	public Image getImage() {
		String imageName = null;
		switch (direction) {
		case LEFT: imageName = this.imageName + "_LEFT.png"; break;
		case RIGHT: imageName = this.imageName + "_RIGHT.png"; break;
		case UP: imageName = this.imageName + "_UP.png"; break;
		case DOWN: imageName = this.imageName + "_DOWN.png"; break;
		}
		URL imgURL = getClass().getResource(Pixel.IMAGE_PATH + imageName);
		ImageIcon i = new ImageIcon(imgURL);
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
			if (board.getLabyrinth().getPixels()[y][x] instanceof BigDot) {
				board.bigDotAction();
				points  += 475;
			}
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

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void die() {
		this.getTimer().stop();
		this.setX(0);
		this.setY(0);
	}
	
}
