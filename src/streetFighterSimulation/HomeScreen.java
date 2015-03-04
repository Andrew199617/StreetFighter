package streetFighterSimulation;

import greenfoot.World;

public class HomeScreen extends World {

	private static final int WORLDWIDTH = 1440, WORLDHEIGHT = 810;
	
	public HomeScreen() {
		super(WORLDWIDTH, WORLDHEIGHT, 1);
<<<<<<< HEAD
		this.addObject(new Background("image/Map_1.png"), WORLDWIDTH/2, WORLDHEIGHT/2);

=======
		this.addObject(new Background(), WORLDWIDTH/2, WORLDHEIGHT/2);
		this.addObject(new Background(), 0, 0);
>>>>>>> 38ae53d1e91b8725c35db8c3823a59760c77472e
	}

}
