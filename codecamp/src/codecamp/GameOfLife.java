package codecamp;

import processing.core.PApplet;

public class GameOfLife extends PApplet {
	int n = 500;
	int s = 2;
	boolean[][] feld;
	boolean run = true;

	public void setup() {
		size(n * s, n * s);
		feld = new boolean[n][n];
		fillRandom();

	}

	public void drawField() {
		noStroke();
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[i].length; j++) {
				if (feld[i][j]) {
					fill(0, 255, 0);
				} else {
					fill(0, 0, 255);
				}
				rect(i * s, j * s, s, s);
			}

		}
	}

	public void draw() {
		if (run) {

			feld = nextGeneration();
		}
		drawField();

	}

	public boolean[][] nextGeneration() {
		boolean[][] res = new boolean[n][n];
		// testen mit nachbarn.
		for (int row = 0; row < feld.length; row++) {
			for (int col = 0; col < feld[row].length; col++) {
				int neighbours = 0;
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (!(i == 0 && j == 0)) {
							if (feld[(row + i + n) % n][(col + j + n) % n]) {
								neighbours++;
							}
						}
					}
				}
				if (feld[row][col]) {
					if (neighbours == 2 || neighbours == 3) {
						res[row][col] = true;
					} else {
						res[row][col] = false;
					}
				} else {
					if (neighbours == 3) {
						res[row][col] = true;
					} else {
						res[row][col] = false;
					}
				}
			}

		}
		return res;
	}

	public void fillRandom() {
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[i].length; j++) {
				feld[i][j] = (Math.random() > 0.85);
			}
		}
		drawField();
	}

	public void mouseClicked() {
		if (mouseButton == RIGHT) {
			run = !run;
		}
		if (mouseButton == LEFT) {
			int x = mouseX / s;
			int y = mouseY / s;
			feld[x][y] = !feld[x][y];

		}
	}

	public void mouseDragged() {
		if (mouseButton == LEFT) {
			int x = mouseX / s;
			int y = mouseY / s;
			feld[x][y] = !feld[x][y];

		}
	}

}
