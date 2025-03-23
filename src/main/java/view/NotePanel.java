package view;

import javax.swing.event.CaretEvent;

public class NotePanel extends DraggableResizableTextPane {
	
	private Formatter formatter;
	
	public NotePanel() {
		super();
		this.formatter = new Formatter(getTextPane());
		initListeners();
	}
	
	public Formatter getFormatter() {
		return this.formatter;
	}
	
	private void initListeners() {
		this.getTextPane().addCaretListener(e -> {
			updateToolbar(e);
		});
	}
	
	private void updateToolbar(CaretEvent e) {
		formatter.updateStyleState(e);
		boolean isBold = formatter.isBold();
		boolean isItalic = formatter.isItalic();
		boolean isUnderline = formatter.isUnderline();
		int fontSize = formatter.getCurrentFontSize();
		
		TextToolbar textToolbar = View.getFrame().getToolBar().getTextToolbar();
		textToolbar.getBoldButton().setSelected(isBold);
		textToolbar.getItalicButton().setSelected(isItalic);
		textToolbar.getUnderlineButton().setSelected(isUnderline);
		textToolbar.getFontBox().setSelectedItem(fontSize);
	}
}
