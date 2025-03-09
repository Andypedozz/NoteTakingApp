package view;

public class NotePanel extends DraggableResizableNote {
	
	public NotePanel() {
		super.setText("");
	}
	
	public NotePanel(String text) {
		super.setText(text);
	}
}
