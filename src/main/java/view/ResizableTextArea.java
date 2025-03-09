package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResizableTextArea extends JPanel {
    private JTextArea textArea;
    private int mouseX, mouseY;
    private boolean resizing;

    public ResizableTextArea() {
        setLayout(new BorderLayout());

        // Crea un'area di testo
        textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Aggiungi un listener per il ridimensionamento
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Verifica se il clic è sul bordo destro e inferiore per iniziare il ridimensionamento
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
                if (resizing) {
                    int deltaX = e.getX() - mouseX;
                    int deltaY = e.getY() - mouseY;

                    // Ridimensiona il pannello e la JTextArea
                    setSize(getWidth() + deltaX, getHeight() + deltaY);
                    textArea.setSize(getWidth() + deltaX - 30, getHeight() + deltaY - 30); // Minus the scroll bar space
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Resizable TextArea");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(new ResizableTextArea());
            frame.setVisible(true);
        });
    }
}

