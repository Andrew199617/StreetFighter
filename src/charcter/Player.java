package charcter;

import interfaces.Player_Status;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import streetFighterSimulation.Stage;
import swing.ReadWithScanner;
import enums.ScaleOfScreen;
import enums.Character;
import enums.Maps;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class Player extends Actor implements Player_Status{

	private static final int HOW_LONG_BEFORE_BACKING_AWAY = 1200;
	private static final int HOW_LONG_TO_WAIT_BEFORE_ATTACKING_AGAIN  = 55;
	protected static final int HIT_DELAY = 40;
	protected static final int HIT_ANIMATE = 10;

	protected int health;
	protected int dmg;
	protected int speed;
	protected int count = 0;

	protected static final int CHAR_WIDTH = 200;
	protected static final int CHAR_HEIGHT = 150;

	protected int standLength;
	protected int walkLength;
	protected int attackLength;
	protected int rangeLength;

	protected static final int VARIANT = 5;
	protected int STAND_MAX_COUNT;
	protected int STAND_MIN_COUNT = 0;
	protected int WALK_MAX_COUNT;
	protected int WALK_MIN_COUNT = 0;
	protected int ATTACK_MAX_COUNT;
	protected int ATTACK_MIN_COUNT = 0;
	protected int RANGE_MAX_COUNT;
	protected int RANGE_MIN_COUNT;

	public static int FLOOR;

	protected String jumpS;
	protected GreenfootImage[] charStand;
	protected GreenfootImage[] charWalk;
	protected GreenfootImage[] charAttack;
	protected GreenfootImage[] charRange;
	protected GreenfootImage[] jumpImg = new GreenfootImage[2];
	protected GreenfootImage[] winImg = new GreenfootImage[2];
	protected GreenfootImage[] lostImg = new GreenfootImage[2];
	protected GreenfootImage[] hitImg = new GreenfootImage[2];

	protected enum Face {RIGHT, LEFT};
	protected Face face;
	protected Character charType;

	protected int animate = 0;
	protected static final int MAX_JUMP = 20;
	protected static final int JUMP_SPEED = 15;
	protected boolean maxedJump = false;
	protected boolean jumped = false;

	protected Actor playerTouch;

	protected String[] facing = new String[2];
	protected String[] actor = new String[2];
	int[] faceSpeed = new int[2];
	protected boolean matchHasntEnded = true;
	public boolean playerRecentlyGotHit = false;
	protected boolean readyHit = true;
	public boolean lostHP = false;
	protected boolean fightAnimate = false;
	protected boolean rangeAnimate = false;
	protected int hitTimer = 0;
	private boolean moveRight = false;
	private boolean moveLeft = false;
	private boolean doAJump = false;
	private Random rand = new Random();
	private boolean wait = false;
	private int waitTimer = 0;
	private int waitToHitAgainTimer = HOW_LONG_TO_WAIT_BEFORE_ATTACKING_AGAIN + 1 ;

	protected Projectile shoot;
	private boolean tryToJump;
	private int tryToJumpTimer;
	private int damageTaken;
	private int damage_Taken_Timer_To_Know_When_To_Back_Away;
	private boolean doingRangedAttack = false;
	protected int properShotCount;
	protected int shotAnimate;

	Player(int stand, int walk, int attack, int range, String jump, Character charType){
		jumpS = jump;
		useSwing(charType);
		rangeLength = range;
		attackLength = attack;
		standLength = stand;
		walkLength = walk;
		setCharType(charType);
		charAttack = new GreenfootImage[attack*2];
		charStand = new GreenfootImage[stand*2];
		charWalk = new GreenfootImage[walk*2];
		charRange = new GreenfootImage[range*2];
		populateCharImg(charType);
		FLOOR = getFloor(Stage.map);
	}
	public int getFloor(Maps m){
		if(m == Maps.DRAGON){
			return (ScaleOfScreen.HEIGHT.getNum()/2)+ScaleOfScreen.HEIGHT.getNum()/5;
		}else {
			return Stage.WORLD_HEIGHT-(Stage.WORLD_HEIGHT/7);
		}
	}
	public void useSwing(Character charType){
		switch(charType){
		case DRAGON:
			health = ReadWithScanner.giveValues(0);
			speed = ReadWithScanner.giveValues(1);
			dmg = ReadWithScanner.giveValues(2);
			break;
		case RAPTOR:
			health = ReadWithScanner.giveValues(3);
			speed = ReadWithScanner.giveValues(4);
			dmg = ReadWithScanner.giveValues(5);
			break;
		}
		
	}
	
	protected void setCharType(Character charType){
		this.charType = charType;
	}

	protected Character getCharType(){
		return charType;
	}

	protected void facePlayer(){
		List<Player> players = getObjectsInRange(ScaleOfScreen.WIDTH.getNum(), Player.class);
		for(Player p:players){
			face = (p.getX()<=getX())?
					Face.LEFT:
						Face.RIGHT;
			animatePlayer(faceThat(face, charType));
		}

	}

	protected void animatePlayer(int i){
		if(lostHP){
			animate++;
			if(animate<HIT_ANIMATE){
				if(face == Face.RIGHT){
					setImage(hitImg[0]);
				}
				else{
					setImage(hitImg[1]);
				}

			}else{
				animate = 0;
				lostHP = false;
			}
		}else if(fightAnimate && waitToHitAgainTimer > HOW_LONG_TO_WAIT_BEFORE_ATTACKING_AGAIN && !rangeAnimate){

			if(count%VARIANT==0 && count<ATTACK_MAX_COUNT){
				setImage(charAttack[count/VARIANT]);
			}
			if(count<ATTACK_MAX_COUNT+(VARIANT-1)){
				count++;
			}else{
				hitOtherPlayer(1);
				fightAnimate=false;
				count = ATTACK_MIN_COUNT;
				waitToHitAgainTimer = 0;
			}
			fall();
		}else if(rangeAnimate && waitToHitAgainTimer > HOW_LONG_TO_WAIT_BEFORE_ATTACKING_AGAIN ){
			int properShotCount = 3;
			jumped = false;
			doingRangedAttack = true;
			if(count%VARIANT==0 && count<RANGE_MAX_COUNT){
				setImage(charRange[count/VARIANT]);
				if(count/VARIANT == (RANGE_MIN_COUNT/VARIANT)+properShotCount){
					shoot = new Projectile(charType, shotAnimate);
					getWorld().addObject(shoot, getX() + 10, getY()-15);
					shoot.fire();
				}
			}
			if(count<RANGE_MAX_COUNT+(VARIANT-1)){
				count++;
			}else{
				rangeAnimate=false;
				count = RANGE_MIN_COUNT;
				waitToHitAgainTimer = 0;
				doingRangedAttack = false;
			}
			fall();
		}
		if(!lostHP && !fightAnimate && !rangeAnimate){
			if(Greenfoot.isKeyDown(actor[0])){
				fightAnimate = true;
				waitToHitAgainTimer = HOW_LONG_TO_WAIT_BEFORE_ATTACKING_AGAIN +1;
				count = ATTACK_MIN_COUNT;
			}else if(Greenfoot.isKeyDown(actor[1])){
				rangeAnimate = true;
				waitToHitAgainTimer = HOW_LONG_TO_WAIT_BEFORE_ATTACKING_AGAIN +1;
				count = RANGE_MIN_COUNT;
			}else if(Greenfoot.isKeyDown(facing[0]) || moveRight){
				setLocation(getX() + faceSpeed[0], getY());
				if(Greenfoot.isKeyDown(jumpS)|| doAJump){
					jump(i);
				}else if(count%VARIANT==0 && count<WALK_MAX_COUNT){
					setImage(charWalk[count/VARIANT]);
					jumped = false;
				}
				if(count<WALK_MAX_COUNT+(VARIANT-1)){
					count++;
				}else {
					count = WALK_MIN_COUNT;
				}
				fall();
			}else if(Greenfoot.isKeyDown(facing[1]) || moveLeft){
				setLocation(getX() + faceSpeed[1], getY());
				if(Greenfoot.isKeyDown(jumpS)|| doAJump){
					jump(i);
				}else if(count%VARIANT==0 && count<WALK_MAX_COUNT){
					setImage(charWalk[count/VARIANT]);
					jumped = false;
				}
				if(count>(WALK_MIN_COUNT)){
					count--;
				}else count = WALK_MAX_COUNT;

				fall();
			}else {//*/
				if(Greenfoot.isKeyDown(jumpS) || doAJump ){
					jump(i);
				}else if(count%VARIANT==0 && count < STAND_MAX_COUNT){
					setImage(charStand[count/VARIANT]);
					jumped = false;
				}
				if(count<STAND_MAX_COUNT+(VARIANT-1)){
					count++;
				}else {
					count = STAND_MIN_COUNT;
				}
				fall();
			}
		}
	}
	public void fall(){
		if(getY()<FLOOR && !jumped){
			setLocation(getX(), getY()+JUMP_SPEED);
		}else if(getY() >= FLOOR && !jumped){
			animate = 0;
			maxedJump = false;
		}
	}
	public void jump(int i){
		jumped = true;
		fightAnimate = false;
		if(animate<MAX_JUMP && !maxedJump){
			setLocation(getX(), getY()-JUMP_SPEED);
			setImage(jumpImg[i]);
			animate++;
		}else if(animate>0){
			if(getY()<FLOOR){
				setLocation(getX(), getY() + JUMP_SPEED);
				setImage(charWalk[0]);
			}
			maxedJump=true;
			animate--;
		}else {
			maxedJump = false;
			doAJump = false;
		}

	}

	protected int faceThat(Face f, Character c){
		int face = 0;
		switch(c){
		case DRAGON:
			facing[0] = "d";
			facing[1] = "a";
			actor[0] ="e";
			actor[1] ="q";
			properShotCount = 3;
			shotAnimate = 3;
			switch(f){
			case LEFT:
				faceSpeed[0] = speed/2;
				faceSpeed[1] = -speed;
				WALK_MIN_COUNT = (walkLength*VARIANT);
				WALK_MAX_COUNT = ((walkLength*VARIANT)*2)-1;
				STAND_MIN_COUNT = (standLength*VARIANT);
				STAND_MAX_COUNT = ((standLength*VARIANT)*2)-1;
				ATTACK_MIN_COUNT = (attackLength*VARIANT);
				ATTACK_MAX_COUNT = ((attackLength*VARIANT)*2)-1;
				RANGE_MIN_COUNT = (rangeLength*VARIANT);
				RANGE_MAX_COUNT = ((rangeLength*VARIANT)*2)-1;
				face = 1;
				break;
			case RIGHT:
				faceSpeed[0] = speed;
				faceSpeed[1] = -speed/2;
				WALK_MIN_COUNT = 0;
				WALK_MAX_COUNT = (walkLength*VARIANT)-1;
				STAND_MIN_COUNT = 0;
				STAND_MAX_COUNT = (standLength*VARIANT)-1;
				ATTACK_MIN_COUNT = 0;
				ATTACK_MAX_COUNT = (attackLength*VARIANT)-1;
				RANGE_MIN_COUNT = 0;
				RANGE_MAX_COUNT = (rangeLength*VARIANT)-1;
				face = 0;
				break;
			}
			break;
		case RAPTOR:
			facing[0] = "j";
			facing[1] = "l";
			actor[0] = "u";
			actor[1] = "o";
			properShotCount = 3;
			shotAnimate = 4;
			switch(f){
			case LEFT:
				faceSpeed[0] = -speed;
				faceSpeed[1] = speed/2;
				WALK_MIN_COUNT = 0;
				WALK_MAX_COUNT = (walkLength*VARIANT)-1;
				STAND_MIN_COUNT = 0;
				STAND_MAX_COUNT = (standLength*VARIANT)-1;
				ATTACK_MIN_COUNT = 0;
				ATTACK_MAX_COUNT = (attackLength*VARIANT)-1;
				RANGE_MIN_COUNT = 0;
				RANGE_MAX_COUNT = (rangeLength*VARIANT)-1;
				face = 0;
				break;
			case RIGHT:
				faceSpeed[0] = -speed/2;
				faceSpeed[1] = speed;
				WALK_MIN_COUNT = (walkLength*VARIANT);
				WALK_MAX_COUNT = ((walkLength*VARIANT)*2)-1;
				STAND_MIN_COUNT = (standLength*VARIANT);
				STAND_MAX_COUNT = ((standLength*VARIANT)*2)-1;
				ATTACK_MIN_COUNT = (attackLength*VARIANT);
				ATTACK_MAX_COUNT = ((attackLength*VARIANT)*2)-1;
				RANGE_MIN_COUNT = (rangeLength*VARIANT);
				RANGE_MAX_COUNT = ((rangeLength*VARIANT)*2)-1;
				face = 1;
				break;
			}
			break;
		}
		return face;
	}

	protected void populateCharImg(Character charType){
		switch(charType){
		case DRAGON:
			for(int i = 0; i < standLength*2; i++){
				charStand[i] = new GreenfootImage("image/Dragon_Stand-" + i + ".png");
				charStand[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < walkLength*2; i++){
				charWalk[i] = new GreenfootImage("image/Dragon_Walk-" + i + ".png");
				charWalk[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < attackLength*2; i++){
				charAttack[i] = new GreenfootImage("image/Dragon_Claw-" + i + ".png");
				charAttack[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < jumpImg.length; i++){
				jumpImg[i] = new GreenfootImage("image/Dragon_Jump-" + i + ".png");
				jumpImg[i].scale(CHAR_WIDTH-(CHAR_WIDTH/5), CHAR_HEIGHT);
			}
			for (int i = 0; i < winImg.length; i++) {
				winImg[i] = new GreenfootImage("image/Dragon_Win-" + i + ".png");
				winImg[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for (int i = 0; i < lostImg.length; i++) {
				lostImg[i] = new GreenfootImage("image/Dragon_Lost-" + i + ".png");
				lostImg[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for (int i = 0; i < hitImg.length; i++) {
				hitImg[i] = new GreenfootImage("image/Dragon_Hit-" + i + ".png");
				hitImg[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < rangeLength*2; i++){
				charRange[i] = new GreenfootImage("image/Dragon_Range-" + i + ".png");
				charRange[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			break;
		case RAPTOR:
			for(int i = 0; i < standLength*2; i++){
				charStand[i] = new GreenfootImage("image/Raptor_Stand-" + i + ".png");
				charStand[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < walkLength*2; i++){
				charWalk[i] = new GreenfootImage("image/Raptor_Walk-" + i + ".png");
				charWalk[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < jumpImg.length; i++){
				jumpImg[i] = new GreenfootImage("image/Raptor_Jump-" + i + ".png");
				jumpImg[i].scale(CHAR_WIDTH-(CHAR_WIDTH/5), CHAR_HEIGHT);
			}
			for (int i = 0; i < winImg.length; i++) {
				winImg[i] = new GreenfootImage("image/Raptor_Win-" + i + ".png");
				winImg[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for (int i = 0; i < lostImg.length; i++) {
				lostImg[i] = new GreenfootImage("image/Raptor_Lost-" + i + ".png");
				lostImg[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for (int i = 0; i < hitImg.length; i++) {
				hitImg[i] = new GreenfootImage("image/Raptor_Hit-" + i + ".png");
				hitImg[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < attackLength*2; i++){
				charAttack[i] = new GreenfootImage("image/Raptor_Basic-" + i + ".png");
				charAttack[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < rangeLength*2; i++){
				charRange[i] = new GreenfootImage("image/Raptor_Range-" + i + ".png");
				charRange[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			break;
		}
	}

	public void setPlayerRecentlyGotHit(boolean playerRecentlyGotHit) {
		this.playerRecentlyGotHit = playerRecentlyGotHit;
		readyHit = false;
	}//*/

	protected void fight(){
		List<Player> otherPlayer = getObjectsInRange(ScaleOfScreen.WIDTH.getNum(), Player.class);
		moveRight = false;
		moveLeft = false;
		waitTimer ++;
		waitToHitAgainTimer  ++;
		damage_Taken_Timer_To_Know_When_To_Back_Away ++;
		int stopSoCPUWontBeAllKnowing = rand.nextInt(30);
		int ocassionalStop = rand.nextInt(80);
		int doRangedAttack = rand.nextInt(30);

		if(stopSoCPUWontBeAllKnowing == 0 || wait || tryToJump){
			setTimeBeforeplayerWillGetHitByProjectile();
			if(otherPlayerDidRangedAttack(otherPlayer)){
				dodgeAttack();
			}
		}

		if (ocassionalStop == 0 || wait && !doingRangedAttack){
			waitForOtherPlayerToDoSomething(otherPlayer);
		}

		else if(doRangedAttack == 0 && notNextToPlayer(otherPlayer) && notInAir() || doingRangedAttack){
			doRangedAtack();
		}

		else if(tookAlotOfDamage()){
			backUpAndTryToDoRangedAttack(otherPlayer);
		}

		else if(closeEnoughToHitOtherPlayer(otherPlayer) && notInAir()){
			hitOtherPlayer();
		}

		else if(notCloseEnoughToHitOtherPlayerOnLeftSide(otherPlayer)){
			moveTowardsOtherPlayerFromLeft();
		}

		else if(notCloseEnoughToHitOtherPlayerOnRightSide(otherPlayer)){
			moveTowardsOtherPlayerFromRight();
		}


	}

	private void backUpAndTryToDoRangedAttack(List<Player> otherPlayer) {
		int howFarBackToGo = rand.nextInt(250);
		int howFarbackToGoAndMinimum = (50 + howFarBackToGo);
		if(onLeftSide()){
			if(getX() > ScaleOfScreen.WIDTH.getNum() - howFarbackToGoAndMinimum || getX() < howFarbackToGoAndMinimum){
				damage_Taken_Timer_To_Know_When_To_Back_Away = HOW_LONG_BEFORE_BACKING_AWAY +1;
			}
			else {
				moveLeft = true;
			}
		}
		else if(onRightSide()){
			if(getX() > ScaleOfScreen.WIDTH.getNum() -howFarbackToGoAndMinimum || getX() < howFarbackToGoAndMinimum){
				damage_Taken_Timer_To_Know_When_To_Back_Away = HOW_LONG_BEFORE_BACKING_AWAY +1;
			}
			else {
				moveRight = true;
			}
		}

	}

	protected abstract boolean onRightSide();

	protected abstract boolean onLeftSide();

	private boolean tookAlotOfDamage() {
		if(damage_Taken_Timer_To_Know_When_To_Back_Away >= HOW_LONG_BEFORE_BACKING_AWAY){
			damage_Taken_Timer_To_Know_When_To_Back_Away = 0;
			damageTaken = 0;
		}
		if(damageTaken > 3 && damage_Taken_Timer_To_Know_When_To_Back_Away < HOW_LONG_BEFORE_BACKING_AWAY){
			return true;
		}

		return false;
	}

	protected abstract void doRangedAtack();

	private boolean notNextToPlayer(List<Player> otherPlayer) {
		boolean result = false;
		for(Player op: otherPlayer){
			result = op.getX() + 250 <= getX() || op.getX() - 250 >= getX();
		}
		return result;
	}

	private boolean notInAir() {
		if(this.getY() < FLOOR - 3){
			return false;
		}
		return true;
	}

	private void dodgeAttack() {
		doAJump = true;
		tryToJump = false;
		tryToJumpTimer = 0;
	}

	private void setTimeBeforeplayerWillGetHitByProjectile() {
		tryToJump = true;
		tryToJumpTimer ++;
		if(tryToJumpTimer > 15){
			tryToJump = false;
			tryToJumpTimer = 0;
		}
	}

	private void waitForOtherPlayerToDoSomething(List<Player> otherPlayer) {
		if(!wait){
			wait = true;
			waitTimer = 0;
		}
		else if(waitTimer > 50){
			wait = false;
		}
//		else if(closeEnoughToHitOtherPlayer(otherPlayer) && notInAir()){
//			hitOtherPlayer();
//		}

	}

	private boolean otherPlayerDidRangedAttack(List<Player> otherPlayer) {
		@SuppressWarnings("unchecked")
		List<Projectile> projectiles = getObjectsInRange(250, Projectile.class);
		boolean result = false;

		for (Projectile p: projectiles) {
			if(p.getCurrentChar() != charType){
				result = true;
			}
		}

		return result;
	}

	protected abstract boolean notCloseEnoughToHitOtherPlayerOnRightSide(List<Player> otherPlayer);

	private void moveTowardsOtherPlayerFromRight() {
		moveLeft = true;
	}

	private void moveTowardsOtherPlayerFromLeft() {
		moveRight = true;	
	}

	protected abstract boolean notCloseEnoughToHitOtherPlayerOnLeftSide(List<Player> otherPlayer);

	private boolean closeEnoughToHitOtherPlayer(List<Player> otherPlayer) {
		boolean result = false;
		for(Player op: otherPlayer){
			result = op.getX() + CHAR_WIDTH/4*3 >= getX() && op.getX() - CHAR_WIDTH/4*3 <= getX();
		}
		return result;
	}

	protected void hitOtherPlayer() {
		fightAnimate = true;
	}

	protected void hitOtherPlayer(int dmg){
		@SuppressWarnings("unchecked")
		List<Player> otherPlayer = getIntersectingObjects(Player.class);

		for (Player otherplayer: otherPlayer) {
			otherplayer.gotHit(dmg);
			otherplayer.damageTaken += dmg;
		}

	}

	public abstract void gotHit(int dmg);

	protected void lostMatch(){
		matchHasntEnded = false;
		if(face == Face.RIGHT){
			setImage(lostImg[0]);
		}
		else {
			setImage(lostImg[1]);
		}
	}

	protected void determineIfMatchHasBeenWon(){
		if(enemyHealthIsLessThanZero()){
			wonMatch();
		}
	}

	private boolean enemyHealthIsLessThanZero() {
		boolean otherPlayersHealthislessThanZero = false;
		List<Player> otherPlayer = getObjectsInRange(ScaleOfScreen.WIDTH.getNum(),Player.class);
		for(Player op: otherPlayer){
			if(op.health <= 0){
				otherPlayersHealthislessThanZero = true;
				op.lostMatch();
			}
		}
		return otherPlayersHealthislessThanZero;
	}

	protected void wonMatch(){
		matchHasntEnded = false;
		if(face == Face.RIGHT){
			setImage(winImg[0]);
		}
		else {
			setImage(winImg[1]);
		}
	}
	@Override
	public void healthDisplay(int i) {
		// TODO Auto-generated method stub

	}
	@Override
	public void setHealth(int i) {
		// TODO Auto-generated method stub

	}
	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}
}
