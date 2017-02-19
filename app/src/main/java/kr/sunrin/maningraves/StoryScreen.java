package kr.sunrin.maningraves;

import java.util.List;

import kr.sunrin.framework.Game;
import kr.sunrin.framework.Graphics;
import kr.sunrin.framework.Input;
import kr.sunrin.framework.Screen;

/**
 * Created by 건후 on 2015-08-30.
 */
public class StoryScreen extends Screen {

    int index;

    public StoryScreen(Game game) {
        super(game);
        index = 0;
    }

    @Override
    public void update(float deltaTime) {
        if (index > 2) {
            game.setScreen(new GameScreen(game));
        } else {
            List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
            int len = touchEvents.size();
            for (int i = 0; i < len; i++) {
                Input.TouchEvent event = touchEvents.get(i);
                if (event.type == Input.TouchEvent.TOUCH_UP) {
                    index++;
                    if (inBounds(event, 642, 428, 62, 44)) {
                        game.setScreen(new GameScreen(game));
                    }
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
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
        if (index == 0) {
            g.drawImage(Assets.story1, 0, 0);
        } else if (index == 1) {
            g.drawImage(Assets.story2, 0, 0);
        } else if (index == 2) {
            g.drawImage(Assets.story3, 0, 0);
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
        game.setScreen(new MainMenuScreen(game));
    }
}
