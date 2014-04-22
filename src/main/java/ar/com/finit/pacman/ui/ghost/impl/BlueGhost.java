package ar.com.finit.pacman.ui.ghost.impl;

import java.awt.Image;

import javax.swing.ImageIcon;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.ghost.Ghost;

/**
 * @author leo
 */
public class BlueGhost extends Ghost {

	public BlueGhost(Board board) {
		super(board);
		x = 14;
		y = 11;
	}

	@Override
	public Image getImage() {
		if (image == null) {
			ImageIcon i = new ImageIcon("BlueGhost.png");
			image = i.getImage();
		}
		return image;
	}

}
