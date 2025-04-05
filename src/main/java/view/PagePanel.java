package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import controller.Controller;
import model.Note;

public class PagePanel extends JPanel {

	private static final int NEW_NOTE_WIDTH = 200;
	private static final int NEW_NOTE_HEIGHT = 200;
	
	private JScrollPane scrollPane;
	private JPanel notesContainer;
	private int pageNumber;
	
	public PagePanel(int pageNumber) {
		super();
		this.pageNumber = pageNumber;
		init();
		loadNotes();
		initListeners();
	}
	
	private void init() {
		setLayout(new GridLayout(1,1));
		
		notesContainer = new JPanel(null);
		
		scrollPane = new JScrollPane(notesContainer);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(scrollPane);
	}
	
	private void loadNotes() {
		HashMap<Integer, Note> notes = Controller.getInstance().getPages().get(pageNumber).getNotes();
		notes.forEach((noteId, note) -> {
			addNote(note);
		});
	}
	
	public void addNote(Note note) {
		Point point = new Point(note.getPositionX(),note.getPositionY());
		Point shifted = point;
		NotePanel notePanel = new NotePanel();
		notePanel.setText(note.getText());
		notePanel.setBounds((int) shifted.getX(), (int) shifted.getY(), NEW_NOTE_WIDTH, NEW_NOTE_HEIGHT);
		notesContainer.add(notePanel);
		notesContainer.revalidate();
		notesContainer.repaint();
		updateContainerSize();
	}
	
	public void addNewNote(Point point) {
		Point shifted = shiftCoordinates(point);
		NotePanel notePanel = new NotePanel();
		notePanel.setBounds((int) shifted.getX(), (int) shifted.getY(), NEW_NOTE_WIDTH, NEW_NOTE_HEIGHT);
		notesContainer.add(notePanel);
		notesContainer.revalidate();
		notesContainer.repaint();
		updateContainerSize();
	}
	
    private void updateContainerSize() {
        int maxWidth = 0, maxHeight = 0;
        for (Component comp : notesContainer.getComponents()) {
            Rectangle bounds = comp.getBounds();
            maxWidth = Math.max(maxWidth, bounds.x + bounds.width);
            maxHeight = Math.max(maxHeight, bounds.y + bounds.height);
        }
        notesContainer.setPreferredSize(new Dimension(maxWidth, maxHeight));
        notesContainer.revalidate();
    }
	
	public void removeNote(NotePanel notePanel) {
			
	}
	
	private void initListeners() {
		notesContainer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					addNewNote(e.getLocationOnScreen());
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
