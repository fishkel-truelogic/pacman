package ar.com.finit.pacman.ui;

import java.awt.Image;

/**
 * @author leo
 */
public abstract class Pixel {

	public static final int SIZE = 16;
	
	protected Image image;
	
	protected int x;
	
	protected int y;
	
	public abstract Image getImage();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

}
