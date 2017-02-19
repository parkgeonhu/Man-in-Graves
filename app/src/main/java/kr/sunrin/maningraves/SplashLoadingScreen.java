package kr.sunrin.maningraves;


import kr.sunrin.framework.Game;
import kr.sunrin.framework.Graphics;
import kr.sunrin.framework.Graphics.*;
import kr.sunrin.framework.Screen;

public class SplashLoadingScreen extends Screen {
	public SplashLoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		game.setScreen(new LoadingScreen(game));
	}

	@Override
	public void paint(float deltaTime) {

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

	}
}