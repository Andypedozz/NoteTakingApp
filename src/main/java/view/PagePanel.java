package view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JPanel;
import controller.Controller;
import model.Note;

public class PagePanel extends JPanel {

	private Controller controller;
	private HashMap<Integer, NotePanel> notePanels;
	
	public PagePanel() {
		init();
	}
	
	private void init() {
		this.notePanels = new HashMap<Integer, NotePanel>();
	}
	
	public void addNote(Note note, Point point) {
		
	}
	
	public void addNote(Point point) {
		NotePanel notePanel = new NotePanel();
	}
	
	public void removeNote(NotePanel notePanel) {
			
	}
	
	private void initListeners() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = e.getLocationOnScreen();
				addNote(point);
			}
		});
	}
}
