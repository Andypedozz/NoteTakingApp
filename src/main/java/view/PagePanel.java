package view;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import controller.Controller;
import model.Note;

public class PagePanel extends JPanel {

	private static final int NEW_NOTE_WIDTH = 200;
	private static final int NEW_NOTE_HEIGHT = 200;
	
	private Controller controller;
	private JScrollPane scrollPane;
	private JPanel notesPanel;
	
	public PagePanel() {
		init();
		initListeners();
	}
	
	private void init() {
		this.setLayout(new GridLayout(1,1));
		this.notesPanel = new JPanel();
		this.notesPanel.setLayout(null);
		this.scrollPane = new JScrollPane(notesPanel);
		this.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollPane);
	}
	
	public void addNote(Note note, Point point) {
		
	}
	
	public void addNote(Point point) {
		Point shifted = shiftCoordinates(point);
		NotePanel notePanel = new NotePanel();
		notePanel.setBounds((int) shifted.getX(), (int) shifted.getY(), NEW_NOTE_WIDTH, NEW_NOTE_HEIGHT);
		this.notesPanel.add(notePanel);
		this.notesPanel.revalidate();
		this.revalidate();
	}
	
	public void removeNote(NotePanel notePanel) {
			
	}
	
	private void initListeners() {
		this.notesPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					addNote(e.getLocationOnScreen());
				}
			}
		});
	}
	
	private Point shiftCoordinates(Point point) {
		Point location = this.getLocationOnScreen();
		int x = (int)(point.getX() - location.getX());
		int y = (int)(point.getY() - location.getY());
		return new Point(x,y);
	}
}
