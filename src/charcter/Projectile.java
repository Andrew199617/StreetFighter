package charcter;

import java.util.List;

import charcter.Player.Face;
import streetFighterSimulation.Stage;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import enums.Character;

public class Projectile extends Actor{

	private GreenfootImage[] shoot;
	private static final int WIDTH = 75;
	private static final int HEIGHT = 50;
	private int length;
	private static final int VARIANT = 7;
	private int MAX_COUNT;
	private int count = 0;
	private Character currentChar;
	private int rotateL = 180;
	private int rotateR = 0;

	public Projectile(Character character, int length){
		this.currentChar = character;
		this.length = length;
		MAX_COUNT = length*VARIANT;
		shoot = new GreenfootImage[length];
		populateFireImg(character);
	}
	public void act(){
		removeThis();
		animate();
		
	}
	private void removeThis(){
		List<Player> player = getIntersectingObjects(Player.class);
		if(getX() <= 2){
			getWorld().removeObject(this);
		}else if(getX() >= Stage.WORLD_WIDTH-1){
			getWorld().removeObject(this);
		}else if(player != null){
			for(Player p:player){
				if(p.getCharType()!= currentChar){
					p.playerRecentlyGotHit = true;
					p.lostHP = true;
					getWorld().removeObject(this);
				}
			}
		}
	}
	public void fire(){
		List<Player> player = getObjectsInRange(Stage.WORLD_WIDTH*2, Player.class);
		if(player!= null){
			for(Player p:player){
				if(p.getCharType() == currentChar){
					if(p.face == Face.LEFT){
						setRotation(rotateL);
					}else if(p.face == Face.RIGHT){
						setRotation(rotateR);
					}
				}
			}
		}
	}
	private void animate(){
		move(10);
		if(count%VARIANT==0 && count<MAX_COUNT){
			setImage(shoot[count/VARIANT]);
		}
		if(count<MAX_COUNT){
			count++;
		}else{
			count = 0;
		}
	}
	public void populateFireImg(Character character){
		if(character == Character.DRAGON){
			for(int i = 0; i < length; i++){
				shoot[i] = new GreenfootImage("image/Dragon_Fire-" + i + ".png");
				shoot[i].scale(WIDTH, HEIGHT);
			}
		}else if(character == Character.RAPTOR){
			for(int i = 0; i < length; i++){
				shoot[i] = new GreenfootImage("image/Raptor_Fire-" + i + ".png");
				shoot[i].scale(WIDTH, HEIGHT);
			}
		}

	}

}
