package ar.com.finit.pacman.ui.ghost.impl;

import java.awt.Image;

import javax.swing.ImageIcon;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.ghost.Ghost;

/**
 * @author leo
 */
public class OrangeGhost extends Ghost {

	public OrangeGhost(Board board) {
		super(board);
		x = 14;
		y = 11;
		direction = RIGHT;
	}

	@Override
	public Image getImage() {
		if (image == null) {
			ImageIcon i = new ImageIcon("OrangeGhost.png");
			image = i.getImage();
		}
		return image;
	}

}
