package charcter;

import java.util.List;

import enums.ScaleOfScreen;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Player extends Actor{

	protected int count = 0;

	protected static final int CHAR_WIDTH = 200;
	protected static final int CHAR_HEIGHT = 150;

	protected int standLength;
	protected int walkLength;

	protected static final int VARIANT = 6;
	protected int STAND_MAX_COUNT;
	protected int WALK_MAX_COUNT;

	protected String[] stand;
	protected String[] walk;
	protected GreenfootImage[] charStand;
	protected GreenfootImage[] charWalk;

	protected enum Face {RIGHT, LEFT};
	protected enum Character {DRAGON, RAPTOR};
	protected Face face = Face.RIGHT;

	Player(int stand, int walk, Character charType){
		standLength = stand;
		walkLength = walk;
		STAND_MAX_COUNT = VARIANT*(standLength-1);
		WALK_MAX_COUNT = VARIANT*(walkLength-1);
		this.stand = new String[stand];
		this.walk = new String[walk];
		charStand = new GreenfootImage[stand];
		charWalk = new GreenfootImage[walk];
		populateCharImg(charType);
	}
	public void act(){
		animate();
	}
	protected void facePlayer(){
		int x;
		List<Player> players = getObjectsInRange(100, Player.class);
		
		for(Player p:players){
			System.out.println("TEST");
		}
	}
	protected void animate(){
		if(Greenfoot.isKeyDown("right")){
			setLocation(getX()+5, getY());
			if(count%VARIANT==0){
				setImage(charWalk[count/VARIANT]);
			}
			if(count<WALK_MAX_COUNT+(VARIANT-1)){
				count++;
			}else count = 0;
		}else if(Greenfoot.isKeyDown("left")){
			setLocation(getX()-3, getY());
			if(count%VARIANT==0){
				setImage(charWalk[count/VARIANT]);
			}
			if(count>-(VARIANT-1)){
				count--;
			}else count = WALK_MAX_COUNT;
		}else {
			if(count%VARIANT==0 && count < STAND_MAX_COUNT){
				setImage(charStand[count/VARIANT]);
			}
			if(count<STAND_MAX_COUNT+(VARIANT-1)){
				count++;
			}else {
				count = 0;
			}
		}
	}
	protected void populateCharImg(Character charType){
		switch(charType){
		case DRAGON:
			for(int i = 0; i < standLength; i++){
				stand[i] = "image/Dragon_Stand-" + i + ".png";
				charStand[i] = new GreenfootImage(stand[i]);
				charStand[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < walkLength; i++){
				walk[i] = "image/Dragon_Walk-" + i + ".png";
				charWalk[i] = new GreenfootImage(walk[i]);
				charWalk[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			break;
		case RAPTOR:
			for(int i = 0; i < standLength; i++){
				stand[i] = "image/Raptor_Stand-" + i + ".png";
				charStand[i] = new GreenfootImage(stand[i]);
				charStand[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < walkLength; i++){
				walk[i] = "image/Raptor_Walk-" + i + ".png";
				charWalk[i] = new GreenfootImage(walk[i]);
				charWalk[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			break;
		}


	}



}
