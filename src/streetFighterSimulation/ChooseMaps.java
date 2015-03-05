package streetFighterSimulation;

import enums.ScaleOfScreen;
import greenfoot.World;

public class ChooseMaps extends World {
	
	Player dino = new Player();
	
	public ChooseMaps() {
		super(ScaleOfScreen.WIDTH.getNum(), ScaleOfScreen.HEIGHT.getNum(), 1);
		this.addObject(new Background("image/Map_1.png"), ScaleOfScreen.WIDTH.getNum()/2, ScaleOfScreen.HEIGHT.getNum()/2);
		AddMapChoices();
		addObject(dino, ScaleOfScreen.WIDTH.getNum()/10, ScaleOfScreen.HEIGHT.getNum()-(ScaleOfScreen.HEIGHT.getNum()/10));
	}

	private void AddMapChoices() {
		this.addObject(new MapButton("image/Dragon_Stage.gif"), ScaleOfScreen.WIDTH.getNum()/4, ScaleOfScreen.HEIGHT.getNum()/4);
		this.addObject(new MapButton("image/Forest-0_Stage.gif"), ScaleOfScreen.WIDTH.getNum()/4*3, ScaleOfScreen.HEIGHT.getNum()/4);
		this.addObject(new MapButton("image/Forest-1_Stage.gif"), ScaleOfScreen.WIDTH.getNum()/4, ScaleOfScreen.HEIGHT.getNum()/4*3);
		this.addObject(new MapButton("image/Temple_Stage.gif"), ScaleOfScreen.WIDTH.getNum()/4*3, ScaleOfScreen.HEIGHT.getNum()/4*3);
	}
}
