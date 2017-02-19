package kr.sunrin.maningraves;

import android.graphics.Rect;

public class Player {
    final int JUMPSPEED1 = -8;
    final int JUMPSPEED2 = 8;
    final int Y1 = 265 - 65;
    final int Y2 = 265 + 20;
    final int X = 175;
    private int JUMPSPEED;
    private int centerX = X;
    private int Y, select;
    private int centerY;
    public boolean jumped = false;
    private boolean downing = false;
    private boolean down = true;
    private static boolean shield = false;
    private boolean moveItem = false;
    private static boolean superarmour = false;
    private static long time = 0;

    private int speedY = 0;
    public Rect rect = new Rect(0, 0, 0, 0);

    public Player(int num) {
        if (num == 1) {
            Y = Y1;
            centerY = Y;
            JUMPSPEED = JUMPSPEED1;
        } else {
            Y = Y2;
            centerY = Y;
            JUMPSPEED = JUMPSPEED2;
        }
        select = num;
        shield = false;
    }

    public void update() {

        if (jumped || downing) {
            if (((centerY >= Y && select == 1) || (centerY <= Y && select == 2)) && downing) {
                downing = false;
                speedY = 0;
                jumped = false;
                centerY = Y;
                down = true;
            }
            if (((centerY < Y - 150 && select == 1) || (centerY > Y + 150 && select == 2)) || downing) {
                downing = true;
                speedY = -(JUMPSPEED);
            }

        }

        if (moveItem) {
            if (X > centerX) {
                centerX += 1;
                rect.set(centerX - 25, centerY - 35, centerX + 25, centerY + 35);
            } else {
                moveItem = false;
            }
        }

        if (isSuperarmour() && time + 2000 < System.currentTimeMillis()) {
            superarmour = false;
        }

        centerY += speedY;

        rect.set(centerX - 20, centerY - 30, centerX + 20, centerY + 30);
//        rect.set(centerX - 20, centerY - 35, centerX + 20, centerY + 35);  ->예전소스
    }

    public boolean isDown() {
        return down;
    }

    public void jump() {
        if (jumped == false) {
            speedY = JUMPSPEED;
            jumped = true;
            down = false;
        }

    }

    public int getCenterX() {
        return centerX;
    }

    public void moveCenterX(int speedX) {
        centerX += speedX;
        rect.set(centerX - 25, centerY - 25, centerX + 25, centerY + 25);
    }

    public void moveItem(int speedX) {
        moveItem = true;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterX() {
        centerX = 300;
    }

    public boolean isJumped() {
        return jumped;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSelect() {
        return select;
    }

    public void addShield() {
        shield = true;
    }

    public void useShield() {
        shield = false;
        superarmour = true;
        time = System.currentTimeMillis();
    }

    public boolean isShield() {
        return shield;
    }

    public boolean isSuperarmour() {
        return superarmour;
    }
}
