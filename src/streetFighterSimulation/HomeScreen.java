package streetFighterSimulation;

import greenfoot.World;

public class HomeScreen extends World {

	private static final int WORLDWIDTH = 1440, WORLDHEIGHT = 810;
	
	public HomeScreen() {
		super(WORLDWIDTH, WORLDHEIGHT, 1);
		this.addObject(new Background("image/Map_1.png"), WORLDWIDTH/2, WORLDHEIGHT/2);
		AddMapChoices();
	}

	private void AddMapChoices() {
		this.addObject(new MapButton("image/Dragon_Stage.gif"), WORLDWIDTH/4, WORLDHEIGHT/4);
		this.addObject(new MapButton("image/Forest-0_Stage.gif"), WORLDWIDTH/4*3, WORLDHEIGHT/4);
		this.addObject(new MapButton("image/Forest-1_Stage.gif"), WORLDWIDTH/4, WORLDHEIGHT/4*3);
		this.addObject(new MapButton("image/Temple_Stage.gif"), WORLDWIDTH/4*3, WORLDHEIGHT/4*3);
	}
//alslasmdasf
}
