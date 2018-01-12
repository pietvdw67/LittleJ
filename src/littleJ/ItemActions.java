package littleJ;

public enum ItemActions {
	ON(1),OFF(0),TOGGLE(2),ENABLE(3),DISABLE(4),PLAY_ONCE(5),PLAY_REPEAT(6),STOP_AUDIO(7);
	
	int value = 0;
	
	private ItemActions(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

}
