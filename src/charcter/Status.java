package charcter;

import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Status extends Actor{
	
	GreenfootImage rect;
	
	public Status(){
		rect = getImage();
		rect.scale(rect.getWidth(), rect.getHeight()*2);
        setImage(rect);
        getImage().setColor(Color.RED);
		getImage().fill();
	}
	

}
