package charcter;

import java.util.ArrayList;
import java.util.List;

import interfaces.Player_Status;

public class Dragon extends Player implements Player_Status{
	
	private int HP;
	private Status stat;
	private Status[] bars;
	private int x = 30;
	private int y = 30;
	
	public Dragon(){
		super (6, 8, 7, "w", Character.DRAGON);
	}
	public void act(){
		
	}
	@Override
	public void healthDisplay(int health){
		for(int i = 0; i < health; i++){
			stat = new Status();
			bars[i] = stat;
			getWorld().addObject(stat, x, y);
			x += 20;
		}
		HP--;
	}
	@Override
	public void setHealth(int health) {
		HP = health;
		bars = new Status[HP];
	}
	@Override
	public int getHealth() {
		return HP;
	}
	
	

}
