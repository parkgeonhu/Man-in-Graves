package kr.sunrin.maningraves;


import kr.sunrin.framework.Image;
import kr.sunrin.framework.Music;
import kr.sunrin.framework.Sound;

public class Assets {
	
	public static Image menu, background, ground, shieldOn, pauseScreen, gameOverScreen;
	public static Image tombstone, skeletonHand;
	public static Image running1, running2, running3, running4, running5, running6, running7, running8,running9, running10, running11, running12;
	public static Image switchrunning1, switchrunning2, switchrunning3, switchrunning4, switchrunning5, switchrunning6, switchrunning7, switchrunning8, switchrunning9, switchrunning10, switchrunning11, switchrunning12;
	public static Image _running1, _running2, _running3, _running4, _running5, _running6, _running7, _running8, _running9, _running10, _running11, _running12;
	public static Image switch_running1, switch_running2, switch_running3, switch_running4, switch_running5, switch_running6, switch_running7, switch_running8, switch_running9, switch_running10, switch_running11, switch_running12 ;
	public static Image star, tree, object;
	public static Image pause, help1, help2, help3, help4, help5, help6, help7, help8;
	public static Image story1, story2, story3;
	public static Image tombstone2, skeletonHand2;
	public static Image shield, moving, shield2, moving2;
	public static Sound jump, switching, fail, itemget;
	public static Music theme;
	static RunningGame _runningGame;
	
	public static void load(RunningGame runningGame) {
		// TODO Auto-generated method stub
		theme = runningGame.getAudio().createMusic("Sound/Strange_Ways.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
		_runningGame = runningGame;
	}

	public static void changeMusic(int num)
	{
		switch(num)
		{
			case 0:
				theme.stop();
				theme = _runningGame.getAudio().createMusic("Sound/Strange_Ways.mp3");
				theme.setLooping(true);
				theme.setVolume(0.85f);
				theme.play();
				break;
			case 1:
				theme.stop();
				theme = _runningGame.getAudio().createMusic("Sound/Midnight.mp3");
				theme.setLooping(true);
				theme.setVolume(0.85f);
				theme.play();
				break;
		}
	}

	public static void setClick()
	{
		jump = _runningGame.getAudio().createSound("Sound/Button_touch.mp3");
		fail = _runningGame.getAudio().createSound("Sound/Earthy_Crust_(Sting).mp3");
		switching = _runningGame.getAudio().createSound("Sound/Scene.mp3");
		itemget = _runningGame.getAudio().createSound("Sound/itemget.mp3");
	}

	public static void onPause()
	{
		theme.pause();
	}

	public static void onResume()
	{
		theme.play();
	}

	public static void onStop()
	{
		theme.stop();
	}

	public static boolean isPlaying() {return theme.isPlaying(); }
}
