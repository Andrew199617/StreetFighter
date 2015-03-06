package charcter;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Player extends Actor{

	private int count = 0;

	private static final int CHAR_WIDTH = 200;
	private static final int CHAR_HEIGHT = 150;

	private static final int VARIANT = 6;
	private static final int STAND_MAX_COUNT = VARIANT*5;
	private static final int WALK_MAX_COUNT = VARIANT*6;

	private String[] Stand = new String[6];
	private String[] Walk = new String[7];

	GreenfootImage[] dragonStand = new GreenfootImage[Stand.length];
	GreenfootImage[] dragonWalk = new GreenfootImage[Walk.length];

	public Player(){
		setImage(dragonStand[count]);
		populateCharImg();
	}
	public void act(){
		animate();
	}
	private void animate(){
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
				setImage(dragonStand[count/VARIANT]);
			}
			if(count<STAND_MAX_COUNT+(VARIANT-1)){
				count++;
			}else count = 0;
		}

	}
	private void populateCharImg(){
		for(int i = 0; i < Stand.length; i++){
			Stand[i] = "image/Dragon_Stand-" + i + ".png";
			dragonStand[i] = new GreenfootImage(Stand[i]);
			dragonStand[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
		}
		for(int i = 0; i < Walk.length; i++){
			Walk[i] = "image/Dragon_Walk-" + i + ".png";
			dragonWalk[i] = new GreenfootImage(Walk[i]);
			dragonWalk[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
		}
	}

}
