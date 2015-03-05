package streetFighterSimulation;

import greenfoot.Greenfoot;

public class MapButton extends Button {

	String nameOfMap = "";
	
	public MapButton(String s) {
		super(s);
		nameOfMap = s;
	}

	public void act (){
		DetermineWhatToDoWhenClicked();
	}

	private void DetermineWhatToDoWhenClicked() {
		if(Greenfoot.mouseClicked(this)){
			Greenfoot.setWorld(new Stage(1440,810,1,this));
		}
	}
	
}
