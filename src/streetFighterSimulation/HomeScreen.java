package streetFighterSimulation;

import sounds.Music;
import buttons.MainMenuButton;
import enums.ScaleOfScreen;
import greenfoot.World;

public class HomeScreen extends World {

	
	private static final int DISTANCE_FROM_TOP_OF_SCREEN = 270;

	public HomeScreen() {
		super(ScaleOfScreen.WIDTH.getNum(), ScaleOfScreen.HEIGHT.getNum(), 1);
		this.addObject(new Background("image/Map_1.png"), ScaleOfScreen.WIDTH.getNum()/2, ScaleOfScreen.HEIGHT.getNum()/2);
		makeMenu();
		Music.Tempting_Secrets.play();
	}

	private void makeMenu() {
		this.addObject(new MainMenuButton("CPU VS CPU",100), ScaleOfScreen.WIDTH.getNum()/2, DISTANCE_FROM_TOP_OF_SCREEN);
		this.addObject(new MainMenuButton("Options",100), ScaleOfScreen.WIDTH.getNum()/2, DISTANCE_FROM_TOP_OF_SCREEN*2);
//		this.addObject(new MainMenuButton("Give UP",100), ScaleOfScreen.WIDTH.getNum()/2, DISTANCE_FROM_TOP_OF_SCREEN);
	}

}
