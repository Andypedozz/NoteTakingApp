package view;

public class NotePanel extends DraggableResizableTextPane {
	
	private Formatter formatter;
	
	public NotePanel() {
		super();
		this.formatter = new Formatter(getTextPane());
	}
	
	public Formatter getFormatter() {
		return this.formatter;
	}
}
