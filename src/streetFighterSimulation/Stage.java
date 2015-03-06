package streetFighterSimulation;

import sounds.Music;
import sounds.SoundClips;
import charcter.Player;
import buttons.MapButton;
import greenfoot.World;

public class Stage extends World {

	private static int WORLD_WIDTH;
	private static int WORLD_HEIGHT;

	Player dino = new Player();

	public Stage(int worldWidth, int worldHeight, int cellSize, MapButton mapButton) {
		super(worldWidth, worldHeight, cellSize);
		setBackground(mapButton,worldHeight,worldWidth);
		WORLD_WIDTH = worldWidth;
		WORLD_HEIGHT = worldHeight;
		addObject(dino, WORLD_WIDTH/10, WORLD_HEIGHT-(WORLD_HEIGHT/10));
		
		playAndStopMusic();
	}

	private void playAndStopMusic() {
		SoundClips.Choose_Your_Map_SoundCLip.stop();
		Music.TheCurtainRises.stop();
		Music.two_Steps_From_Hell.playLoop();
	}

	private void setBackground(MapButton mapButton, int worldHeight, int worldWidth) {
		switch (mapButton.getNameOfMap()){
		case "image/Dragon_Stage.gif":
			this.addObject(new Background("image/Dragon_Stage.gif"),worldWidth/2 , worldHeight/2);
			break;
		case "image/Forest-1_Stage.gif":
			this.addObject(new Background("image/Forest-1_Stage.gif"),worldWidth/2 , worldHeight/2);
			break;
		case "image/Forest-0_Stage.gif":
			this.addObject(new Background("image/Forest-0_Stage.gif"),worldWidth/2 , worldHeight/2);
			break;
		case "image/Temple_Stage.gif":
			this.addObject(new Background("image/Temple_Stage.gif"),worldWidth/2 , worldHeight/2);
			break;

		}
	}

}
