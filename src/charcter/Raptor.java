package charcter;

import java.util.List;

import charcter.Player.Face;
import enums.ScaleOfScreen;
import interfaces.Player_Status;
import enums.Character;

public class Raptor extends Player implements Player_Status{

	public HealthBar stat;
	public HealthBar[] bars;
	private int x = ScaleOfScreen.WIDTH.getNum()-30;
	private int y = 30;

	private static final int STAND = 6;
	private static final int WALK = 8;
	private static final int ATTACK = 6;
	private static final int RANGE = 8;
	private static final String JUMP = "i";

	public Raptor(){
		super (STAND, WALK, ATTACK, RANGE, JUMP, Character.RAPTOR);
	}
	public void act(){
		if(matchHasntEnded){
			facePlayer();
		}
		if(playerRecentlyGotHit){
			gotHit(dmg);
		}
		//timeBetweenHits();
		determineIfMatchHasBeenWon();
		fight();
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
	@Override
	public void healthDisplay(int health){ 
		this.bars = new HealthBar[health];
		for(int i = 0; i < health; i++){
			stat = new HealthBar();
			bars[i] = stat;
			getWorld().addObject(stat, x, y);
			x -= 20;
		}
		health--;
	}
	@Override
	public void setHealth(int i) {
		health = i;
		bars = new HealthBar[health];
	}
	@Override
	public int getHealth() {
		return health;
	}
	@Override
	protected boolean notCloseEnoughToHitOtherPlayerOnRightSide(
			List<Player> otherPlayer) {
		boolean result = false;
		for(Player op: otherPlayer){
			result = op.getX() - CHAR_WIDTH/4*3 >= getX() && face == Face.RIGHT;
		}
		return result;
	}
	@Override
	protected boolean notCloseEnoughToHitOtherPlayerOnLeftSide(
			List<Player> otherPlayer) {
		boolean result = false;
		for(Player op: otherPlayer){
			result = op.getX() + CHAR_WIDTH/4*3 <= getX() && face == Face.LEFT;
		}
		return result;
	}
	@Override
	protected void doRangedAtack() {
		rangeAnimate = true;
	}
	@Override
	protected boolean onRightSide() {
		return  face == Face.RIGHT;
	}
	@Override
	protected boolean onLeftSide() {
		return face == Face.LEFT;
	}
}
