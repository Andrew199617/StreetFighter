package streetFighterSimulation;

import charcter.Player;
import buttons.MapButton;
import greenfoot.World;

public class Stage extends World {

	private static int WORLD_WIDTH;
	private static int WORLD_HEIGHT;

	Raptor dino = new Raptor();

	public Stage(int worldWidth, int worldHeight, int cellSize, MapButton mapButton) {
		super(worldWidth, worldHeight, cellSize);
		setBackground(mapButton,worldHeight,worldWidth);
		WORLD_WIDTH = worldWidth;
		WORLD_HEIGHT = worldHeight;
		addObject(dino, WORLD_WIDTH-(WORLD_WIDTH/10), WORLD_HEIGHT-(WORLD_HEIGHT/10));
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
