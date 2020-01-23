import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class VisualisationTwo extends PApplet {

float angle = 0;
float dims = 100;

float rRed = 0;
float rGreen = 0;
float rBlue = 255;
float tRed = 0;
float tGreen = 255;
float tBlue = 0;
// 0.8 angle, 2500 size divider, 1.5 dims limit.
float angleModifier = 4f;
float sizeDivider = -1500;
float dimsLimitFactor = 1.5f;

public void setup() {
  
  background(0);
  strokeWeight(1);
  rectMode(CENTER);
  nextColor();
}

public void draw() {
  drawMessage();
  drawVisualisation();
  nextState();
}

public void nextState() {
  dims -= height / sizeDivider;
  angle += angleModifier;
  if (dims <= -height * dimsLimitFactor || dims >= height * dimsLimitFactor) {
    dims = 100;
    nextColor();
  }
}

public void drawVisualisation() {
  float x = height / 2;
  float y = height / 2;
  pushMatrix();
  translate(width / 2, height / 2);
  rotate(angle);
  stroke(rRed, rGreen, rBlue, 20);
  rect(x, y, dims * 0.7f, dims * 0.7f);
  stroke(tRed, tGreen, tBlue, 20);
  triangle(x - dims, y, x + dims, y, x, y - dims);
  popMatrix();
}

public void drawMessage() {
  fill(160, 160, 160);
  textSize(18);
  text("Angle modifier: " + angleModifier, 20, 40); 
  textSize(14);
  text("Left mouse click: angleModifier += 0.4", 20, 70);
  text("Right mouse click: angleModifier -= 0.4", 20, 90);
  textSize(18);
  text("Size divider: " + sizeDivider, 20, 130);
  textSize(14);
  text("Mouse scroll towards you: sizeDivider += 100", 20, 160);
  text("Mouse scroll away from you: sizeDivider -= 100", 20, 180);
  textSize(18);
  text("Round duration: " + dimsLimitFactor, 20, 220); 
  textSize(14);
  text("Up arrow - make round longer", 20, 250);
  text("Down arrow - make round shorter", 20, 270);
  noFill();
}

public void nextColor() {
  rRed = random(0, 255);
  rGreen = random(0, 255);
  rBlue = random(0, 255);
  tRed = random(0, 255);
  tGreen = random(0, 255);
  tBlue = random(0, 255);
}

public void mouseClicked() {
  background(0);
  if (mouseButton == LEFT) {
    angleModifier += 0.4f;
  } else {
    angleModifier -= 0.4f;
  }
}

public void mouseWheel(MouseEvent event) {
  background(0);
  float scrolls = event.getCount();
  sizeDivider += scrolls * 100;
}

public void keyPressed() {
  background(0);
  if (keyCode == UP) {
    dimsLimitFactor += 0.05f;
  } else if (keyCode == DOWN) {
    dimsLimitFactor -= 0.05f;
  } 
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "VisualisationTwo" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
