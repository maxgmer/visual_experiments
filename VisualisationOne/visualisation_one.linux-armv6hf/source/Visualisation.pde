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

void setupConfiguration(int configID) {
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

void setup() {
  fullScreen();
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

void draw() {
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
