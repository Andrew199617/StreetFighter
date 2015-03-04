package streetFighterSimulation;

import greenfoot.World;

public class HomeScreen extends World {

	private static final int WORLDWIDTH = 1440, WORLDHEIGHT = 810;
	
	public HomeScreen(int worldWidth, int worldHeight, int cellSize) {
		super(WORLDWIDTH, WORLDHEIGHT, cellSize);
		this.addObject(new Background(), 0, 0);
	}

}
