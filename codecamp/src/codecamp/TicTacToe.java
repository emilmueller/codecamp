package codecamp;

import java.util.Vector;

import processing.core.PApplet;

public class TicTacToe extends PApplet {
	int s = 300;
	int[][] spielFeld = new int[3][3];
	int spieler = 1;
	boolean won = false;
	int wx, wy;
	int winner = -1;
	int aktPlayer;
	int nextMove;
	boolean comp = true;
	int drawX, drawY;
	int drawPlayer = 0;
	int wait = 1000;
	boolean done = false;

	public void setup() {
		size(3 * s, 3 * s);
		noFill();
		drawFeld();
		noLoop();

	}

	public void draw() {

		strokeWeight(20);
		noFill();
		stroke(0);

		for (int i = 0; i < spielFeld.length; i++) {
			for (int j = 0; j < spielFeld[i].length; j++) {
				if (spielFeld[j][i] != 0) {
					if (spielFeld[j][i] == 1) {
						ellipse(j * s + s / 2, i * s + s / 2, 3 * s / 4, 3 * s / 4);
					} else {
						line(j * s + s / 8, i * s + s / 8, (j + 1) * s - s / 8, (i + 1) * s - s / 8);
						line((j + 1) * s - s / 8, i * s + s / 8, j * s + s / 8, (i + 1) * s - s / 8);
					}
				}
			}
		}
		// done=true;

		if (winner > 0) {
			// System.out.println("WINNER: " + winner);
			strokeWeight(0);
			// stroke(255, 0, 0);
			fill(255, 150, 150, 220);
			if (wy == 0) { // vertikal
				for (int i = 0; i < 3; i++) {
					rect((wx - 1) * s, i * s, s, s);
				}

			}
			if (wx == 0) { // horizontal
				for (int i = 0; i < 3; i++) {
					rect(i * s, (wy - 1) * s, s, s);
				}

			}
			if (wx == wy) { // diagonal
				if (wx == 1) {
					for (int i = 0; i < 3; i++) {
						rect(i * s, i * s, s, s);
					}

				} else {
					for (int i = 0; i < 3; i++) {
						rect((2 - i) * s, i * s, s, s);
					}

				}
			}

			won = true;
			fill(255, 0, 0);
			textSize(48);
			text("Spieler " + winner + " gewinnt!", s / 4, s / 4);

		} else if (winner == 0) {
			// System.out.println("Unentschieden");
			fill(255, 0, 0);
			textSize(48);
			text("Unentschieden!", s / 4, s / 4);
			won = true;
		}

		fill(255);

		if (won) {

			noStroke();
			fill(200, 200, 200, 250);
			rect(s / 2, s / 2, 2 * s, s);
			fill(0);
			textSize(36);
			text("Wer beginnt?", 3 * s / 4, 3 * s / 4);
			stroke(0);
			strokeWeight(3);
			noFill();
			rect(3 * s / 4, s, 5 * s / 8, s / 4);
			rect(3 * s / 2, s, 5 * s / 8, s / 4);
			textSize(24);
			text("Mensch", 3 * s / 4 + 8, s + 29);
			text("Computer", 3 * s / 2 + 8, s + 29);

		}

	}

	public void drawFeld() {
		background(255);
		stroke(180);
		strokeWeight(3);
		for (int i = 1; i <= 2; i++) {
			line(0, i * s, 3 * s, i * s);
			line(i * s, 0, i * s, 3 * s);
		}
		stroke(0);

		redraw();

	}

	public void printField(int[][] f, boolean formatted) {
		for (int j = 0; j < f.length; j++) {
			for (int i = 0; i < f[j].length; i++) {
				if (formatted) {
					if (f[i][j] == 1) {
						System.out.print("O");
					} else if (f[i][j] == 2) {
						System.out.print("X");
					} else {
						System.out.print(" ");
					}
				} else {
					System.out.print(f[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

	public void setupGame(int sp, boolean playOn) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				spielFeld[i][j] = 0;
			}
		}
		spieler = sp;
		wx = 0;
		wy = 0;
		won = false;
		winner = -1;
		drawFeld();
		if (playOn) {
			mouseClicked();
		}
	}

	public void mouseClicked() {
		int x = mouseX / s;
		int y = mouseY / s;

		if (spielFeld[x][y] == 0 && !won)

		{
			if (spieler == 1) {
				drawField(x, y, spieler);
				spieler = 2;

			} else {
				if (!comp) {
					drawField(x, y, spieler);
					spieler = 1;
				}
			}

			winner = checkWinner(spielFeld);
			if (winner == -1 && comp) {

				aktPlayer = 2;
				play(aktPlayer);
				spieler = 1;

			}
			winner = checkWinner(spielFeld);

		}
		if (won) {

			if (mouseX >= 3 * s / 4 && mouseX <= 11 * s / 8 && mouseY >= s && mouseY <= 5 * s / 4) {
				setupGame(1, false);

			}
			if (mouseX >= 3 * s / 2 && mouseX <= 17 * s / 8 && mouseY >= s && mouseY <= 5 * s / 4) {

				setupGame(2, true);
			}
		}

	}

	public int checkWinner(int[][] feld) {
		boolean full = true;
		int p = 0;
		boolean w = true;
		for (int i = 0; i < 3; i++) { // spalte
			w = true;
			p = feld[i][0];
			if (p != 0) {
				for (int j = 1; j < 3; j++) {
					full = full && (feld[i][j] != 0);
					w = w && (p == feld[i][j]);
				}
				if (w) {
					wx = i + 1;
					wy = 0;
					return feld[i][0];
				}
			} else {
				full = false;
			}
		}
		for (int i = 0; i < 3; i++) { // zeile
			w = true;
			p = feld[0][i];
			if (p != 0) {
				for (int j = 1; j < 3; j++) {
					w = w && (p == feld[j][i]);
				}
				if (w) {
					wx = 0;
					wy = i + 1;
					return feld[0][i];
				}
			}
		}
		w = true;
		p = feld[0][0];
		if (p != 0) { // diagonale 1
			for (int j = 1; j < 3; j++) {
				w = w && (p == feld[j][j]);
			}
			if (w) {
				wx = 1;
				wy = 1;
				return feld[0][0];
			}
		}
		w = true;
		p = feld[2][0];
		if (p != 0) { // diagonale 2
			for (int j = 0; j < 3; j++) {
				w = w && (p == feld[2 - j][j]);
			}
			if (w) {
				wx = 2;
				wy = 2;
				return feld[2][0];
			}
		}
		// wenn das programm bis hier kommt, gab es keinen gewinner
		if (full) {
			return 0; // unentschieden
		} else {
			return -1; // noch nicht voll
		}

	}

	public void drawField(int x, int y, int player) {
		spielFeld[x][y] = player;
		redraw();
		System.out.println("DRAW: " + x + "/" + y + " -> " + player);

	}

	public void play(int player) {

		new WaitThread(wait).run();
		int index = miniMax(spielFeld, player, 0);
		drawField(nextMove / 3, nextMove % 3, player);

	}

	public int miniMax(int[][] tmpFeld, int player, int weight) {

		int tWinner = checkWinner(tmpFeld);

		// rückgabe der wertungen einer gewinn/verlust-situation
		if (tWinner >= 0) {

			if (tWinner == aktPlayer) {
				return 10 - weight;
			} else if (tWinner == 3 - aktPlayer) {
				return weight - 10;
			} else {
				return 0;
			}

		} else {
			int[] freeIndizes = getFreeIndizes(tmpFeld);
			if (freeIndizes.length == 9) {// computer macht ersten zug zufällig
				nextMove = (int) (Math.random() * 9);
				return 0;
			} else {
				int[] scores = new int[freeIndizes.length];
				// probiere alle möglichen züge rekursiv aus
				for (int i = 0; i < freeIndizes.length; i++) {
					tmpFeld[freeIndizes[i] / 3][freeIndizes[i] % 3] = player;
					scores[i] = miniMax(tmpFeld, 3 - player, weight + 1);

					tmpFeld[freeIndizes[i] / 3][freeIndizes[i] % 3] = 0;

				}

				// get max
				int m = scores[0];
				int index = 0;
				if (weight == 0) {
					// System.out.print("W: " + weight + " ");
					int i = 0;
//					for (int p = 0; p < scores.length; p++) {
//						System.out.print(freeIndizes[p]+" -> "+scores[p]+ " | ");
//					}
					System.out.println();
					for (int r = 0; r < 3; r++) {
						for (int c = 0; c < 3; c++) {
							if (i < scores.length && freeIndizes[i] / 3 == r && freeIndizes[i] % 3 == c) {
								System.out.print(scores[i] + " ");
								i++;
							}else{
								System.out.print("X ");
							}
						}
						System.out.println();
					}
				}
				System.out.println();
				// }

				if (player == aktPlayer) {// falls aktplayer am zug ist, suche
											// maximum
					for (int i = 0; i < scores.length; i++) {
						if (scores[i] > m) {
							m = scores[i];
							index = i;
						}
						if (scores[i] == m) { // verhindert, dass der computer
												// immer den gleichen zug in der
												// gleichen situation macht
							if (Math.random() > 0.8) {

								m = scores[i];
								index = i;
							}
						}
					}
				} else {// falls gegner am zug ist, suche minimum
					for (int i = 0; i < scores.length; i++) {
						if (scores[i] < m) {
							m = scores[i];
							index = i;
						}
						if (scores[i] == m) {
							if (Math.random() > 0.8) {
								m = scores[i];
								index = i;
							}
						}
					}
				}
				nextMove = freeIndizes[index];
				return m;
			}
		}

	}

	public int[] getFreeIndizes(int[][] f) {
		Vector<Integer> res = new Vector<Integer>();

		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[i].length; j++) {
				if (f[i][j] == 0) {
					res.add(new Integer(i * 3 + j));
				}
			}
		}
		int[] ret = new int[res.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = res.get(i).intValue();
		}
		return ret;
	}

	public boolean isFull(boolean[] a) {
		boolean full = true;
		for (boolean b : a) {
			full = full && !b;

		}
		return full;

	}
}
