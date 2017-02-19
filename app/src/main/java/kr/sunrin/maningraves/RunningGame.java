package kr.sunrin.maningraves;

import kr.sunrin.framework.Screen;
import kr.sunrin.framework.implementation.AndroidGame;

public class RunningGame extends AndroidGame {
    boolean firstTimeCreate = true;

    @Override
    public Screen getInitScreen() {

        if (firstTimeCreate) {
            Assets.load(this);
            firstTimeCreate = false;
        }

        return new SplashLoadingScreen(this);

    }

    @Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }

    @Override
    public void onResume() {
        super.onResume();
        Assets.theme.play();
    }

    @Override
    public void onPause() {
        super.onPause();
        Assets.theme.pause();
    }



    public static void changeMusic(int num)
    {
        Assets.changeMusic(num);
    }
}
