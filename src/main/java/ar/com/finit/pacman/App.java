package ar.com.finit.pacman;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ar.com.finit.pacman.ui.Board;

/**
 * @author Leo
 */
public class App extends JFrame {

	private static final long serialVersionUID = 1L;

	public App() {
		add(new Board());
		setTitle("Pacman Leo Fini");
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new App();
				frame.setVisible(true);
			}

		});
	}

}
