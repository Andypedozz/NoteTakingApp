package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

public class ZoomablePanel extends JPanel {
    private double scale = 1.0;
    private static final double SCALE_STEP = 0.1;

    // Mappa per salvare le dimensioni e posizioni originali dei componenti
    private final Map<Component, Rectangle> originalBounds = new HashMap<>();

    public ZoomablePanel() {
        setPreferredSize(new Dimension(800, 600));
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.isControlDown()) {
                    if (e.getWheelRotation() < 0) {
                        zoomIn();
                    } else {
                        zoomOut();
                    }
                }
            }
        });
    }

    @Override
    public Component add(Component comp) {
        originalBounds.put(comp, comp.getBounds()); // Salva le dimensioni originali
        super.add(comp);
        return comp;
    }

    public void zoomIn() {
        scale += SCALE_STEP;
        rescaleComponents();
    }

    public void zoomOut() {
        if (scale > SCALE_STEP) {
            scale -= SCALE_STEP;
            rescaleComponents();
        }
    }

    /**
     * Ridimensiona e riposiziona i componenti in base alla scala attuale.
     */
    private void rescaleComponents() {
        for (Component comp : getComponents()) {
            Rectangle origBounds = originalBounds.get(comp); // Ottiene le dimensioni originali
            if (origBounds != null) {
                int newX = (int) (origBounds.x * scale);
                int newY = (int) (origBounds.y * scale);
                int newWidth = (int) (origBounds.width * scale);
                int newHeight = (int) (origBounds.height * scale);
                comp.setBounds(newX, newY, newWidth, newHeight);
            }
        }
        revalidate();
        repaint();
    }
}
