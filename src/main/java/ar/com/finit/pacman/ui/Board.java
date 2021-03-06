package ar.com.finit.pacman.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import ar.com.finit.pacman.score.Score;
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
	
	private Timer timer;
	
	private Labyrinth labyrinth;
	
	private Pacman pacman;
	
	private ArrayList<Ghost> ghosts;

	private int moveIteration;
	
	private int level = 1;
	
	private boolean paused;

	private Score score;
	
	JTextField txtFld;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (pacman.getLives() < 0 || this.level > 5) {
			if (txtFld == null) {
				txtFld = new JTextField();
				txtFld.setBounds(((M_WIDTH / 2) - 4) * Pixel.SIZE, M_HEIGHT * Pixel.SIZE - Pixel.SIZE * 5 , 200, 32);
				txtFld.setVisible(true);
				txtFld.addKeyListener(new InputScoreListener(this));
				add(txtFld);
				repaint();
				paintInputName(g);
			}
			paintGameOver(g);
		} else {
			paintLabyrinth(g);
			paint(pacman, g);
			for (Ghost ghost: ghosts) {
				paint(ghost, g);
			}
			paintPoints(g);
			paintLives(g);
			paintLevel(g);
			if (paused) {
				paintPaused(g);
			}
		}
	}


	private void paintPaused(Graphics g) {
		String msg = "PAUSED";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, 13 * Pixel.SIZE, 15 * Pixel.SIZE);
		
	}

	private void paintInputName(Graphics g) {
		String msg = "Name: ";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, 3 * Pixel.SIZE, M_HEIGHT * Pixel.SIZE - Pixel.SIZE * 5 );
		
	}


	private void paintGameOver(Graphics g) {
		String msg = "GAME OVER";
		Font font = new Font("Helvetica", Font.BOLD, 14);
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(msg, 12 * Pixel.SIZE, 2 * Pixel.SIZE);
		
		font = new Font("Helvetica", Font.BOLD, 22);
		int i = 0;
		for (int j = 0; j < 10; j++) {
			msg = score.getScore()[j][0] + "   ---   " + score.getScore()[j][1];
			g.drawString(msg, 12 * Pixel.SIZE, (5 + i) * Pixel.SIZE);
			i += 2;
		}
	}


	private void paintLevel(Graphics g) {
		String msg = "level:" + level;
		Font small = new Font("Helvetica", Font.BOLD, 10);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, 200, 7);
	}


	private void paintLives(Graphics g) {
		int x = MARGIN_LEFT + 5 , y = MARGIN_TOP + 18 * Pixel.SIZE;
		for (int i = 0; i < pacman.getLives(); i++) {
			g.drawImage(pacman.getLiveImage(), x, y, this);
			x += + (i + 1) * Pixel.SIZE + 5;
		}
	}


	private void paintPoints(Graphics g) {
		String msg = "Pts:" + pacman.getPoints();
		Font small = new Font("Helvetica", Font.BOLD, 10);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, 7, 7);
		
	}


	private void paint(Pixel pixel, Graphics g) {
		int x = MARGIN_LEFT + pixel.getX() * Pixel.SIZE - Pixel.SIZE + moveIteration ;
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
		if(labyrinth.containsDots()) {
			for (Ghost ghost: ghosts) {
				if (ghostTouchPacman(ghost)) {
					if(ghost.isBlue()) {
						ghost.born();
						pacman.setPoints(pacman.getPoints() + 500);
					} else {
						pacman.setLives(pacman.getLives() -1);
						if (pacman.getLives() < 0) {
							timer.stop();
							pacman.getTimer().stop();
						} else {
							pacman.born();
							for (Ghost g: ghosts) {
								g.born();
							}
						}
						break;
					}
				}
			}
			for (moveIteration = 0; moveIteration < Pixel.SIZE; moveIteration ++) {
				repaint();
			}
		} else {
			levelUp();
		}
	}
	
	private void levelUp() {
		pacman.setPoints(pacman.getPoints() + 1000);
		labyrinth = new Labyrinth();
		level++;
		if (level == 6) {
			timer.stop();
			pacman.getTimer().stop();
		} else {
			pacman.born();
			for (Ghost g: ghosts) {
				g.born();
			}
			Ghost.incrementDificulty();
		}
	}


	private boolean ghostTouchPacman(Ghost ghost) {
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
		
		return false;
	}


	public Board() {
		super();
		score = new Score();
		pacman = new Pacman(this);
		ghosts= new ArrayList<Ghost>();
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

			switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT: board.onKeyLeft(); break;
				case KeyEvent.VK_RIGHT: board.onKeyRight(); break;
				case KeyEvent.VK_UP: board.onKeyUp(); break;
				case KeyEvent.VK_DOWN: board.onKeyDown(); break;
				case KeyEvent.VK_ENTER: board.onKeyEnter(); break;
				case KeyEvent.VK_R: board.onKeyRestart(); break;
			}
		}

	}

	public void onKeyLeft() {
		pacman.setNextDirection(Pacman.LEFT);
	}

	public void onKeyRestart() {
		level = 1;
		Ghost.restartDificulty();
		labyrinth = new Labyrinth();
		for (Ghost ghost : ghosts) {
			ghost.born();
		}
		pacman = new Pacman(this);
		timer.restart();
		txtFld = null;
		repaint();
	}


	public void onKeyEnter() {
		if (!paused) {
			for (Ghost ghost : ghosts) {
				ghost.getTimer().stop();
			}
			pacman.getTimer().stop();
			timer.stop();
		} else {
			for (Ghost ghost : ghosts) {
				ghost.getTimer().restart();
			}
			pacman.getTimer().restart();
			timer.restart();
		}
		paused = !paused;
		repaint();
		
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
			if (cont == Ghost.BLUE_TIME) {
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
	private class InputScoreListener implements KeyListener {

		private Board board;

		public InputScoreListener(Board board) {
			this.board = board;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				board.saveScore();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	

	public void saveScore() {
		score.saveScore(txtFld.getText(), pacman.getPoints());
		txtFld.setVisible(false);
		remove(txtFld);
		repaint();
	}
}
