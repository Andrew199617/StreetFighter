package charcter;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Raptor extends Player{
	
	private int animate = 0;
	private static final int MAX_JUMP = 25;
	public boolean maxed = false;

	public Raptor(){
		super (6, 8, "up", Character.RAPTOR);
	}

}
