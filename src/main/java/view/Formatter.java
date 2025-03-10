package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Formatter {

	private JTextPane textPane;
	private boolean isBold;
	private boolean isItalic;
	private boolean isUnderline;
	private int currentFontSize = 12;
	private boolean bulletMode = false;
	
	public Formatter(JTextPane textPane) {
		this.textPane = textPane;
		initListeners();
	}
	
	private void initListeners() {
        textPane.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if (e.isControlDown()) {
        			switch (e.getKeyCode()) {
        			case KeyEvent.VK_G:
        				toggleStyle(StyleConstants.Bold);
        				break;
        			case KeyEvent.VK_I:
        				toggleStyle(StyleConstants.Italic);
        				break;
        			case KeyEvent.VK_U:
        				toggleStyle(StyleConstants.Underline);
        			case KeyEvent.VK_COMMA:
        				changeFontSize(-2);
        				break;
        			case KeyEvent.VK_PERIOD:
        				changeFontSize(2);
        				break;
        			}
        		}
        	}
        	
            @Override
            public void keyTyped(KeyEvent e) {
                handleBulletPoint(e);
            }
        });
        textPane.addCaretListener(e -> updateStyleState(e));
	}
	
	public void toggleStyle(Object style) {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        StyledDocument doc = textPane.getStyledDocument();

        if (start == end) {
            // Se non c'è selezione, cambiamo lo stato per il testo futuro
            MutableAttributeSet attr = new SimpleAttributeSet(textPane.getInputAttributes());
            if (style.equals(StyleConstants.Bold)) {
                isBold = !StyleConstants.isBold(attr);
                StyleConstants.setBold(attr, isBold);
            } else if (style.equals(StyleConstants.Italic)) {
                isItalic = !StyleConstants.isItalic(attr);
                StyleConstants.setItalic(attr, isItalic);
            } else if (style.equals(StyleConstants.Underline)) {
                isUnderline = !StyleConstants.isUnderline(attr);
                StyleConstants.setUnderline(attr, isUnderline);
            }
            textPane.setCharacterAttributes(attr, false);
            textPane.getInputAttributes().addAttributes(attr);
        } else {
            // Se c'è una selezione, applicare direttamente il cambiamento
            Element element = doc.getCharacterElement(start);
            AttributeSet attrs = element.getAttributes();
            SimpleAttributeSet newAttr = new SimpleAttributeSet(attrs);
            
            if (style.equals(StyleConstants.Bold)) {
                StyleConstants.setBold(newAttr, !StyleConstants.isBold(attrs));
            } else if (style.equals(StyleConstants.Italic)) {
                StyleConstants.setItalic(newAttr, !StyleConstants.isItalic(attrs));
            } else if (style.equals(StyleConstants.Underline)) {
                StyleConstants.setUnderline(newAttr, !StyleConstants.isUnderline(attrs));
            }

            doc.setCharacterAttributes(start, end - start, newAttr, false);
        }
    }
	
    public void changeFontSize(int delta) {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        StyledDocument doc = textPane.getStyledDocument();

        if (start == end) {
            currentFontSize = Math.max(2, currentFontSize + delta);
            MutableAttributeSet attr = new SimpleAttributeSet(textPane.getInputAttributes());
            StyleConstants.setFontSize(attr, currentFontSize);
            textPane.setCharacterAttributes(attr, false);
            textPane.getInputAttributes().addAttributes(attr);
        } else {
            Element element = doc.getCharacterElement(start);
            AttributeSet attrs = element.getAttributes();
            int currentSize = StyleConstants.getFontSize(attrs);
            SimpleAttributeSet newAttr = new SimpleAttributeSet(attrs);
            StyleConstants.setFontSize(newAttr, Math.max(2, currentSize + delta));
            doc.setCharacterAttributes(start, end - start, newAttr, false);
        }
    }

    // Metodo per aggiungere un punto elenco
    private void handleBulletPoint(KeyEvent e) {
        try {
            StyledDocument doc = textPane.getStyledDocument();
            int pos = textPane.getCaretPosition();
            if (pos >= 2) {
                String text = doc.getText(pos - 2, 2);
                if (text.equals("* ")) {
                    doc.remove(pos - 2, 2);
                    doc.insertString(pos - 2, "\u2022 ", null);
                    bulletMode = true;
                    e.consume();
                }
            }
            if (e.getKeyChar() == '\n' && bulletMode) {
                doc.insertString(pos, "\u2022 ", null);
                e.consume();
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateStyleState(CaretEvent e) {
        int pos = textPane.getCaretPosition();
        if (pos > 0) {
            StyledDocument doc = textPane.getStyledDocument();
            Element element = doc.getCharacterElement(pos - 1);
            AttributeSet attrs = element.getAttributes();
            isBold = StyleConstants.isBold(attrs);
            isItalic = StyleConstants.isItalic(attrs);
            isUnderline = StyleConstants.isUnderline(attrs);
            currentFontSize = StyleConstants.getFontSize(attrs);
        }
    }

	public boolean isBold() {
		return isBold;
	}

	public boolean isItalic() {
		return isItalic;
	}

	public boolean isUnderline() {
		return isUnderline;
	}

	public int getCurrentFontSize() {
		return currentFontSize;
	}

}
