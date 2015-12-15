package codecamp;

import processing.core.PApplet;

public class FallingBall extends PApplet {
	float sx=400, sy=200, vx=13, vy=15, gy=3;
	float reibH=0.999f;
	float reibV=0.99f;
	public void setup() {

	  size(800, 800);
	  smooth();
	  background(255);
	  fill(0);
	}

	public void draw() {
	  background(255);
	  vy=vy+gy;
	  sy=sy+vy;
	  vx=vx*reibH;
	  sx=sx+vx;
	  if (sy>775) {
	    vy=-vy*reibV;
	    sy=775;
	  }
	  if (sy<25) {
	    vy=-vy;
	   
	    sy=25;
	  }
	  if(sx<25){
	    vx=-vx;
	    sx=25;
	  }
	  if(sx>775){
	    vx=-vx;
	    sx=775;
	  }
	  if(mousePressed){
	    background(255);
	    sx=mouseX;
	    sy=mouseY;
	    vx=mouseX-pmouseX;
	    vy=mouseY-pmouseY;
	   
	  }
	  ellipse(sx, sy, 50, 50);
	}

}
