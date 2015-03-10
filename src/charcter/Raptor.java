package charcter;

import enums.ScaleOfScreen;
import interfaces.Player_Status;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Raptor extends Player implements Player_Status{
	
	private int HP;
	private Status stat;
	private Status[] bars;
	private int x = ScaleOfScreen.WIDTH.getNum()-30;
	private int y = 30;

	public Raptor(){
		super (6, 8, 0, "up", Character.RAPTOR);
	}
	@Override
	public void healthDisplay(int health){
		for(int i = 0; i < health; i++){
			stat = new Status();
			bars[i] = stat;
			getWorld().addObject(stat, x, y);
			x -= 20;
		}
		HP--;
	}
	@Override
	public void setHealth(int i) {
		HP = i;
		bars = new Status[HP];
	}

	@Override
	public int getHealth() {
		return HP;
	}
}
