package ar.com.finit.pacman.ui.ghost.impl;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.ghost.Ghost;

/**
 * @author leo
 */
public class RedGhost extends Ghost {

	public RedGhost(Board board) {
		super(board);
		born();
	}

	@Override
	public void born() {
		x = 14;
		y = 11;
		direction = LEFT;
		this.setBlue(false);
	}

}
