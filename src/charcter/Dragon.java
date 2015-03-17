package charcter;

import java.util.List;

import charcter.Player.Face;
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
		//timeBetweenHits();
		determineIfMatchHasBeenWon();
		fight();
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
			getWorld().removeObject(bars[health-1]);
			health --;
			lostHP = true;
		}
	}
	@Override
	protected boolean notCloseEnoughToHitOtherPlayerOnRightSide(List<Player> otherPlayer) {
		boolean result = false;
		for(Player op: otherPlayer){
			result = op.getX() + CHAR_WIDTH/4*3 <= getX() && face == Face.LEFT;
		}
		return result;
	}
	@Override
	protected boolean notCloseEnoughToHitOtherPlayerOnLeftSide(
			List<Player> otherPlayer) {
		boolean result = false;
		for(Player op: otherPlayer){
			result = op.getX() - CHAR_WIDTH/4*3 >= getX() && face == Face.RIGHT;
		}
		return result;
	}
	@Override
	protected void doRangedAtack() {
		rangeAnimate = true;
	}
	@Override
	protected boolean onRightSide() {
		return face == Face.LEFT;
	}
	@Override
	protected boolean onLeftSide() {
		return face == Face.RIGHT;
	}
	

}
