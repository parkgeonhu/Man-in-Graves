package kr.sunrin.maningraves;

import android.graphics.Color;
import android.graphics.Paint;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import kr.sunrin.framework.Game;
import kr.sunrin.framework.Graphics;
import kr.sunrin.framework.Image;
import kr.sunrin.framework.Input;
import kr.sunrin.framework.Input.*;
import kr.sunrin.framework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    private final int MAX_WIDTH = 800;
    private final int MAX_HEIGHT = 480;
    boolean GAMEOVER;
    private static Background bg1, bg2;
    private static Background g1, g2;
    private static Player player[] = new Player[2];
    private static StructureManager structureManager[] = new StructureManager[2];
    private int select = 0;
    public static int score = 0;
    private Image stone;
    private Image hand;
    private Image run_temp, _switch_temp;
    private Image _run_temp, switch_temp;
    public static FileOutput fileOutput;
    public static FileInput fileInput;
    private int len;
    private Animation runAni, _runAni, switchAni, _switchAni;
    Paint paint, highscore, _nowScore, nowScore;  //nowscore -> 오른쪽, _nowscore->pause,gameover   highscore->pause highscore

    public GameScreen(Game game) {
        super(game);

        String path="";
        Random random;
        Date date;
        int hour;
        date = new Date();
        hour = date.getHours();
        random=new Random();
        if (3 <= hour && hour < 8) {
            int num = random.nextInt(4)+1;
            path = "Background/3-8/"+String.valueOf(num)+".png";
        }
        else if (8 <= hour && hour < 16) {
            int num = random.nextInt(6)+1;
            path = "Background/8-16/"+String.valueOf(num)+".png";
        }
        else if (16 <= hour && hour < 22) {
            int num = random.nextInt(10)+1;
            path = "Background/16-22/"+String.valueOf(num)+".png";
        }
        else if (22 <= hour || hour < 3) {
            int num = random.nextInt(6)+1;
            path = "Background/22-3/"+String.valueOf(num)+".png";
        }

        Graphics g=game.getGraphics();
        Assets.background = g.newImage(path, Graphics.ImageFormat.RGB565);


        select = 0;
        score = 0;
        Assets.setClick();
        GAMEOVER = false;
        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);
        g1 = new Background(0, 227);
        g2 = new Background(2160, 227);
        player[0] = new Player(1);
        player[1] = new Player(2);
        structureManager[0] = new StructureManager(1);
        structureManager[1] = new StructureManager(2);
        stone = Assets.tombstone;
        hand = Assets.skeletonHand;

        run_temp=Assets.running7;
        _switch_temp=Assets.switch_running7;
        _run_temp=Assets._running7;
        switch_temp=Assets.switchrunning7;

        runAni = new Animation();
        runAni.addFrame(Assets.running1, 20);
        runAni.addFrame(Assets.running2, 20);
        runAni.addFrame(Assets.running3, 20);
        runAni.addFrame(Assets.running4, 20);
        runAni.addFrame(Assets.running5, 20);
        runAni.addFrame(Assets.running6, 20);
        runAni.addFrame(Assets.running7, 20);
        runAni.addFrame(Assets.running8, 20);
        runAni.addFrame(Assets.running9, 20);
        runAni.addFrame(Assets.running10, 20);
        runAni.addFrame(Assets.running11, 20);
        runAni.addFrame(Assets.running12, 20);

        _runAni = new Animation();
        _runAni.addFrame(Assets._running1, 20);
        _runAni.addFrame(Assets._running2, 20);
        _runAni.addFrame(Assets._running3, 20);
        _runAni.addFrame(Assets._running4, 20);
        _runAni.addFrame(Assets._running5, 20);
        _runAni.addFrame(Assets._running6, 20);
        _runAni.addFrame(Assets._running7, 20);
        _runAni.addFrame(Assets._running8, 20);
        _runAni.addFrame(Assets._running9, 20);
        _runAni.addFrame(Assets._running10, 20);
        _runAni.addFrame(Assets._running11, 20);
        _runAni.addFrame(Assets._running12, 20);

        switchAni = new Animation();
        switchAni.addFrame(Assets.switchrunning1, 20);
        switchAni.addFrame(Assets.switchrunning2, 20);
        switchAni.addFrame(Assets.switchrunning3, 20);
        switchAni.addFrame(Assets.switchrunning4, 20);
        switchAni.addFrame(Assets.switchrunning5, 20);
        switchAni.addFrame(Assets.switchrunning6, 20);
        switchAni.addFrame(Assets.switchrunning7, 20);
        switchAni.addFrame(Assets.switchrunning8, 20);
        switchAni.addFrame(Assets.switchrunning9, 20);
        switchAni.addFrame(Assets.switchrunning10, 20);
        switchAni.addFrame(Assets.switchrunning11, 20);
        switchAni.addFrame(Assets.switchrunning12, 20);

        _switchAni = new Animation();
        _switchAni.addFrame(Assets.switch_running1, 20);
        _switchAni.addFrame(Assets.switch_running2, 20);
        _switchAni.addFrame(Assets.switch_running3, 20);
        _switchAni.addFrame(Assets.switch_running4, 20);
        _switchAni.addFrame(Assets.switch_running5, 20);
        _switchAni.addFrame(Assets.switch_running6, 20);
        _switchAni.addFrame(Assets.switch_running7, 20);
        _switchAni.addFrame(Assets.switch_running8, 20);
        _switchAni.addFrame(Assets.switch_running9, 20);
        _switchAni.addFrame(Assets.switch_running10, 20);
        _switchAni.addFrame(Assets.switch_running11, 20);
        _switchAni.addFrame(Assets.switch_running12, 20);

        nowScore = new Paint();
        nowScore.setTextSize(40);
        nowScore.setTextAlign(Paint.Align.RIGHT);
        nowScore.setAntiAlias(true);
        nowScore.setColor(Color.WHITE);

        _nowScore = new Paint();
        _nowScore.setTextSize(75);
        _nowScore.setTextAlign(Paint.Align.LEFT);
        _nowScore.setAntiAlias(true);
        _nowScore.setColor(Color.WHITE);


        highscore = new Paint();
        highscore.setTextSize(40);
        highscore.setTextAlign(Paint.Align.LEFT);
        highscore.setAntiAlias(true);
        highscore.setColor(Color.WHITE);

        fileInput = new FileInput(game, "score.txt");
        try {
            if (fileInput.getFis() == null) {
                fileOutput = new FileOutput(game, "score.txt");
                fileOutput.write("0");
                fileOutput.close();
            }
            len = Integer.parseInt(fileInput.getData().trim());
        } catch (IOException e) {

        }
        fileInput.close();

//        _run_temp = _runAni.getImage();
//        switch_temp = switchAni.getImage();
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);

    }

    private void updateReady(List<TouchEvent> touchEvents) {

        if (Assets.isPlaying() == false) Assets.onResume();

        RunningGame.changeMusic(1);
        state = GameState.Running;

    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

        if (Assets.isPlaying() == false) Assets.onResume();

        if (GAMEOVER) {
            state = GameState.GameOver;
            Assets.onStop();
            Assets.fail.play(0.5f);
        }
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 0, MAX_WIDTH / 2, MAX_HEIGHT) && player[select].isJumped() == false) {
                    player[0].jump();
                    player[1].jump();
                    Assets.jump.play(0.5f);

                } else if (inBounds(event, MAX_WIDTH / 2, 0, MAX_WIDTH / 2, MAX_HEIGHT) && !inBounds(event, 730, 20, 50, 50)) {
                    Assets.switching.play(0.5f);
                    select++;
                    if (select == 2) {
                        select = 0;
                    }
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {

                if (inBounds(event, 742, 12, 45, 45)) {
                    Assets.onPause();
                    pause();

                }
            }

        }

        player[0].update();
        player[1].update();

        bg1.update();
        bg2.update();
        g1.update();
        g2.update();

        for (int i = 0; i < 2; i++) {
            structureManager[i].update(player, select, this);
        }
        animate();

        if (player[select].getCenterX() < 0) {
            state = GameState.GameOver;
            Assets.onStop();
            Assets.fail.play(0.5f);
        }

        setSpeed();
        if (score > 10000) StructureManager.term = 1500;
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 595, 337, 187, 50)) {
                    Assets.onResume();
                    resume();
                }

                if (inBounds(event, 595, 397, 187, 50)) {
                    nullify();
                    goToMenu();
                    RunningGame.changeMusic(0);
                }

                if (inBounds(event, 595, 277, 187, 50)) {
                    nullify();
//                    changeImage();
                    game.setScreen(new GameScreen(game));

                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 595, 397, 187, 50)) {
                    RunningGame.changeMusic(0);
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 595, 337, 187, 50)) {
                    nullify();
//                    changeImage();
                    game.setScreen(new GameScreen(game));
                }
            }
        }
    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
        g.drawImage(Assets.pause, 742, 12);

        for (int i = 0; i < 2; i++) {
            structureManager[i].paint(g, hand, stone);
        }

        if (player[select].isShield()) {
            if (select == 0) {
                g.drawImage(Assets.shieldOn, player[select].getCenterX() - 70, player[select].getCenterY() - 105);
            } else {
                g.drawImage(Assets.shieldOn, player[select].getCenterX() - 70, player[select].getCenterY() - 55);
            }
        }

        if (select == 0) {
            if (player[0].jumped == false && player[1].jumped == false) {
                g.drawImage(runAni.getImage(), player[0].getCenterX() - 40, player[0].getCenterY() - 60);
                g.drawImage(_switchAni.getImage(), player[1].getCenterX() - 40, player[1].getCenterY() - 40);
            } else {
                g.drawImage(run_temp, player[0].getCenterX() - 40, player[0].getCenterY() - 60);
                g.drawImage(_switch_temp, player[1].getCenterX() - 40, player[1].getCenterY() - 40);
            }
        } else {
            if (player[0].jumped == false && player[1].jumped == false) {
                g.drawImage(_runAni.getImage(), player[0].getCenterX() - 40, player[0].getCenterY() - 60);
                g.drawImage(switchAni.getImage(), player[1].getCenterX() - 40, player[1].getCenterY() - 40);
            } else {
                g.drawImage(_run_temp, player[0].getCenterX() - 40, player[0].getCenterY() - 60);
                g.drawImage(switch_temp, player[1].getCenterX() - 40, player[1].getCenterY() - 40);
            }

        }

        g.drawImage(Assets.ground, g1.getBgX(), g1.getBgY());
        g.drawImage(Assets.ground, g2.getBgX(), g2.getBgY());

        if (state == GameState.Running)
            drawGameScore();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    public void animate() {
        runAni.update(7);
        _runAni.update(7);
        _switchAni.update(7);
        switchAni.update(7);
    }

    private void nullify() {

        paint = null;
        bg1 = null;
        bg2 = null;
        g1 = null;
        g2 = null;
        stone = null;
        hand = null;
        player[0] = null;
        player[1] = null;
        structureManager[0] = null;
        structureManager[1] = null;
        runAni = null;
        _runAni = null;
        switchAni = null;
        _switchAni = null;

        System.gc();

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        g.drawImage(Assets.pauseScreen, 0, 0);
        g.drawString(Integer.toString(score), 29, 230, _nowScore);
        if (score > len) {
            fileOutput = new FileOutput(game, "score.txt");
            fileOutput.write(Integer.toString(score));
            g.drawString(Integer.toString(score), 248, 437, highscore);
            fileOutput.close();
        } else {
            g.drawString(Integer.toString(len), 248, 437, highscore);
        }
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawARGB(155, 0, 0, 0);
        g.drawImage(Assets.gameOverScreen, 0, 0);
        g.drawString(Integer.toString(score), 29, 230, _nowScore);
        if (score > len) {
            fileOutput = new FileOutput(game, "score.txt");
            fileOutput.write(Integer.toString(score));
            g.drawString(Integer.toString(score), 248, 437, highscore);
            fileOutput.close();
        } else {
            g.drawString(Integer.toString(len), 248, 437, highscore);
        }

    }

    private void drawGameScore() {
        Graphics g = game.getGraphics();
        g.drawString(Integer.toString(score), 780, 110, nowScore);
    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        game.setScreen(new MainMenuScreen(game));

    }

    public void addScore(int score) {
        this.score += score;
    }

    public void setSpeed() {
        bg1.setFinalSpeedX(score / 30000);
        bg2.setFinalSpeedX(score / 30000);
        g1.setFinalSpeedX(score / 30000);
        g2.setFinalSpeedX(score / 30000);
        structureManager[0].setFinalSpeedX(score / 30000);
        structureManager[1].setFinalSpeedX(score / 30000);
    }

//    public void changeImage() {
//
//
//        date = new Date();
//        hour = date.getHours();
//        random = new Random();
//        if (3 <= hour && hour < 8) {
//            int num = random.nextInt(4) + 1;
//            path = "Background/3-8/" + String.valueOf(num) + ".png";
//        } else if (8 <= hour && hour < 16) {
//            int num = random.nextInt(6) + 1;
//            path = "Background/8-16/" + String.valueOf(num) + ".png";
//        } else if (16 <= hour && hour < 22) {
//            int num = random.nextInt(10) + 1;
//            path = "Background/16-22/" + String.valueOf(num) + ".png";
//        } else if (22 <= hour || hour < 3) {
//            int num = random.nextInt(6) + 1;
//            path = "Background/22-3/" + String.valueOf(num) + ".png";
//        }
//
//        Graphics g=game.getGraphics();
//        Assets.background = g.newImage(path, Graphics.ImageFormat.RGB565);
//
//    }  //background theme change

}