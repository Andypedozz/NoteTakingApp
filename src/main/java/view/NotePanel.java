package view;

public class NotePanel extends DraggableResizableNote {
	
	private String text;
	
	public NotePanel() {
		this.text = "";
	}
	
	public NotePanel(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
