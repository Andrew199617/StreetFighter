package streetFighterSimulation;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import charcter.Player;

public class Raptor extends Player{

	protected int count = 0;

	protected static final int VARIANT = 6;
	protected static int STAND_MAX_COUNT = VARIANT*5;
	protected static int WALK_MAX_COUNT = VARIANT*7;

	private String[] Stand = new String[6];
	private String[] Walk = new String[8];

	private GreenfootImage[] raptorStand = new GreenfootImage[Stand.length];
	private GreenfootImage[] raptorWalk = new GreenfootImage[Walk.length];

	Raptor(){
		setImage(raptorStand[0]);
		populateCharImg();
	}
	public void act(){
		animate();
	}
	protected void animate(){
		if(Greenfoot.isKeyDown("left")){
			setLocation(getX()-5, getY());
			if(count%VARIANT==0){
				setImage(raptorWalk[count/VARIANT]);
			}
			if(count<WALK_MAX_COUNT+(VARIANT-1)){
				count++;
			}else count = 0;
		}else if(Greenfoot.isKeyDown("right")){
			setLocation(getX()+3, getY());
			if(count%VARIANT==0){
				setImage(raptorWalk[count/VARIANT]);
			}
			if(count>-(VARIANT-1)){
				count--;
			}else count = WALK_MAX_COUNT;
		}else {
			if(count%VARIANT==0 && count < STAND_MAX_COUNT){
				setImage(raptorStand[count/VARIANT]);
			}
			if(count<STAND_MAX_COUNT+(VARIANT-1)){
				count++;
			}else count = 0;
		}

	}
	protected void populateCharImg(){
		for(int i = 0; i < Stand.length; i++){
			Stand[i] = "image/Raptor_Stand-" + i + ".png";
			raptorStand[i] = new GreenfootImage(Stand[i]);
			raptorStand[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
		}
		for(int i = 0; i < Walk.length; i++){
			Walk[i] = "image/Raptor_Walk-" + i + ".png";
			raptorWalk[i] = new GreenfootImage(Walk[i]);
			raptorWalk[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
		}
	}

}
