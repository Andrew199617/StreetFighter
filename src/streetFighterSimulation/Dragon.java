package streetFighterSimulation;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Dragon extends Player{

	protected int count = 0;

	protected static final int CHAR_WIDTH = 200;
	protected static final int CHAR_HEIGHT = 150;

	protected static final int VARIANT = 6;
	protected static int STAND_MAX_COUNT = VARIANT*5;
	protected static int WALK_MAX_COUNT = VARIANT*6;

	private String[] Stand = new String[6];
	private String[] Walk = new String[7];

	private GreenfootImage[] dinoStand = new GreenfootImage[Stand.length];
	private GreenfootImage[] dragonWalk = new GreenfootImage[Walk.length];

	public Dragon(){
		setImage(dinoStand[count]);
		populateCharImg();
	}
	public void act(){
		animate();
	}
	protected void animate(){
		if(Greenfoot.isKeyDown("right")){
			setLocation(getX()+5, getY());
			if(count%VARIANT==0){
				setImage(dragonWalk[count/VARIANT]);
			}
			if(count<WALK_MAX_COUNT+(VARIANT-1)){
				count++;
			}else count = 0;
		}else if(Greenfoot.isKeyDown("left")){
			setLocation(getX()-3, getY());
			if(count%VARIANT==0){
				setImage(dragonWalk[count/VARIANT]);
			}
			if(count>-(VARIANT-1)){
				count--;
			}else count = WALK_MAX_COUNT;
		}else {
			if(count%VARIANT==0 && count < STAND_MAX_COUNT){
				setImage(dinoStand[count/VARIANT]);
			}
			if(count<STAND_MAX_COUNT+(VARIANT-1)){
				count++;
			}else count = 0;
		}

	}
	protected void populateCharImg(){
		for(int i = 0; i < Stand.length; i++){
			Stand[i] = "image/Dragon_Stand-" + i + ".png";
			dinoStand[i] = new GreenfootImage(Stand[i]);
			dinoStand[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
		}
		for(int i = 0; i < Walk.length; i++){
			Walk[i] = "image/Dragon_Walk-" + i + ".png";
			dragonWalk[i] = new GreenfootImage(Walk[i]);
			dragonWalk[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
		}
	}

}
