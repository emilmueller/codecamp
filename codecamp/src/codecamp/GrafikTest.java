package codecamp;

import processing.core.PApplet;

public class GrafikTest extends PApplet {
	int x =2;
	public void setup(){
		size(500,400);
		fill(255,0,0);
		stroke(0,255,0);
		
		rect(100,50,100,200);
		strokeWeight(5);
		ellipse(280,160,50,50);
		
		
	}
	
	public void draw(){ //wird 60 Mal pro Sekunde aufgerufen
		background(155,0,230);
		
		rect(x,100,30,30);
		x++;
	}
	
	
}
