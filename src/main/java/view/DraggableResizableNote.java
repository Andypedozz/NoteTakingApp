package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DraggableResizableNote extends JPanel {
    private static final int BORDER_THICKNESS = 5;
    private Point initialClick;
    private boolean resizing = false;
    private boolean dragging = false;
    private int initialWidth;
    private int initialHeight;

    public DraggableResizableNote() {
        setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                initialWidth = getWidth();
                initialHeight = getHeight();
                int width = getWidth();
                int height = getHeight();
                
                if (initialClick.x >= width - BORDER_THICKNESS || initialClick.y >= height - BORDER_THICKNESS) {
                    resizing = true;
                    dragging = false;
                } else if (initialClick.x <= BORDER_THICKNESS) {
                    dragging = true;
                    resizing = false;
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            	if (e.getX() >= getWidth() - BORDER_THICKNESS) {
            		setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
            	} else if (e.getX() <= BORDER_THICKNESS) {
            		setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            	}else {
            		setCursor(Cursor.getDefaultCursor());
            	}
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (resizing) {
                    int newWidth = initialWidth + (e.getX() - initialClick.x);
                    int newHeight = initialHeight + (e.getY() - initialClick.y);
                    setSize(new Dimension(Math.max(100, newWidth), Math.max(100, newHeight)));
                    revalidate();
                    repaint();
                } else if (dragging) {
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
        
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }
}
