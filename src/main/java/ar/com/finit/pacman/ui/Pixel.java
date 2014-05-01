package ar.com.finit.pacman.ui;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * @author leo
 */
public abstract class Pixel {

	public static final int SIZE = 16;

	public static final String IMAGE_PATH = "/META-INF/";
	
	protected Image image;
	
	protected int x;
	
	protected int y;
	
	public Image getImage() {
		if (image == null) {
			URL imgURL = getClass().getResource(Pixel.IMAGE_PATH + this.getClass().getSimpleName() + ".png");
			ImageIcon i = new ImageIcon(imgURL);
			image = i.getImage();
		}
		return image;
	}

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
