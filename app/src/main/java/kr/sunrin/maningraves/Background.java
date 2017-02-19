package kr.sunrin.maningraves;


public class Background {
	
	private int bgX, bgY, speedX;
	private final int speedFinalX = -5;
	
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedX = speedFinalX;
	}
	
	public void update() {
		bgX += speedX;

		if (bgX <= -2160){
			bgX += 4320;
		}
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public void setSpeedX(int speedX) { this.speedX  = speedX;}

	public int getSpeedX() {
		return speedX;
	}

	public void setFinalSpeedX(int speedX) { this.speedX = speedFinalX - speedX;  if(this.speedX < -10) this.speedX = -10;}
}
