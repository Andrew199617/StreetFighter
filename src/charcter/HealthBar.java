package charcter;

import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class HealthBar extends Actor{
	
	private GreenfootImage rect;
	
	public HealthBar(){
		rect = getImage();
		rect.scale(rect.getWidth(), rect.getHeight()*2);
        setImage(rect);
        getImage().setColor(Color.RED);
		getImage().fill();
	}
	

}
