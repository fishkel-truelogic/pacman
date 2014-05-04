package ar.com.finit.pacman.ui.ghost.impl;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.ghost.Ghost;

/**
 * @author leo
 */
public class GreyGhost extends Ghost {

	private static final int JAIL_TIME = 50;

	public GreyGhost(Board board) {
		super(board, JAIL_TIME);
		born();
	}
}
