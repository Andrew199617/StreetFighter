package streetFighterSimulation;

import greenfoot.World;

public class HomeScreen extends World {

	private static final int WORLDWIDTH = 1440, WORLDHEIGHT = 810;
	
	public HomeScreen() {
		super(WORLDWIDTH, WORLDHEIGHT, 1);

		this.addObject(new Background(), WORLDWIDTH/2, WORLDHEIGHT/2);
	}

}
