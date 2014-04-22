package ar.com.finit.pacman.ui;

import ar.com.finit.pacman.ui.dot.Dot;
import ar.com.finit.pacman.ui.dot.impl.SmallDot;
import ar.com.finit.pacman.ui.wall.HWall;
import ar.com.finit.pacman.ui.wall.LeftBottomWall;
import ar.com.finit.pacman.ui.wall.LeftTopWall;
import ar.com.finit.pacman.ui.wall.RightBottomWall;
import ar.com.finit.pacman.ui.wall.RightTopWall;
import ar.com.finit.pacman.ui.wall.VWall;

/**
 * @author leo
 */
public class Labyrinth {
	
	private Pixel[][] pixels; 

	public Labyrinth() {
		init();
	}

	private void init() {
		pixels = new Pixel[Board.M_HEIGHT][Board.M_WIDTH];
		for (int i = 0; i < Board.M_HEIGHT; i++) {
			for (int j = 0; j < Board.M_WIDTH; j++) {
				if (j == 0) {
					pixels[i][j] = new VWall();
				} else if (i == 0) {
					pixels[i][j] = new HWall();
				} else if (i == Board.M_HEIGHT -1) {
					pixels[i][j] = new HWall();
				} else if (j == Board.M_WIDTH -1) {
					pixels[i][j] = new VWall();
				} else {
					pixels[i][j] = new SmallDot();
				}
			}
		}
		pixels[3][2]= new VWall();
		pixels[3][5]= new VWall();
		pixels[3][7]= new VWall();
		pixels[3][11]= new VWall();
		pixels[3][17]= new VWall();
		pixels[3][21]= new VWall();
		pixels[3][23]= new VWall();
		pixels[3][26]= new VWall();
		pixels[4][14]= new HWall();
		pixels[10][14]= new HWall();
		pixels[22][14]= new HWall();
		pixels[28][14]= new HWall();
		pixels[20][15]= new VWall();
		pixels[20][13]= new VWall();
		pixels[21][15]= new VWall();
		pixels[21][13]= new VWall();
		pixels[26][15]= new VWall();
		pixels[26][13]= new VWall();
		pixels[27][15]= new VWall();
		pixels[27][13]= new VWall();
		pixels[13][28] = new HWall();
		pixels[15][28] = new HWall();
		pixels[25][7] = new VWall();
		pixels[25][8] = new VWall();
		pixels[25][20] = new VWall();
		pixels[25][21] = new VWall();

		for (int i = 22; i < 25; i++) {
			pixels[i][4]= new VWall();
			pixels[i][5]= new VWall();
			pixels[i][23]= new VWall();
			pixels[i][24]= new VWall();
		}
		for (int i = 8; i < 10; i++) {
			pixels[i][13]= new VWall();
			pixels[i][15]= new VWall();
		}
		for (int i = 10; i < 13; i++) {
			pixels[i][23]= new VWall();
		}
		for (int i = 16; i < 19; i++) {
			pixels[i][7]= new VWall();
			pixels[i][8]= new VWall();
			pixels[i][20]= new VWall();
			pixels[i][21]= new VWall();
			pixels[i][23]= new VWall();
		}
		for (int i = 13; i < 16; i++) {
			pixels[i][10]= new VWall();
			pixels[i][18]= new VWall();
		}
		for (int i = 26; i < 28; i++) {
			pixels[i][7]= new VWall();
			pixels[i][8]= new VWall();
			pixels[i][20]= new VWall();
			pixels[i][21]= new VWall();
		}
		for (int i = 7; i < 13; i++) {
			pixels[i][7]= new VWall();
			pixels[i][8]= new VWall();
			pixels[i][20]= new VWall();
			pixels[i][21]= new VWall();
		}
		for (int i = 1; i < 4; i++) {
			pixels[i][13]= new VWall();
			pixels[i][15]= new VWall();
		}
		
		for (int i = 10; i < 13; i++) {
			pixels[i][5]= new VWall();
		}
		for (int i = 16; i < 19; i++) {
			pixels[i][5]= new VWall();
		}
		for (int j = 0; j < 5; j++) {
			pixels[9][j] = new HWall();
			pixels[13][j] = new HWall();
			pixels[15][j] = new HWall();
			pixels[19][j] = new HWall();
		}
		for (int j = 24; j < Board.M_WIDTH - 1; j++) {
			pixels[9][j] = new HWall();
			pixels[13][j] = new HWall();
			pixels[15][j] = new HWall();
			pixels[19][j] = new HWall();
		}
		for (int j = 3; j < 5; j++) {
			pixels[2][j] = new HWall();
			pixels[4][j] = new HWall();
		}
		for (int j = 3; j < 5; j++) {
			pixels[6][j] = new HWall();
			pixels[7][j] = new HWall();
		}
		for (int j = 8; j < 11; j++) {
			pixels[2][j] = new HWall();
			pixels[4][j] = new HWall();
		}
		for (int j = 9; j < 11; j++) {
			pixels[9][j] = new HWall();
			pixels[10][j] = new HWall();
		}
		for (int j = 18; j < 20; j++) {
			pixels[9][j] = new HWall();
			pixels[10][j] = new HWall();
		}
		for (int j = 11; j < 18; j++) {
			pixels[12][j] = new HWall();
			pixels[16][j] = new HWall();
		}
		for (int j = 11; j < 18; j++) {
			pixels[18][j] = new HWall();
			pixels[19][j] = new HWall();
		}
		for (int j = 11; j < 18; j++) {
			pixels[24][j] = new HWall();
			pixels[25][j] = new HWall();
		}
		for (int j = 3; j < 5; j++) {
			pixels[21][j] = new HWall();
		}
		pixels[22][3] = new HWall();
		for (int j = 24; j < 26; j++) {
			pixels[21][j] = new HWall();
		}
		pixels[22][25] = new HWall();
		for (int j = 8; j < 11; j++) {
			pixels[21][j] = new HWall();
			pixels[22][j] = new HWall();
		}
		for (int j = 18; j < 21; j++) {
			pixels[21][j] = new HWall();
			pixels[22][j] = new HWall();
		}
		for (int j = 18; j < 21; j++) {
			pixels[2][j] = new HWall();
			pixels[4][j] = new HWall();
		}
		for (int j = 24; j < 26; j++) {
			pixels[2][j] = new HWall();
			pixels[4][j] = new HWall();
		}
		for (int j = 24; j < 26; j++) {
			pixels[6][j] = new HWall();
			pixels[7][j] = new HWall();
		}
		for (int j = 11; j < 18; j++) {
			pixels[6][j] = new HWall();
			pixels[7][j] = new HWall();
		}
		for (int j = 3; j < 11; j++) {
			pixels[27][j] = new HWall();
			pixels[28][j] = new HWall();
		}
		for (int j = 18; j < 26; j++) {
			pixels[27][j] = new HWall();
			pixels[28][j] = new HWall();
		}
		for (int i = 24; i < 26; i++) {
			pixels[i][1] = new HWall();
		}
		for (int i = 24; i < 26; i++) {
			pixels[i][27] = new HWall();
		}
		pixels[0][0] = new LeftTopWall();
		pixels[0][13] = new RightTopWall();
		pixels[0][15] = new LeftTopWall();
		pixels[7][13] = new RightTopWall();
		pixels[7][15] = new LeftTopWall();
		pixels[19][13] = new RightTopWall();
		pixels[19][15] = new LeftTopWall();
		pixels[25][13] = new RightTopWall();
		pixels[25][15] = new LeftTopWall();
		pixels[0][Board.M_WIDTH -1] = new RightTopWall();
		pixels[Board.M_HEIGHT -1][Board.M_WIDTH -1] = new RightBottomWall();
		pixels[Board.M_HEIGHT -1][0] = new LeftBottomWall();
		pixels[2][2] = new LeftTopWall(); 
		pixels[2][5] = new RightTopWall(); 
		pixels[4][2] = new LeftBottomWall(); 
		pixels[4][5] = new RightBottomWall(); 
		pixels[2][7] = new LeftTopWall(); 
		pixels[2][11] = new RightTopWall(); 
		pixels[4][7] = new LeftBottomWall(); 
		pixels[4][11] = new RightBottomWall(); 
		pixels[2][17] = new LeftTopWall(); 
		pixels[2][21] = new RightTopWall(); 
		pixels[4][17] = new LeftBottomWall(); 
		pixels[4][21] = new RightBottomWall(); 
		pixels[2][23] = new LeftTopWall(); 
		pixels[2][26] = new RightTopWall(); 
		pixels[4][23] = new LeftBottomWall(); 
		pixels[4][26] = new RightBottomWall(); 
		pixels[19][5] = new RightTopWall();
		pixels[19][28] = new RightTopWall();
		pixels[9][0] = new LeftBottomWall();
		pixels[19][0] = new LeftTopWall();
		pixels[24][0] = new LeftBottomWall();
		pixels[25][0] = new LeftTopWall();
		pixels[24][2] = new RightTopWall();
		pixels[25][2] = new RightBottomWall();
		pixels[9][5] = new RightTopWall();
		pixels[9][11] = new RightTopWall();
		pixels[9][17] = new LeftTopWall();
		pixels[9][23] = new LeftTopWall();
		pixels[9][28] = new RightBottomWall();
		pixels[10][11] = new RightBottomWall();
		pixels[10][17] = new LeftBottomWall();
		pixels[13][5] = new RightBottomWall();
		pixels[15][5] = new RightTopWall();
		pixels[18][10] = new LeftTopWall();
		pixels[18][18] = new RightTopWall();
		pixels[19][10] = new LeftBottomWall();
		pixels[19][18] = new RightBottomWall();
		pixels[19][5] = new RightBottomWall();
		pixels[6][2] = new LeftTopWall();
		pixels[6][20] = new LeftTopWall();
		pixels[6][21] = new RightTopWall();
		pixels[6][23] = new LeftTopWall();
		pixels[6][26] = new RightTopWall();
		pixels[7][2] = new LeftBottomWall();
		pixels[6][5] = new RightTopWall();
		pixels[7][5] = new RightBottomWall();
		pixels[7][10] = new LeftBottomWall();
		pixels[7][18] = new RightBottomWall();
		pixels[7][23] = new LeftBottomWall();
		pixels[7][26] = new RightBottomWall();
		pixels[9][8] = new LeftBottomWall();
		pixels[10][8] = new LeftTopWall();
		pixels[9][20] = new RightBottomWall();
		pixels[10][20] = new RightTopWall();
		pixels[6][7] = new LeftTopWall();
		pixels[6][10] = new LeftTopWall();
		pixels[6][18] = new RightTopWall();
		pixels[6][8] = new RightTopWall();
		pixels[4][13] = new LeftBottomWall();
		pixels[4][15] = new RightBottomWall();
		pixels[10][13] = new LeftBottomWall();
		pixels[10][15] = new RightBottomWall();
		pixels[12][10] = new LeftTopWall();
		pixels[12][18] = new RightTopWall();
		pixels[16][10] = new LeftBottomWall();
		pixels[16][18] = new RightBottomWall();
		pixels[13][7] = new LeftBottomWall();
		pixels[13][8] = new RightBottomWall();
		pixels[13][20] = new LeftBottomWall();
		pixels[13][21] = new RightBottomWall();
		pixels[13][23] = new LeftBottomWall();
		pixels[15][7] = new LeftTopWall();
		pixels[15][8] = new RightTopWall();
		pixels[15][20] = new LeftTopWall();
		pixels[15][21] = new RightTopWall();
		pixels[15][23] = new LeftTopWall();
		pixels[19][7] = new LeftBottomWall();
		pixels[19][8] = new RightBottomWall();
		pixels[19][20] = new LeftBottomWall();
		pixels[19][21] = new RightBottomWall();
		pixels[19][23] = new LeftBottomWall();
		pixels[21][2] = new LeftTopWall();
		pixels[21][5] = new RightTopWall();
		pixels[21][7] = new LeftTopWall();
		pixels[21][11] = new RightTopWall();
		pixels[21][17] = new LeftTopWall();
		pixels[21][21] = new RightTopWall();
		pixels[21][23] = new LeftTopWall();
		pixels[21][26] = new RightTopWall();
		pixels[22][2] = new LeftBottomWall();
		pixels[22][7] = new LeftBottomWall();
		pixels[22][11] = new RightBottomWall();
		pixels[22][17] = new LeftBottomWall();
		pixels[22][21] = new RightBottomWall();
		pixels[22][26] = new RightBottomWall();
		pixels[22][13] = new LeftBottomWall();
		pixels[22][15] = new RightBottomWall();
		pixels[22][4] = new RightTopWall();
		pixels[22][24] = new LeftTopWall();
		pixels[24][10] = new LeftTopWall();
		pixels[24][18] = new RightTopWall();
		pixels[24][26] = new LeftTopWall();
		pixels[24][7] = new LeftTopWall();
		pixels[24][8] = new RightTopWall();
		pixels[24][20] = new LeftTopWall();
		pixels[24][21] = new RightTopWall();
		pixels[24][28] = new RightBottomWall();
		pixels[25][4] = new LeftBottomWall();
		pixels[25][5] = new RightBottomWall();
		pixels[25][10] = new LeftBottomWall();
		pixels[25][18] = new RightBottomWall();
		pixels[25][23] = new LeftBottomWall();
		pixels[25][24] = new RightBottomWall();
		pixels[25][26] = new LeftBottomWall();
		pixels[25][28] = new RightTopWall();
		pixels[27][2] = new LeftTopWall();
		pixels[28][2] = new LeftBottomWall();
		pixels[27][11] = new RightTopWall();
		pixels[28][11] = new RightBottomWall();
		pixels[27][17] = new LeftTopWall();
		pixels[28][17] = new LeftBottomWall();
		pixels[27][26] = new RightTopWall();
		pixels[27][7] = new RightBottomWall();
		pixels[27][8] = new LeftBottomWall();
		pixels[27][20] = new RightBottomWall();
		pixels[27][21] = new LeftBottomWall();
		pixels[28][26] = new RightBottomWall();
		pixels[28][13] = new LeftBottomWall();
		pixels[28][15] = new RightBottomWall();
		for (int i = 10; i < 13; i++) {
			pixels[i][0] = null;
		}
		for (int i = 3; i < 5; i++) {
			pixels[3][i] = null;
		}
		for (int i = 8; i < 11; i++) {
			pixels[3][i] = null;
		}
		for (int i = 18; i < 21; i++) {
			pixels[3][i] = null;
		}
		for (int i = 24; i < 26; i++) {
			pixels[3][i] = null;
		}
		for (int i = 0; i < 4; i++) {
			pixels[i][14] = null;
		}
		for (int i = 7; i < 10; i++) {
			pixels[i][14] = null;
		}
		for (int i = 19; i < 22; i++) {
			pixels[i][14] = null;
		}
		for (int i = 25; i < 28; i++) {
			pixels[i][14] = null;
		}
		for (int j = 0; j < 6; j++) {
			pixels[14][j] = null;
		}
		for (int j = 23; j < 29; j++) {
			pixels[14][j] = null;
		}
		for (int i = 10; i < 13; i++) {
			for (int j = 0; j < 5; j++) {
				pixels[i][j] = null;
			}
			for (int j = 24; j < 29; j++) {
				pixels[i][j] = null;
			}
		}
		for (int i = 13; i < 16; i++) {
			for (int j = 11; j < 18; j++) {
				pixels[i][j] = null;
			}
		}
		for (int i = 16; i < 19; i++) {
			for (int j = 0; j < 5; j++) {
				pixels[i][j] = null;
			}
			for (int j = 24; j < 29; j++) {
				pixels[i][j] = null;
			}
		}
	}

	public Pixel[][] getPixels() {
		return pixels;
	}

	public void setPixels(Pixel[][] pixels) {
		this.pixels = pixels;
	}

	public boolean isIntersection(int x, int y) {
		int axisX = 0; 
		int axisY = 0; 
		
		if (x == 0  || x == Board.M_WIDTH - 1) return false;
		
		if (pixels[y + 1][x] == null || pixels[y + 1][x] instanceof Dot) { 
			axisY++; 
		}
		if (pixels[y - 1][x] == null || pixels[y - 1][x] instanceof Dot) { 
			axisY++; 
		}
		if (pixels[y][x + 1] == null || pixels[y][x + 1] instanceof Dot) { 
			axisX++; 
		}
		if (pixels[y][x - 1] == null || pixels[y][x - 1] instanceof Dot) { 
			axisX++; 
		}
		return axisX > 0 && axisY > 0;
	}
	
}
