package enums;

public enum ScaleOfScreen {
	WIDTH(1440),
	HEIGHT(810);
	
	public int getNum() {
		return num;
	}

	private int num = 0;
	
	private ScaleOfScreen(int num){
		this.num = num;
	}
	
}
