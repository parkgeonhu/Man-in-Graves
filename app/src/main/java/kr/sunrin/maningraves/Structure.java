package kr.sunrin.maningraves;

import android.graphics.Rect;

public class Structure
{
    private final int X = 1000;
    private int centerX = X;
    private int centerY;
    final int Y1 = 265 - 75;
    final int Y2 = 265 + 5;
    private final int speedFinalX = -8;
    private int speedX;
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    public Rect rect = new Rect(0, 0, 0, 0);

    public Structure() {}
    public Structure(int num, int select)
    {
        speedX = speedFinalX;
        centerX += centerX + WIDTH * num;
        if (select == 0) centerY = Y1;
        else centerY = Y2;
    }

    public void update() {
        centerX += speedX;
        rect.set(centerX - WIDTH/2, centerY - HEIGHT/2, centerX + WIDTH/2, centerY + HEIGHT/2);
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }

    public int getWIDTH() {return WIDTH; }

    public void setCenterY(int centerY) {this.centerY = centerY;}

    public int getX() {return X;}

    public int getSpeedX() { return speedX; }

    public void setSpeedX(int speedX) { this.speedX = speedFinalX - speedX; if(this.speedX < -10) this.speedX = -10;}

}
