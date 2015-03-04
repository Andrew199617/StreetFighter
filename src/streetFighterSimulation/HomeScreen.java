package streetFighterSimulation;

import greenfoot.World;

public class HomeScreen extends World {

	private static final int WORLDWIDTH = 1440, WORLDHEIGHT = 810;
	
	public HomeScreen() {
		super(WORLDWIDTH, WORLDHEIGHT, 1);
		this.addObject(new Background("image/Map_1.png"), WORLDWIDTH/2, WORLDHEIGHT/2);
		this.addObject(new Button("image/Dragon_Stage.gif"), WORLDWIDTH/2, WORLDHEIGHT/2);
	}

}
