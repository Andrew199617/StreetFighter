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
	
	public Button (String text,int fontsize){
		
		Color fontColor = Color.WHITE; 
        Color bgColor = new Color(0, 0, 0, 0); 
        GreenfootImage txtImg = new GreenfootImage(""+text+"", fontsize, fontColor, bgColor);
        // create the base image
        GreenfootImage img = new GreenfootImage(txtImg.getWidth()+20, txtImg.getHeight()+10);
        bgColor = Color.BLACK; 
        img.setColor(bgColor);
        img.fill();
        
        img.drawImage(txtImg, 10, 5);
        setImage(img);
	}
	
}
