package codecamp;

import processing.core.PApplet;

public class MausInteraktion extends PApplet {
	public void setup() {
		size(300, 300);
	}

	public void draw() {

	}

	public void mouseClicked() {
		System.out.println("Hallo");
		System.out.println("x: " + mouseX + " - y: " + mouseY);
		if (mouseButton == RIGHT) {
			System.out.println("Du hast rechts geklickt");
		} else {
			System.out.println("Du hast links geklickt");
		}

	}
}
