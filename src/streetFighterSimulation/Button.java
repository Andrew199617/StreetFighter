package streetFighterSimulation;

import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Button extends Actor{

	public Button (String s) {
		GreenfootImage image = new GreenfootImage(s);
		GreenfootImage box = new GreenfootImage(image.getWidth()+ 10, image.getHeight() + 10);
		box.setColor(Color.BLACK);
		box.fill();
		box.drawImage(image, 5, 5);
		this.setImage(box);
	}
	
	
}
