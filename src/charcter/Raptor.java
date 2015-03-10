package charcter;

import enums.ScaleOfScreen;
import interfaces.Player_Status;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Raptor extends Player implements Player_Status{
	
	public Status stat;
	public Status[] bars;
	private int x = ScaleOfScreen.WIDTH.getNum()-30;
	private int y = 30;

	public Raptor(){
		super (6, 8, 0, "up", Character.RAPTOR);
	}
	@Override
	public void healthDisplay(int health){ 
		this.bars = new Status[health/4];
		for(int i = 0; i < health/4; i++){
			stat = new Status();
			bars[i] = stat;
			getWorld().addObject(stat, x, y);
			x -= 20;
		}
		health--;
	}
	@Override
	public void setHealth(int i) {
		health = i;
		bars = new Status[health];
	}

	@Override
	public int getHealth() {
		return health;
	}
}
