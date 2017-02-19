package kr.sunrin.maningraves;

import android.graphics.Color;
import android.graphics.Paint;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import kr.sunrin.framework.Game;
import kr.sunrin.framework.Graphics;
import kr.sunrin.framework.Input;
import kr.sunrin.framework.Input.*;
import kr.sunrin.framework.Screen;
import kr.sunrin.framework.etc.DataManager;

public class MainMenuScreen extends Screen {

	Background star, object, tree;
	Background star2, object2, tree2, ground2;
	Paint paint;
	private static int blink = 50;
	private static boolean isBlink = false;
	private int helpCount;
	Paint scorePaint;

	String istutorial;

	String path;
	Random random;

	Date date;
	int hour;


	public MainMenuScreen(Game game) {
		super(game);

		star = new Background(0,0); star2 = new Background(2160,0);
		object = new Background(0,330); object2 = new Background(2160,330);
		tree = new Background(0,270); tree2 = new Background(2160,270);
		ground2 = new Background(-1,415);
		star.setSpeedX(-1); star2.setSpeedX(-1);
		object.setSpeedX(-2); object2.setSpeedX(-2);
		tree.setSpeedX(-3); tree2.setSpeedX(-3);

		helpCount = 0;

		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		scorePaint = new Paint();
		scorePaint.setTextSize(50);
		scorePaint.setTextAlign(Paint.Align.CENTER);
		scorePaint.setAntiAlias(true);
		scorePaint.setColor(Color.WHITE);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 480)) {
					if((!(inBounds(event, 729, 10 , 70, 70) )) && helpCount == 0) {
						istutorial = DataManager.getInstance(null).getPreferences("TUTORIAL");
						if(Integer.parseInt(istutorial)==1){
							game.setScreen(new GameScreen(game));
						}
						else{
							DataManager.getInstance(null).savePreferences("TUTORIAL","1");
							game.setScreen(new StoryScreen(game));
						}
					}
				}
				if (inBounds(event, 729, 10 , 70, 70) && helpCount == 0)
				{
					helpCount = 1;
				}

				if (inBounds(event, 0, 0, 800, 480) && helpCount != 0)
				{
					helpCount++;
					if(helpCount == 10) helpCount = 0;
				}
			}
		}

		star.update(); star2.update();
		object.update(); object2.update();
		tree.update(); tree2.update();
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);

		g.drawImage(Assets.star, star.getBgX(), star.getBgY());
		g.drawImage(Assets.star, star2.getBgX(), star2.getBgY());
		g.drawImage(Assets.object, object.getBgX(), object.getBgY());
		g.drawImage(Assets.object, object2.getBgX(), object2.getBgY());
		g.drawImage(Assets.ground, ground2.getBgX(), ground2.getBgY());
		g.drawImage(Assets.tree, tree2.getBgX(), tree2.getBgY());
		g.drawImage(Assets.tree, tree.getBgX(), tree.getBgY());

		try {
			Thread.sleep(1);
			paint.setAlpha(blink);
			System.out.println(blink);
			if(blink==50)    isBlink = false;
			if(blink==230)
				isBlink = true;
			if(isBlink)
			{
				blink-=4;
			}
			else{
				blink += 4;
			}

		} catch (Exception e)
		{

		}

		g.drawString("TOUCH TO START", 400, 390, paint);
		GameScreen.fileInput = new FileInput(game, "score.txt");
		try {
			if(GameScreen.fileInput.getFis() == null){
				GameScreen.fileOutput = new FileOutput(game, "score.txt");
				GameScreen.fileOutput.write("0");
				GameScreen.fileOutput.close();
				GameScreen.score = 0;
			}
			else {
				GameScreen.score = Integer.parseInt(GameScreen.fileInput.getData().trim());
				GameScreen.fileInput.close();
			}
		} catch (IOException e) {
			GameScreen.fileInput.close();
		}

		g.drawString(Integer.toString(GameScreen.score), 400, 320, scorePaint);

		if(helpCount != 0 && helpCount < 10)
		{
			if(helpCount == 2) g.drawImage(Assets.help1, 0, 0);
			if(helpCount == 3) g.drawImage(Assets.help2, 0, 0);
			if(helpCount == 4) g.drawImage(Assets.help3, 0, 0);
			if(helpCount == 5) g.drawImage(Assets.help4, 0, 0);
			if(helpCount == 6) g.drawImage(Assets.help5, 0, 0);
			if(helpCount == 7) g.drawImage(Assets.help6, 0, 0);
			if(helpCount == 8) g.drawImage(Assets.help7, 0, 0);
			if(helpCount == 9) g.drawImage(Assets.help8, 0, 0);
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
        android.os.Process.killProcess(android.os.Process.myPid());

	}
}
