package streetFighterSimulation;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Background extends Actor {

	public Background (String s){
		GreenfootImage image = new GreenfootImage(s);
		image.scale(1440, 810);
		this.setImage(image);
		
	}
}
