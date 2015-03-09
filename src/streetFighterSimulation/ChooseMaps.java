package streetFighterSimulation;

import sounds.Music;
import sounds.SoundClips;
import charcter.Player;
import buttons.MapButton;
import enums.ScaleOfScreen;
import greenfoot.World;

public class ChooseMaps extends World {
	
	MapButtonLocation[][] mapButtonLocations = new MapButtonLocation[2][2];
	
	public ChooseMaps() {
		super(ScaleOfScreen.WIDTH.getNum(), ScaleOfScreen.HEIGHT.getNum(), 1);
		this.addObject(new Background("image/Map_1.png"), ScaleOfScreen.WIDTH.getNum()/2, ScaleOfScreen.HEIGHT.getNum()/2);
		
		AddMapChoices();
		playAndStopMusic();
		
	}

	private void playAndStopMusic() {
		SoundClips.Choose_Your_Map_SoundCLip.play();
		Music.TheCurtainRises.setVolume(60);
		Music.TheCurtainRises.playLoop();
	}

	private void AddMapChoices() {
		
		createArrayWithMapButtonLocations();
		
		this.addObject(new MapButton("image/Dragon_Stage.gif"), mapButtonLocations[0][0].getX(), mapButtonLocations[0][0].getY());
		this.addObject(new MapButton("image/Forest-0_Stage.gif"), mapButtonLocations[0][1].getX(), mapButtonLocations[0][1].getY());
		this.addObject(new MapButton("image/Forest-1_Stage.gif"), mapButtonLocations[1][0].getX(), mapButtonLocations[1][0].getY());
		this.addObject(new MapButton("image/Temple_Stage.gif"), mapButtonLocations[1][1].getX(), mapButtonLocations[1][1].getY());
	}

	private void createArrayWithMapButtonLocations() {
		
		int x = ScaleOfScreen.WIDTH.getNum()/4;
		int y = ScaleOfScreen.HEIGHT.getNum()/4;
		for(int i = 0;i < mapButtonLocations.length;i++){
			for(int i2 = 0; i2 < mapButtonLocations.length;i2++){
				mapButtonLocations[i][i2] = new MapButtonLocation(x,y);
				x += ScaleOfScreen.WIDTH.getNum()/2;
			}
			y += ScaleOfScreen.HEIGHT.getNum()/2;
			x = ScaleOfScreen.WIDTH.getNum()/4;
		}
		
	}
}
