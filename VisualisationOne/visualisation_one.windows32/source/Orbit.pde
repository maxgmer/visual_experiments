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
  
  void update() {
    if (parent != null) {
      angle += speed;
      float rSum = r + parent.r;
      x = parent.x + rSum * cos(angle);
      y = parent.y + rSum * sin(angle);
    }
  }
  
  void show() {
    if (parent != null) {
      stroke(red, green, blue, START_ALPHA + (orbitNumber * ALPHA_MODIFIER));
      strokeWeight(2);
      noFill();
      ellipse(x, y, r * 2, r * 2);
    }
  }
  
  Orbit addChild() {
    float newR = r * REDUCTION_MULTIPLIER;
    float newX = x + r + newR;
    float newY = y;
    child = new Orbit(newX, newY, newR, -FIGURE_CHANGER * speed, this);
    return child;
  }
  
}
