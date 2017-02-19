package kr.sunrin.maningraves;

import kr.sunrin.framework.Game;
import kr.sunrin.framework.Graphics;
import kr.sunrin.framework.Screen;
import kr.sunrin.framework.Graphics.*;
import kr.sunrin.framework.etc.DataManager;

public class LoadingScreen extends Screen {

    String istutorial;

    public LoadingScreen(Game game) {
        super(game);
        istutorial = DataManager.getInstance(null).getPreferences("TUTORIAL");
        if (istutorial.equals("")) {
            DataManager.getInstance(null).savePreferences("TUTORIAL", "0");
        }
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();

        Assets.pauseScreen = g.newImage("Pause/pause_screen.png", ImageFormat.ARGB8888);
        Assets.gameOverScreen = g.newImage("Gameover/gameoverScreen.jpg", ImageFormat.ARGB8888);
        Assets.pause = g.newImage("Pause/pause.png", ImageFormat.ARGB8888);

        Assets.story1 = g.newImage("Story/story1.png", ImageFormat.ARGB8888);
        Assets.story2 = g.newImage("Story/story2.png", ImageFormat.ARGB8888);
        Assets.story3 = g.newImage("Story/story3.png", ImageFormat.ARGB8888);

        Assets.menu = g.newImage("Menu/menu.png", ImageFormat.RGB565);
        Assets.object = g.newImage("Menu/object.png", ImageFormat.RGB565);
        Assets.star = g.newImage("Menu/star.png", ImageFormat.RGB565);
        Assets.tree = g.newImage("Menu/tree.png", ImageFormat.RGB565);


        Assets.ground = g.newImage("Background/ground.png", ImageFormat.RGB565);

        Assets.help1 = g.newImage("Help/help1.png", ImageFormat.RGB565);
        Assets.help2 = g.newImage("Help/help2.png", ImageFormat.RGB565);
        Assets.help3 = g.newImage("Help/help3.png", ImageFormat.RGB565);
        Assets.help4 = g.newImage("Help/help4.png", ImageFormat.RGB565);
        Assets.help5 = g.newImage("Help/help5.png", ImageFormat.RGB565);
        Assets.help6 = g.newImage("Help/help6.png", ImageFormat.RGB565);
        Assets.help7 = g.newImage("Help/help7.png", ImageFormat.RGB565);
        Assets.help8 = g.newImage("Help/help8.png", ImageFormat.RGB565);

        Assets.running1 = g.newImage("Player/1.png", ImageFormat.RGB565);
        Assets.running2 = g.newImage("Player/2.png", ImageFormat.RGB565);
        Assets.running3 = g.newImage("Player/3.png", ImageFormat.RGB565);
        Assets.running4 = g.newImage("Player/4.png", ImageFormat.ARGB8888);
        Assets.running5 = g.newImage("Player/5.png", ImageFormat.ARGB8888);
        Assets.running6 = g.newImage("Player/6.png", ImageFormat.ARGB8888);
        Assets.running7 = g.newImage("Player/7.png", ImageFormat.ARGB8888);
        Assets.running8 = g.newImage("Player/8.png", ImageFormat.ARGB8888);
        Assets.running9 = g.newImage("Player/9.png", ImageFormat.ARGB8888);
        Assets.running10 = g.newImage("Player/10.png", ImageFormat.ARGB8888);
        Assets.running11 = g.newImage("Player/11.png", ImageFormat.ARGB8888);
        Assets.running12 = g.newImage("Player/12.png", ImageFormat.ARGB8888);

        Assets._running1 = g.newImage("Player/_1.png", ImageFormat.RGB565);
        Assets._running2 = g.newImage("Player/_2.png", ImageFormat.RGB565);
        Assets._running3 = g.newImage("Player/_3.png", ImageFormat.RGB565);
        Assets._running4 = g.newImage("Player/_4.png", ImageFormat.RGB565);
        Assets._running5 = g.newImage("Player/_5.png", ImageFormat.RGB565);
        Assets._running6 = g.newImage("Player/_6.png", ImageFormat.RGB565);
        Assets._running7 = g.newImage("Player/_7.png", ImageFormat.RGB565);
        Assets._running8 = g.newImage("Player/_8.png", ImageFormat.RGB565);
        Assets._running9 = g.newImage("Player/_9.png", ImageFormat.RGB565);
        Assets._running10 = g.newImage("Player/_10.png", ImageFormat.RGB565);
        Assets._running11 = g.newImage("Player/_11.png", ImageFormat.RGB565);
        Assets._running12 = g.newImage("Player/_12.png", ImageFormat.RGB565);

        Assets.switchrunning1 = g.newImage("Player/switch1.png", ImageFormat.RGB565);
        Assets.switchrunning2 = g.newImage("Player/switch2.png", ImageFormat.RGB565);
        Assets.switchrunning3 = g.newImage("Player/switch3.png", ImageFormat.RGB565);
        Assets.switchrunning4 = g.newImage("Player/switch4.png", ImageFormat.RGB565);
        Assets.switchrunning5 = g.newImage("Player/switch5.png", ImageFormat.RGB565);
        Assets.switchrunning6 = g.newImage("Player/switch6.png", ImageFormat.RGB565);
        Assets.switchrunning7 = g.newImage("Player/switch7.png", ImageFormat.RGB565);
        Assets.switchrunning8 = g.newImage("Player/switch8.png", ImageFormat.RGB565);
        Assets.switchrunning9 = g.newImage("Player/switch9.png", ImageFormat.RGB565);
        Assets.switchrunning10 = g.newImage("Player/switch10.png", ImageFormat.RGB565);
        Assets.switchrunning11 = g.newImage("Player/switch11.png", ImageFormat.RGB565);
        Assets.switchrunning12 = g.newImage("Player/switch12.png", ImageFormat.RGB565);

        Assets.switch_running1 = g.newImage("Player/switch_1.png", ImageFormat.RGB565);
        Assets.switch_running2 = g.newImage("Player/switch_2.png", ImageFormat.RGB565);
        Assets.switch_running3 = g.newImage("Player/switch_3.png", ImageFormat.RGB565);
        Assets.switch_running4 = g.newImage("Player/switch_4.png", ImageFormat.RGB565);
        Assets.switch_running5 = g.newImage("Player/switch_5.png", ImageFormat.RGB565);
        Assets.switch_running6 = g.newImage("Player/switch_6.png", ImageFormat.RGB565);
        Assets.switch_running7 = g.newImage("Player/switch_7.png", ImageFormat.RGB565);
        Assets.switch_running8 = g.newImage("Player/switch_8.png", ImageFormat.RGB565);
        Assets.switch_running9 = g.newImage("Player/switch_9.png", ImageFormat.RGB565);
        Assets.switch_running10 = g.newImage("Player/switch_10.png", ImageFormat.RGB565);
        Assets.switch_running11 = g.newImage("Player/switch_11.png", ImageFormat.RGB565);
        Assets.switch_running12 = g.newImage("Player/switch_12.png", ImageFormat.RGB565);

        Assets.shieldOn = g.newImage("Player/shield.png", ImageFormat.ARGB8888);
        Assets.tombstone = g.newImage("Structure/Tombstone_2.png", ImageFormat.ARGB4444);
        Assets.skeletonHand = g.newImage("Structure/SkeletonHand_2.png", ImageFormat.ARGB4444);
        Assets.tombstone2 = g.newImage("Structure/Tombstone_1.png", ImageFormat.ARGB4444);
        Assets.skeletonHand2 = g.newImage("Structure/SkeletonHand_1.png", ImageFormat.ARGB4444);

        Assets.shield = g.newImage("Items/shield_1.png", ImageFormat.ARGB4444);
        Assets.moving = g.newImage("Items/moving_1.png", ImageFormat.ARGB4444);
        Assets.shield2 = g.newImage("Items/shield_2.png", ImageFormat.ARGB4444);
        Assets.moving2 = g.newImage("Items/moving_2.png", ImageFormat.ARGB4444);

        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
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