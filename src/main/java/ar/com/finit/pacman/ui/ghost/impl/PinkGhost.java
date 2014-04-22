package ar.com.finit.pacman.ui.ghost.impl;

import java.awt.Image;

import javax.swing.ImageIcon;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.ghost.Ghost;

/**
 * @author leo
 */
public class PinkGhost extends Ghost {

	public PinkGhost(Board board) {
		super(board);
		x = 14;
		y = 11;
	}

	@Override
	public Image getImage() {
		if (image == null) {
			ImageIcon i = new ImageIcon("PinkGhost.png");
			image = i.getImage();
		}
		return image;
	}

}