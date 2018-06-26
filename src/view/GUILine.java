package view;

public class GUILine {
	
	private boolean consumed;
	private int lineIndex;
	private int color;
	private GUIPlayer owner;
	
	GUILine(boolean consumed, int lineIndex, int color) {
		this.consumed = consumed;
		this.lineIndex = lineIndex;
		this.color = color;
		this.owner = null;
		
	}
	
	public boolean getConsumed() {
		return this.consumed;
	}
	
	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}
	
	public int getLineIndex() {
		return this.lineIndex;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public GUIPlayer getOwner() {
		return this.owner;
	}
	
	public void setOwner(GUIPlayer owner) {
		this.owner = owner;
	}

}
