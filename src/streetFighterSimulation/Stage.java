package streetFighterSimulation;

import greenfoot.World;

public class Stage extends World {

	public Stage(int worldWidth, int worldHeight, int cellSize, MapButton mapButton) {
		super(worldWidth, worldHeight, cellSize);
		setBackground(mapButton,worldHeight,worldWidth);
	}

	private void setBackground(MapButton mapButton, int worldHeight, int worldWidth) {
		if(mapButton.nameOfMap == "image/Dragon_Stage.gif"){
			this.addObject(new Background("image/Dragon_Stage.gif"),worldWidth/2 , worldHeight/2);
		}
	}

}
