package charcter;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Player extends Actor{
	
	protected static final int CHAR_WIDTH = 200;
	protected static final int CHAR_HEIGHT = 150;
	
	public int standLength = 6;
	public int walkLength = 8;
	
	protected static final int VARIANT = 6;
	protected static int STAND_MAX_COUNT = VARIANT*5;
	protected static int WALK_MAX_COUNT = VARIANT*7;

	private String[] Stand = new String[standLength];
	private String[] Walk = new String[walkLength];

	private GreenfootImage[] raptorStand = new GreenfootImage[Stand.length];
	private GreenfootImage[] raptorWalk = new GreenfootImage[Walk.length];
	
	public Player(){
		
	}

}
