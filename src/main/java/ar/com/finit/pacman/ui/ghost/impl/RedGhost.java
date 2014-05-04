package ar.com.finit.pacman.ui.ghost.impl;

import ar.com.finit.pacman.ui.Board;
import ar.com.finit.pacman.ui.ghost.Ghost;

/**
 * @author leo
 */
public class RedGhost extends Ghost {

	private static final int JAIL_TIME = 10;

	public RedGhost(Board board) {
		super(board, JAIL_TIME);
		born();
	}
}
