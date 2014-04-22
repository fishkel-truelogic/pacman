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
import ar.com.finit.pacman.ui.ghost.impl.BlueGhost;
import ar.com.finit.pacman.ui.ghost.impl.OrangeGhost;
import ar.com.finit.pacman.ui.ghost.impl.PinkGhost;
import ar.com.finit.pacman.ui.ghost.impl.RedGhost;
import ar.com.finit.pacman.ui.impl.Pacman;


/**
 * @author leo
 */
public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final int DELAY = 1;

	public static final int M_WIDTH = 29;

	public static final int M_HEIGHT = 31;

	private static final int B_WIDTH = M_WIDTH * Pixel.SIZE;

	private static final int B_HEIGHT = M_HEIGHT * Pixel.SIZE;

	private static final int MARGIN_LEFT = 5;

	private static final int MARGIN_TOP = 5;
	
	private Timer timer;
	
	private Labyrinth labyrinth;
	
	private Pacman pacman;
	
	private ArrayList<Ghost> ghosts;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintLabyrinth(g);
		paint(pacman, g);
		for (Ghost ghost: ghosts) {
			paint(ghost, g);
		}
		paintPoints(g);
	}


	private void paintPoints(Graphics g) {
		String msg = "Pts:" + pacman.getPoints();
		Font small = new Font("Helvetica", Font.BOLD, 10);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, 7, 7);
		
	}


	private void paint(Pixel pixel, Graphics g) {
		int x = MARGIN_LEFT + pixel.getX() * Pixel.SIZE;;
		int y = MARGIN_TOP + pixel.getY() * Pixel.SIZE;
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
		for (Ghost ghost: ghosts) {
			if (pacman.getX() == ghost.getX() && pacman.getY() == ghost.getY()) {
				if(ghost.isBlue()) {
					ghost.setX(14);
					ghost.setY(11);
					ghost.setBlue(false);
					ghost.setDirection(Movible.LEFT);
					pacman.setPoints(pacman.getPoints() + 500);
				} else {
					timer.stop();
					pacman.getTimer().stop();
					break;
				}
			}
		}
		repaint();
	}
	
	public Board() {
		super();
		pacman = new Pacman(this);
		ghosts= new ArrayList<Ghost>();
		ghosts.add(new RedGhost(this));
		ghosts.add(new BlueGhost(this));
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

			switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT: board.onKeyLeft(); break;
				case KeyEvent.VK_RIGHT: board.onKeyRight(); break;
				case KeyEvent.VK_UP: board.onKeyUp(); break;
				case KeyEvent.VK_DOWN: board.onKeyDown(); break;
			}
		}

	}

	public void onKeyLeft() {
		pacman.setNextDirection(Pacman.LEFT);
	}

	public void onKeyDown() {
		pacman.setNextDirection(Pacman.DOWN);
		
	}

	public void onKeyUp() {
		pacman.setNextDirection(Pacman.UP);
		
	}

	public void onKeyRight() {
		pacman.setNextDirection(Pacman.RIGHT);
		
	}

	public Labyrinth getLabyrinth() {
		return labyrinth;
	}

	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}


	public void bigDotAction() {
		for (Ghost ghost: ghosts) {
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
			if (cont == 5000) {
				board.bigDotRevert();
			}
		}
		
	}

	public void bigDotRevert() {
		for (Ghost ghost: ghosts) {
			ghost.setBlue(false);
		}
	}

	public Pacman getPacman() {
		return pacman;
	}


	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}
	
}
