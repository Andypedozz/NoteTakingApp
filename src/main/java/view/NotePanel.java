package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NotePanel extends JPanel {
	
	private static final int BORDER_SIZE = 10;
	private static final Color BORDER_COLOR = new Color(217, 217, 217);
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private boolean resizing;
	private boolean dragging;
	
	private Point initialClick;
	private int initialWidth;
	private int initialHeight;
	
	public NotePanel() {
		this.setLayout(new BorderLayout());
		this.textArea = new JTextArea();
		this.scrollPane = new JScrollPane(textArea);
		this.add(scrollPane);
		this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_SIZE));
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
		
			@Override
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
				initialWidth = getWidth();
				initialHeight = getHeight();
				int width = getWidth();
				int height = getHeight();
				
				if(isCursorOnRight(e)) {
					resizing = true;
					dragging = false;
				}else if(isCursorOnLeft(e)) {
					resizing = false;
					dragging = true;
				}
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isCursorOnLeft(e)) {
					setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				}else if(isCursorOnRight(e)) {
					setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(resizing) {
                    int newWidth = initialWidth + (e.getX() - initialClick.x);
                    int newHeight = initialHeight + (e.getY() - initialClick.y);
                    setSize(new Dimension(Math.max(100, newWidth), Math.max(100, newHeight)));
                    revalidate();
                    repaint();
				}else if(dragging) {
                    Point newLocation = getLocation();
                    newLocation.translate(e.getX() - initialClick.x, e.getY() - initialClick.y);
                    setLocation(newLocation);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				resizing = false;
				dragging = false;
			}
			
		};
		
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
	}
	
    public String getText() {
    	return this.textArea.getText();
    }
    
    public void setText(String text) {
    	this.textArea.setText(text);
    }
    
    private boolean isCursorOnLeft(MouseEvent e) {
    	int mouseX = e.getX();
    	int mouseY = e.getY();

    	boolean xCondition = (mouseX >= 0 && mouseX <= BORDER_SIZE);
    	boolean yCondition = (mouseY >= 0 && mouseY <= getHeight());
    	
    	return xCondition && yCondition;
    }
    
    private boolean isCursorOnRight(MouseEvent e) {
    	int mouseX = e.getX();
    	int mouseY = e.getY();

    	boolean xCondition = (mouseX >= getWidth() - BORDER_SIZE && mouseX <= getWidth());
    	boolean yCondition = (mouseY >= 0 && mouseY <= getHeight());
    	
    	return xCondition && yCondition;
    }
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(600,400);
    	frame.setLocationRelativeTo(null);
    	
    	NotePanel note = new NotePanel();
    	note.setBounds(20,20,300,300);
    	frame.setLayout(null);
    	frame.add(note);
    	frame.setVisible(true);
    }
}
