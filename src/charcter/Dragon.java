package charcter;


import interfaces.Player_Status;

public class Dragon extends Player implements Player_Status{
	
	public Status stat;
	public Status[] bars;
	private int x = 30;
	private int y = 30;
	
	public Dragon(){
		super (6, 8, 7, "w", Character.DRAGON);
	}
	@Override
	public void healthDisplay(int health){
		bars = new Status[health/4];
		for(int i = 0; i < health/4; i++){
			stat = new Status();
			bars[i] = stat;
			getWorld().addObject(stat, x, y);
			x += 20;
		}
		health--;
	}
	@Override
	public void setHealth(int health) {
		this.health = health;
		bars = new Status[health];
	}
	@Override
	public int getHealth() {
		return health;
	}
	
	

}
