package charcter;

import interfaces.Player_Status;

import java.util.List;

import enums.ScaleOfScreen;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class Player extends Actor implements Player_Status{

	protected static final int HIT_DELAY = 40;
	protected static final int HIT_ANIMATE = 10;

	protected int health = 25;
	protected int count = 0;

	protected static final int CHAR_WIDTH = 200;
	protected static final int CHAR_HEIGHT = 150;

	protected int standLength;
	protected int walkLength;
	protected int attackLength;

	protected static final int VARIANT = 5;
	protected int STAND_MAX_COUNT;
	protected int STAND_MIN_COUNT = 0;
	protected int WALK_MAX_COUNT;
	protected int WALK_MIN_COUNT = 0;
	protected int ATTACK_MAX_COUNT;
	protected int ATTACK_MIN_COUNT = 0;

	public static final int FLOOR = (ScaleOfScreen.HEIGHT.getNum()/2)+ScaleOfScreen.HEIGHT.getNum()/5;

	protected String jumpS;
	protected String[] stand;
	protected String[] walk;
	protected String[] attack;
	protected GreenfootImage[] charStand;
	protected GreenfootImage[] charWalk;
	protected GreenfootImage[] charAttack;
	protected GreenfootImage[] jumpImg = new GreenfootImage[2];
	protected GreenfootImage[] winImg = new GreenfootImage[2];
	protected GreenfootImage[] lostImg = new GreenfootImage[2];
	protected GreenfootImage[] hitImg = new GreenfootImage[2];

	protected enum Face {RIGHT, LEFT};
	protected enum Character {DRAGON, RAPTOR};
	protected enum Play {ONE, TWO};
	protected Face face;
	protected Character charType;

	protected int animate = 0;
	protected static final int MAX_JUMP = 20;
	protected static final int JUMP_SPEED = 15;
	protected boolean maxedJump = false;
	protected boolean jumped = false;

	protected Actor playerTouch;

	String[] facing = new String[2];
	String[] actor = new String[2];
	int[] faceSpeed = new int[2];
	protected boolean matchHasntEnded = true;
	protected boolean playerRecentlyGotHit = false;
	protected boolean readyHit = true;
	protected boolean lostHP = false;
	protected boolean fightAnimate = false;
	protected int hitTimer = 0;


	Player(int stand, int walk, int attack, String jump, Character charType){
		jumpS = jump;
		attackLength = attack;
		standLength = stand;
		walkLength = walk;
		this.charType = charType;
		STAND_MAX_COUNT = VARIANT*(standLength-1);
		WALK_MAX_COUNT = VARIANT*(walkLength-1);
		this.attack = new String[attack*2];
		this.stand = new String[stand*2];
		this.walk = new String[walk*2];
		charAttack = new GreenfootImage[attack*2];
		charStand = new GreenfootImage[stand*2];
		charWalk = new GreenfootImage[walk*2];
		populateCharImg(charType);
	}
	/*
	public void act(){
		if(matchHasntEnded){
			facePlayer();
		}
		timeBetweenHits();
		determineIfMatchHasBeenWon();
	}//*/
	/*
		if(playerRecentlyGotHit){
			hitTimer ++;
			if(hitTimer > HIT_DELAY){
				hitTimer = 0;
				readyHit = true;
			}
		}
	}//*/
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
		}else if(fightAnimate){
			
			if(count%VARIANT==0 && count<ATTACK_MAX_COUNT){
				setImage(charAttack[count/VARIANT]);
				jumped = false;
			}
			if(count<ATTACK_MAX_COUNT+(VARIANT-1)){
				count++;
			}else {
				hitOtherPlayer();
				fightAnimate=false;
				count = ATTACK_MIN_COUNT;
			}
		}
		if(!lostHP && !fightAnimate){
			if(Greenfoot.isKeyDown(actor[0])){
				fightAnimate = true;
				count = ATTACK_MIN_COUNT;
			}else if(Greenfoot.isKeyDown(facing[0])){
				setLocation(getX() + faceSpeed[0], getY());
				if(Greenfoot.isKeyDown(jumpS)){
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
			}else if(Greenfoot.isKeyDown(facing[1])){
				setLocation(getX() + faceSpeed[1], getY());
				if(Greenfoot.isKeyDown(jumpS)){
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
				if(Greenfoot.isKeyDown(jumpS)){
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
		}

	}
	protected int faceThat(Face f, Character c){
		int forward = 10;
		int back = 6;
		int face = 0;
		switch(c){
		case DRAGON:
			facing[0] = "d";
			facing[1] = "a";
			actor[0] ="e";
			switch(f){
			case LEFT:
				faceSpeed[0] = back;
				faceSpeed[1] = -forward;
				WALK_MIN_COUNT = (walkLength*VARIANT);
				WALK_MAX_COUNT = ((walkLength*VARIANT)*2)-1;
				STAND_MIN_COUNT = (standLength*VARIANT);
				STAND_MAX_COUNT = ((standLength*VARIANT)*2)-1;
				ATTACK_MIN_COUNT = (attackLength*VARIANT);
				ATTACK_MAX_COUNT = ((attackLength*VARIANT)*2)-1;
				face = 1;
				break;
			case RIGHT:
				faceSpeed[0] = forward;
				faceSpeed[1] = -back;
				WALK_MIN_COUNT = 0;
				WALK_MAX_COUNT = (walkLength*VARIANT)-1;
				STAND_MIN_COUNT = 0;
				STAND_MAX_COUNT = (standLength*VARIANT)-1;
				ATTACK_MIN_COUNT = 0;
				ATTACK_MAX_COUNT = (attackLength*VARIANT)-1;
				face = 0;
				break;
			}
			break;
		case RAPTOR:
			facing[0] = "left";
			facing[1] = "right";
			actor[0] = "shift";
			switch(f){
			case LEFT:
				faceSpeed[0] = -forward;
				faceSpeed[1] = back;
				WALK_MIN_COUNT = 0;
				WALK_MAX_COUNT = (walkLength*VARIANT)-1;
				STAND_MIN_COUNT = 0;
				STAND_MAX_COUNT = (standLength*VARIANT)-1;
				face = 0;
				break;
			case RIGHT:
				faceSpeed[0] = -back;
				faceSpeed[1] = forward;
				WALK_MIN_COUNT = (walkLength*VARIANT);
				WALK_MAX_COUNT = ((walkLength*VARIANT)*2)-1;
				STAND_MIN_COUNT = (standLength*VARIANT);
				STAND_MAX_COUNT = ((standLength*VARIANT)*2)-1;
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
				stand[i] = "image/Dragon_Stand-" + i + ".png";
				charStand[i] = new GreenfootImage(stand[i]);
				charStand[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < walkLength*2; i++){
				walk[i] = "image/Dragon_Walk-" + i + ".png";
				charWalk[i] = new GreenfootImage(walk[i]);
				charWalk[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < attackLength*2; i++){
				attack[i] = "image/Dragon_Claw-" + i + ".png";
				charAttack[i] = new GreenfootImage(attack[i]);
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
			for (int i = 0; i < hitImg .length; i++) {
				hitImg[i] = new GreenfootImage("image/Dragon_Hit-" + i + ".png");
				hitImg[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			break;
		case RAPTOR:
			for(int i = 0; i < standLength*2; i++){
				stand[i] = "image/Raptor_Stand-" + i + ".png";
				charStand[i] = new GreenfootImage(stand[i]);
				charStand[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			for(int i = 0; i < walkLength*2; i++){
				walk[i] = "image/Raptor_Walk-" + i + ".png";
				charWalk[i] = new GreenfootImage(walk[i]);
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
			for (int i = 0; i < hitImg .length; i++) {
				hitImg[i] = new GreenfootImage("image/Raptor_Hit-" + i + ".png");
				hitImg[i].scale(CHAR_WIDTH, CHAR_HEIGHT);
			}
			break;
		}
	}
	public void setPlayerRecentlyGotHit(boolean playerRecentlyGotHit) {
		this.playerRecentlyGotHit = playerRecentlyGotHit;
		readyHit = false;
	}//*/
	protected void fight(){
		goHitOtherPlayer();
	}
	protected void goHitOtherPlayer() {

	}
	protected void hitOtherPlayer(){
		List<Player> otherPlayer = getIntersectingObjects(Player.class);
		for (Player otherplayer: otherPlayer) {
			otherplayer.gotHit(1);
			//otherplayer.setPlayerRecentlyGotHit(true);
		}
	}
	protected abstract void gotHit(int dmg);

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
