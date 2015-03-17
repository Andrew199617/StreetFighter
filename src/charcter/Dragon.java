package charcter;

import interfaces.Player_Status;
import enums.Character;

public class Dragon extends Player implements Player_Status{

	public HealthBar stat;
	public HealthBar[] bars;
	private int x = 30;
	private int y = 30;

	private static final int STAND = 6;
	private static final int WALK = 8;
	private static final int ATTACK = 7;
	private static final int RANGE = 10;
	private static final String JUMP = "w";

	public Dragon(){
		super (STAND, WALK, ATTACK, RANGE, JUMP, Character.DRAGON);
	}
	public void act(){
		if(matchHasntEnded){
			facePlayer();
		}
		if(playerRecentlyGotHit){
			gotHit(dmg);
		}
		determineIfMatchHasBeenWon();
		//fight();
	}
	@Override
	public void healthDisplay(int health){
		bars = new HealthBar[health];
		for(int i = 0; i < health; i++){
			stat = new HealthBar();
			bars[i] = stat;
			getWorld().addObject(stat, x, y);
			x += 20;
		}
		health--;
	}
	@Override
	public void setHealth(int health) {
		this.health = health;
		bars = new HealthBar[health];
	}
	@Override
	public int getHealth() {
		return health;
	}
	public void gotHit(int dmg) {
		playerRecentlyGotHit = false;
		for(int i = 0; i < dmg;i++){
			try{
				getWorld().removeObject(bars[health-1]);
				health --;
				lostHP = true;
			}catch (ArrayIndexOutOfBoundsException array){
				System.out.println("TOO MANY SHOTS");
			}
		}
	}


}
