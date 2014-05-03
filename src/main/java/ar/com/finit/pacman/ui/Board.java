package ar.com.finit.pacman.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import ar.com.finit.pacman.ui.ghost.Ghost;
import ar.com.finit.pacman.ui.ghost.impl.GreyGhost;
import ar.com.finit.pacman.ui.ghost.impl.OrangeGhost;
import ar.com.finit.pacman.ui.ghost.impl.PinkGhost;
import ar.com.finit.pacman.ui.ghost.impl.RedGhost;
import ar.com.finit.pacman.ui.impl.Pacman;

/**
 * @author leo
 */
public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final int DELAY = 150;

	public static final int M_WIDTH = 29;

	public static final int M_HEIGHT = 31;

	private static final int B_WIDTH = M_WIDTH * Pixel.SIZE;

	private static final int B_HEIGHT = M_HEIGHT * Pixel.SIZE;

	private static final int MARGIN_LEFT = 5;

	private static final int MARGIN_TOP = 5;

	private static final int PTS_X = 100;

	private Timer timer;

	private Labyrinth labyrinth;

	private Pacman pacman;

	private Pacman missPacman;

	private ArrayList<Ghost> ghosts;

	private int moveIteration;

	private int level = 1;

	private boolean paused;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintLabyrinth(g);
		
		if (pacman.getLives() >= 0)
			paint(pacman, g);
		else
			pacman.die();
		
		
		if (missPacman.getLives() >= 0)
			paint(missPacman, g);
		else 
			missPacman.die();
		
		for (Ghost ghost : ghosts) {
			paint(ghost, g);
		}
		
		paintPoints(g);
		paintLives(g);
		paintLevel(g);
		
		if (pacman.getLives() < 0 && missPacman.getLives() < 0) {
			paintGameOver(g);
			timer.stop();
		}
		
		if (paused) {
			paintPaused(g);
		}
	}

	private void paintPaused(Graphics g) {
		String msg = "PAUSED";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, 13 * Pixel.SIZE, 15 * Pixel.SIZE);

	}

	private void paintGameOver(Graphics g) {
		String msg = "GAME OVER";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, 12 * Pixel.SIZE, 15 * Pixel.SIZE);
	}

	private void paintLevel(Graphics g) {
		String msg = "level:" + level;
		Font small = new Font("Helvetica", Font.BOLD, 10);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, M_WIDTH / 2 * Pixel.SIZE, 7);
	}

	private void paintLives(Graphics g) {
		int x = MARGIN_LEFT + 5, y = MARGIN_TOP + 18 * Pixel.SIZE;
		for (int i = 0; i < pacman.getLives(); i++) {
			g.drawImage(pacman.getLiveImage(), x, y, this);
			x += (i + 1) * Pixel.SIZE + 5;
		}

		x = ((M_WIDTH - 1) * Pixel.SIZE) - (MARGIN_LEFT + 5);
		y = MARGIN_TOP + 18 * Pixel.SIZE;
		for (int i = 0; i < missPacman.getLives(); i++) {
			g.drawImage(missPacman.getLiveImage(), x, y, this);
			x -= (i + 1) * Pixel.SIZE + 5;
		}
	}

	private void paintPoints(Graphics g) {
		Font small = new Font("Helvetica", Font.BOLD, 10);
		
		String msg = "Pacman: " + pacman.getPoints();
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, PTS_X, 7);

		
		msg = "Miss Pacman: " + missPacman.getPoints();
		g.drawString(msg, PTS_X + M_WIDTH / 2 * Pixel.SIZE, 7);
		
		

	}

	private void paint(Pixel pixel, Graphics g) {
		int x = MARGIN_LEFT + pixel.getX() * Pixel.SIZE - Pixel.SIZE + moveIteration;
		int y = MARGIN_TOP + pixel.getY() * Pixel.SIZE - Pixel.SIZE + moveIteration;
		g.drawImage(pixel.getImage(), x, y, this);

	}

	private void paintLabyrinth(Graphics g) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < M_HEIGHT; i++) {
			for (int j = 0; j < M_WIDTH; j++) {
				if (labyrinth.getPixels()[i][j] != null) {
					x = MARGIN_LEFT + j * Pixel.SIZE;
					y = MARGIN_TOP + i * Pixel.SIZE;
					g.drawImage(labyrinth.getPixels()[i][j].getImage(), x, y, this);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (labyrinth.containsDots()) {
			for (Ghost ghost : ghosts) {
				if (touch(ghost, pacman)) {
					if (ghost.isBlue()) {
						ghost.born();
						pacman.setPoints(pacman.getPoints() + 500);
					} else {
						pacman.setLives(pacman.getLives() - 1);
						pacman.born(Pacman.LEFT);
						for (Ghost g : ghosts) {
							g.born();
						}
						break;
					}
				}
				if (touch(ghost, missPacman)) {
					if (ghost.isBlue()) {
						ghost.born();
						missPacman.setPoints(missPacman.getPoints() + 500);
					} else {
						missPacman.setLives(missPacman.getLives() - 1);
						missPacman.born(Pacman.RIGHT);
						for (Ghost g : ghosts) {
							g.born();
						}
						break;
					}
				}
			}
			for (moveIteration = 0; moveIteration < Pixel.SIZE; moveIteration++) {
				repaint();
			}
		} else {
			levelUp();
		}
	}

	private void levelUp() {
		pacman.setPoints(pacman.getPoints() + 1000);
		missPacman.setPoints(missPacman.getPoints() + 1000);
		labyrinth = new Labyrinth();
		level++;
		if (level == 6) {
			timer.stop();
			pacman.getTimer().stop();
			missPacman.getTimer().stop();
		} else {
			pacman.born(Pacman.LEFT);
			missPacman.born(Pacman.RIGHT);
			for (Ghost g : ghosts) {
				g.born();
			}
			Ghost.incrementDificulty();
		}
	}

	private boolean touch(Ghost ghost, Pacman pacman) {
		if (pacman.getLives() >= 0) {
			int pxMin = pacman.getX() * Pixel.SIZE - Pixel.SIZE / 2;
			int pxMax = pacman.getX() * Pixel.SIZE + Pixel.SIZE / 2;
			int pyMin = pacman.getY() * Pixel.SIZE - Pixel.SIZE / 2;
			int pyMax = pacman.getY() * Pixel.SIZE + Pixel.SIZE / 2;
			int gxMin = ghost.getX() * Pixel.SIZE - Pixel.SIZE / 2;
			int gxMax = ghost.getX() * Pixel.SIZE + Pixel.SIZE / 2;
			int gyMin = ghost.getY() * Pixel.SIZE - Pixel.SIZE / 2;
			int gyMax = ghost.getY() * Pixel.SIZE + Pixel.SIZE / 2;
			if (pacman.getX() == ghost.getX()) {
				if (gyMax >= pyMin && gyMax <= pyMax) {
					return true;
				}
				if (gyMin >= pyMin && gyMin <= pyMax) {
					return true;
				}
			}
			if (pacman.getY() == ghost.getY()) {
				if (gxMax >= pxMin && gxMax <= pxMax) {
					return true;
				}
				if (gxMin >= pxMin && gxMin <= pxMax) {
					return true;
				}
			}
		}

		return false;
	}

	public Board() {
		super();
		pacman = new Pacman(this, Pacman.LEFT, "P");
		missPacman = new Pacman(this, Pacman.RIGHT, "MP");
		ghosts = new ArrayList<Ghost>();
		ghosts.add(new RedGhost(this));
		ghosts.add(new GreyGhost(this));
		ghosts.add(new PinkGhost(this));
		ghosts.add(new OrangeGhost(this));
		labyrinth = new Labyrinth();
		addKeyListener(new PacmanKeyListener(this));
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setFocusable(true);
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private class PacmanKeyListener extends KeyAdapter {

		private Board board;

		public PacmanKeyListener(Board board) {
			this.board = board;
		}

		@Override
		public void keyPressed(KeyEvent e) {

			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				board.onKeyLeft();
				break;
			case KeyEvent.VK_RIGHT:
				board.onKeyRight();
				break;
			case KeyEvent.VK_UP:
				board.onKeyUp();
				break;
			case KeyEvent.VK_DOWN:
				board.onKeyDown();
				break;
			case KeyEvent.VK_W:
				board.onKeyW();
				break;
			case KeyEvent.VK_A:
				board.onKeyA();
				break;
			case KeyEvent.VK_S:
				board.onKeyS();
				break;
			case KeyEvent.VK_D:
				board.onKeyD();
				break;
			case KeyEvent.VK_ENTER:
				board.onKeyEnter();
				break;
			case KeyEvent.VK_R:
				board.onKeyRestart();
				break;
			}
		}

	}

	public void onKeyRestart() {
		level = 1;
		Ghost.restartDificulty();
		labyrinth = new Labyrinth();
		for (Ghost ghost : ghosts) {
			ghost.born();
		}
		pacman = new Pacman(this, Pacman.LEFT, "P");
		missPacman = new Pacman(this, Pacman.RIGHT, "MP");
		timer.restart();
		repaint();
	}

	public void onKeyEnter() {
		if (!paused) {
			for (Ghost ghost : ghosts) {
				ghost.getTimer().stop();
			}
			pacman.getTimer().stop();
			missPacman.getTimer().stop();
			timer.stop();
		} else {
			for (Ghost ghost : ghosts) {
				ghost.getTimer().restart();
			}
			pacman.getTimer().restart();
			missPacman.getTimer().restart();
			timer.restart();
		}
		paused = !paused;
		repaint();

	}

	public void onKeyLeft() {
		missPacman.setNextDirection(Pacman.LEFT);
	}

	public void onKeyDown() {
		missPacman.setNextDirection(Pacman.DOWN);

	}

	public void onKeyUp() {
		missPacman.setNextDirection(Pacman.UP);

	}

	public void onKeyRight() {
		missPacman.setNextDirection(Pacman.RIGHT);

	}

	public void onKeyA() {
		pacman.setNextDirection(Pacman.LEFT);
	}

	public void onKeyS() {
		pacman.setNextDirection(Pacman.DOWN);

	}

	public void onKeyW() {
		pacman.setNextDirection(Pacman.UP);

	}

	public void onKeyD() {
		pacman.setNextDirection(Pacman.RIGHT);

	}

	public Labyrinth getLabyrinth() {
		return labyrinth;
	}

	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}

	public void bigDotAction() {
		for (Ghost ghost : ghosts) {
			ghost.setBlue(true);
		}
		timer.restart();
		for (ActionListener al : timer.getActionListeners()) {
			if (!al.equals(this)) {
				timer.removeActionListener(al);
			}
		}
		timer.addActionListener(new GhostActionListener(this));
	}

	private class GhostActionListener implements ActionListener {

		private int cont = 0;
		private Board board;

		public GhostActionListener(Board board) {
			this.board = board;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			cont++;
			if (cont == Ghost.BLUE_TIME) {
				board.bigDotRevert();
			}
		}

	}

	public void bigDotRevert() {
		for (Ghost ghost : ghosts) {
			ghost.setBlue(false);
		}
	}

	public Pacman getPacman() {
		return pacman;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}

	public Pacman getMissPacman() {
		return missPacman;
	}

	public void setMissPacman(Pacman missPacman) {
		this.missPacman = missPacman;
	}

}
