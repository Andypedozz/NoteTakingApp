package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResizableNote extends JPanel {
	private JTextArea textPane;
	private int mouseX, mouseY;
	private boolean resizing;
	
	public ResizableNote() {
		setLayout(new BorderLayout());
		
		textPane = new JTextArea(10,30);
		JScrollPane scrollPane = new JScrollPane(textPane);
		add(scrollPane, BorderLayout.CENTER);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
                if (e.getX() >= getWidth() - 10 && e.getY() >= getHeight() - 10) {
                    resizing = true;
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				resizing = false;
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(resizing) {
					int deltaX = e.getX() - mouseX;
                    int deltaY = e.getY() - mouseY;
                    
                    setSize(getWidth() + deltaX, getHeight() + deltaY);
                    textPane.setSize(getWidth() + deltaX - 30, getHeight() + deltaY - 30); // Minus the scroll bar space
                    mouseX = e.getX();
                    mouseY = e.getY();
				}
				revalidate();
			}
		
		});
	}

}
