package view;

import javax.swing.JPanel;

public class NotePanel extends ResizableTextArea {
	
	private String text;
	
	public NotePanel() {
		
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
