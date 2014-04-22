package ar.com.finit.pacman.ui.dot.impl;

import java.awt.Image;

import javax.swing.ImageIcon;

import ar.com.finit.pacman.ui.dot.Dot;

/**
 * @author leo
 */
public class SmallDot extends Dot {

	@Override
	public Image getImage() {
		if (image == null) {
			ImageIcon i = new ImageIcon("SmallDot.png");
			image = i.getImage();
		}
		return image;
	}

}
