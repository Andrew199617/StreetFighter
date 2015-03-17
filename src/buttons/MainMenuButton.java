package buttons;

import sounds.Music;
import streetFighterSimulation.ChooseMaps;
import swing.Swing;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;

public class MainMenuButton extends Button {
	
	String words_On_The_Button = "";
	
	public MainMenuButton(String s, int i) {
		super(s,i);
		this.words_On_The_Button = s;
	}
	
	public void act(){
		if(Greenfoot.mouseClicked(this))
		determine_What_To_Do_When_Clicked();
		
	}

	private void determine_What_To_Do_When_Clicked() {
		switch(words_On_The_Button){
		case "CPU VS CPU":
			Music.Tempting_Secrets.stop();
			Greenfoot.setWorld(new ChooseMaps());
			break;
		case "Give Up":
//			Greenfoot.setWorld(new ChooseMaps());
			break;
		case "Options":
//			Greenfoot.setWorld(new ChooseMaps());
			Swing.swingWindow();
			break;
		}
		
	}

}
