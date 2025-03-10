package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class DraggableResizableTextPane extends JPanel {
	
	private static final int BORDER_SIZE = 10;
	private static final Color BORDER_COLOR = new Color(217, 217, 217);
	private JTextPane textPane;
	private JScrollPane scrollPane;
	private boolean resizing;
	private boolean dragging;
	private Point initialClick;
	private int initialWidth;
	private int initialHeight;
	
	public DraggableResizableTextPane() {
		this.setLayout(new BorderLayout());
		this.textPane = new JTextPane();
		this.scrollPane = new JScrollPane(textPane);
		this.add(scrollPane);
		this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_SIZE));
		initListeners();
	}
	
	private void initListeners() {
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
				}else if(isCursorOnLeft(e) || isCursorOnTop(e) || isCursorOnBottom(e)) {
					resizing = false;
					dragging = true;
				}
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isCursorOnLeft(e) || isCursorOnTop(e) || isCursorOnBottom(e)) {
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
    	return this.textPane.getText();
    }
    
    public void setText(String text) {
    	this.textPane.setText(text);
    }
    
    public JTextPane getTextPane() {
    	return this.textPane;
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
    
    private boolean isCursorOnTop(MouseEvent e) {
    	int mouseX = e.getX();
    	int mouseY = e.getY();
    	
    	boolean xCondition = (mouseX >= 0 && mouseX <= getWidth());
    	boolean yCondition = (mouseY >= 0 && mouseY <= BORDER_SIZE);
    	
    	return xCondition && yCondition;
    }
    
    private boolean isCursorOnBottom(MouseEvent e) {
    	int mouseX = e.getX();
    	int mouseY = e.getY();
    	
    	boolean xCondition = (mouseX >= 0 && mouseX <= getWidth());
    	boolean yCondition = (mouseY >= getHeight() - BORDER_SIZE && mouseY <= getHeight());
    	
    	return xCondition && yCondition;
    }
}
