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

public class Visualisation extends PApplet {

float ROTATION_SPEED = 0;
float FIGURE_CHANGER = 0;
int START_ALPHA = 0;
float REDUCTION_MULTIPLIER = 0;
int TUNNEL_NUMBER = 0;
int ALPHA_MODIFIER = 0;

ArrayList<PVector> path;
Orbit sun;
Orbit drawOrbit;
boolean resetupDone = false;
int currentVisualisation = 0;

public void setupConfiguration(int configID) {
  switch(configID) {
    case 0:
      ROTATION_SPEED = 0.001f;
      FIGURE_CHANGER = 2;
      START_ALPHA = 15;
      REDUCTION_MULTIPLIER = 0.7f;
      TUNNEL_NUMBER = 5;
      ALPHA_MODIFIER = 2;
      break;   
    case 1:
      ROTATION_SPEED = 0.001f;
      FIGURE_CHANGER = 3;
      START_ALPHA = 10;
      REDUCTION_MULTIPLIER = 0.7f;
      TUNNEL_NUMBER = 7;
      ALPHA_MODIFIER = 3;
      break;
    case 2:
      ROTATION_SPEED = 0.1f;
      FIGURE_CHANGER = 5f;
      START_ALPHA = 15;
      REDUCTION_MULTIPLIER = -0.7f;
      TUNNEL_NUMBER = 3;
      ALPHA_MODIFIER = 2;
      break;
    case 3:
      ROTATION_SPEED = 0.0001f;
      FIGURE_CHANGER = 4;
      START_ALPHA = 5;
      REDUCTION_MULTIPLIER = 0.7f;
      TUNNEL_NUMBER = 8;
      ALPHA_MODIFIER = 5;
      break;
    case 4:
      ROTATION_SPEED = 0.001f;
      FIGURE_CHANGER = 10;
      START_ALPHA = 15;
      REDUCTION_MULTIPLIER = 0.5f;
      TUNNEL_NUMBER = 3;
      ALPHA_MODIFIER = 2;
      break;
  }
  if (configID > 4) {
    currentVisualisation = 0;
    setup();
  }
}

public void setup() {
  
  setupConfiguration(currentVisualisation++);
  path = new ArrayList<PVector>();
  sun = new Orbit(width / 2, height / 2, 100, ROTATION_SPEED, null);
  Orbit next = sun;
  for (int i = 0; i < TUNNEL_NUMBER + 1; i++) {
    next = next.addChild();  
  }
  drawOrbit = next;
  background(0);
  stroke(255, 15);
  strokeWeight(1);
  noFill();
}

public void draw() {
  Orbit next = sun;
  while (next != null) {
    next.update();
    next.show();
    next = next.child;
  }
  
  if (mousePressed && !resetupDone) { 
    background(51);
    setup();
    resetupDone = true;
  }
  if (!mousePressed) resetupDone = false;
  textSize(20);
  text(String.valueOf(currentVisualisation), 30, 60);
  textSize(20);
  text("Made by Maxim Hapeyenka", width - 300, height - 50);
  if (!helpHasBeenShown) {
    helpHasBeenShown = true;
    text("Wait! Maybe something interesting will appear! :)", 30, 90);
    text("Also, try to press mouse to change visualisation config!", 30, 120);
  }
}
boolean helpHasBeenShown = false;
class Orbit {
  float x;
  float y;
  float r;
  float orbitNumber = 1;
  
  float red, green, blue;
  
  float speed;
  float angle;
  
  Orbit parent;
  Orbit child;
  
  Orbit (float x, float y, float r, float speed, Orbit parent) {
    if (parent != null) this.orbitNumber = parent.orbitNumber + 1;
    this.x = x;
    this.y = y;
    this.r = r;
    red = random(0, 255);
    green = random(0, 255);
    blue = random(0, 255);
    this.parent = parent;
    angle = 0;
    this.speed = speed;
  }
  
  public void update() {
    if (parent != null) {
      angle += speed;
      float rSum = r + parent.r;
      x = parent.x + rSum * cos(angle);
      y = parent.y + rSum * sin(angle);
    }
  }
  
  public void show() {
    if (parent != null) {
      stroke(red, green, blue, START_ALPHA + (orbitNumber * ALPHA_MODIFIER));
      strokeWeight(2);
      noFill();
      ellipse(x, y, r * 2, r * 2);
    }
  }
  
  public Orbit addChild() {
    float newR = r * REDUCTION_MULTIPLIER;
    float newX = x + r + newR;
    float newY = y;
    child = new Orbit(newX, newY, newR, -FIGURE_CHANGER * speed, this);
    return child;
  }
  
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Visualisation" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
