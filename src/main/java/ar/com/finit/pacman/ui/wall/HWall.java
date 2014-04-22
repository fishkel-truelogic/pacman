package ar.com.finit.pacman.ui.wall;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author leo
 */
public class HWall extends Wall {

	@Override
	public Image getImage() {
		if (image == null) {
			ImageIcon i = new ImageIcon("HWall.png");
			image = i.getImage();
		}
		return image;
	}

}
