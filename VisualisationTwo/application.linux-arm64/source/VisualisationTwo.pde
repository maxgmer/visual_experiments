float angle = 0;
float dims = 100;

float rRed = 0;
float rGreen = 0;
float rBlue = 255;
float tRed = 0;
float tGreen = 255;
float tBlue = 0;

float angleModifier = 4f;
float sizeDivider = -1500;
float dimsLimitFactor = 1.5f;

void setup() {
  fullScreen();
  background(0);
  strokeWeight(1);
  rectMode(CENTER);
  nextColor();
}

void draw() {
  drawMessage();
  drawVisualisation();
  nextState();
}

void nextState() {
  dims -= height / sizeDivider;
  angle += angleModifier;
  if (dims <= -height * dimsLimitFactor || dims >= height * dimsLimitFactor) {
    dims = 100;
    nextColor();
  }
}

void drawVisualisation() {
  float x = height / 2;
  float y = height / 2;
  pushMatrix();
  translate(width / 2, height / 2);
  rotate(angle);
  stroke(rRed, rGreen, rBlue, 20);
  rect(x, y, dims * 0.7, dims * 0.7);
  stroke(tRed, tGreen, tBlue, 20);
  triangle(x - dims, y, x + dims, y, x, y - dims);
  popMatrix();
}

void drawMessage() {
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
  noFill();
}

void nextColor() {
  rRed = random(0, 255);
  rGreen = random(0, 255);
  rBlue = random(0, 255);
  tRed = random(0, 255);
  tGreen = random(0, 255);
  tBlue = random(0, 255);
}

void mouseClicked() {
  background(0);
  if (mouseButton == LEFT) {
    angleModifier += 0.4f;
  } else {
    angleModifier -= 0.4f;
  }
}

void mouseWheel(MouseEvent event) {
  background(0);
  float scrolls = event.getCount();
  sizeDivider += scrolls * 100;
}
