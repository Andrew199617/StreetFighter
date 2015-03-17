package streetFighterSimulation;

import sounds.Music;
import sounds.SoundClips;
import charcter.*;
import enums.Character;
import enums.Maps;
import enums.ScaleOfScreen;
import buttons.MapButton;
import greenfoot.World;

public class Stage extends World {

	public static final int WORLD_WIDTH = ScaleOfScreen.WIDTH.getNum();
	public static final int WORLD_HEIGHT = ScaleOfScreen.HEIGHT.getNum();
	
	public static Maps map = null;

	Player dino;
	Player raptor;
	HealthBar stat = new HealthBar();

	public Stage(int worldWidth, int worldHeight, int cellSize, MapButton mapButton) {
		super(worldWidth, worldHeight, cellSize);
		setBackground(mapButton,worldHeight,worldWidth);
		
		dino = new Dragon();
		raptor = new Raptor();
		
		createPlayers();
		playAndStopMusic();
		
		dino.healthDisplay(dino.getHealth());
		
		raptor.healthDisplay(raptor.getHealth());
		
	}

	private void createPlayers() {
		addObject(dino, WORLD_WIDTH/10, Player.FLOOR);
		addObject(raptor, WORLD_WIDTH-(WORLD_WIDTH/10), Player.FLOOR);
	}
	private void playAndStopMusic() {
		SoundClips.Choose_Your_Map_SoundCLip.stop();
		Music.TheCurtainRises.stop();
		Music.two_Steps_From_Hell.playLoop();
	}
	
	private void setBackground(MapButton mapButton, int worldHeight, int worldWidth) {
		switch (mapButton.getNameOfMap()){
		case "image/Dragon_Stage.gif":
			map = Maps.DRAGON;
			this.addObject(new Background("image/Dragon_Stage.gif"),worldWidth/2 , worldHeight/2);
			break;
		case "image/Forest-1_Stage.gif":
			map = Maps.FOREST_TREE;
			this.addObject(new Background("image/Forest-1_Stage.gif"),worldWidth/2 , worldHeight/2);
			break;
		case "image/Forest-0_Stage.gif":
			map = Maps.FOREST_CLIFF;
			this.addObject(new Background("image/Forest-0_Stage.gif"),worldWidth/2 , worldHeight/2);
			break;
		case "image/Temple_Stage.gif":
			map = Maps.TEMPLE;
			this.addObject(new Background("image/Temple_Stage.gif"),worldWidth/2 , worldHeight/2);
			break;

		}
	}

}
