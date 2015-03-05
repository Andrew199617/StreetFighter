package streetFighterSimulation;

import greenfoot.World;

public class Stage extends World {

	Player dino = new Player();
	
	public Stage(int worldWidth, int worldHeight, int cellSize, MapButton mapButton) {
		super(worldWidth, worldHeight, cellSize);
		setBackground(mapButton,worldHeight,worldWidth);
		addObject(dino, worldWidth/10, worldHeight-(worldHeight/10));
	}

	private void setBackground(MapButton mapButton, int worldHeight, int worldWidth) {
		switch (mapButton.nameOfMap){
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
