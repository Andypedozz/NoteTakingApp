package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class TextToolbar extends JPanel {
	
	private JToolBar upperToolBar;
	private JToolBar lowerToolBar;
	
	private JButton boldButton;
	private JButton italicButton;
	private JButton underlineButton;
	private JButton colorButton;
	private JButton alignLeftButton;
	private JButton alignCenterButton;
	private JButton alignRightButton;
	private JButton bulletListButton;
	private JButton numberedListButton;
	private JComboBox<String> fontSizeBox;
	private JComboBox<String> fontBox;
	
	public TextToolbar() {
		addToolbarButtons();
	}
	
	private void addToolbarButtons() {
		this.setLayout(new GridLayout(2,1));
		this.upperToolBar = new JToolBar();
	    boldButton = new JButton("B");
	    boldButton.setFont(new Font("Arial", Font.BOLD, 12));
	    upperToolBar.add(boldButton);
	
	    italicButton = new JButton("I");
	    italicButton.setFont(new Font("Arial", Font.ITALIC, 12));
	    upperToolBar.add(italicButton);
	
	    underlineButton = new JButton("U");
	    underlineButton.setFont(new Font("Arial", Font.PLAIN, 12));
	    upperToolBar.add(underlineButton);
	    
	    colorButton = new JButton("Color");
	    upperToolBar.add(colorButton);
	    
	    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	    fontBox = new JComboBox<>(fonts);
	    upperToolBar.add(fontBox);

	    String[] sizes = {"12", "14", "16", "18", "20", "24", "28"};
	    fontSizeBox = new JComboBox<>(sizes);
	    upperToolBar.add(fontSizeBox);
	    
	    this.lowerToolBar = new JToolBar();
	    alignLeftButton = new JButton("L");
	    lowerToolBar.add(alignLeftButton);
	    
	    alignCenterButton = new JButton("C");
	    lowerToolBar.add(alignCenterButton);
	    
	    alignRightButton = new JButton("R");
	    lowerToolBar.add(alignRightButton);
	    
	    bulletListButton = new JButton("•");
	    lowerToolBar.add(bulletListButton);
	    
	    numberedListButton = new JButton("1.");
	    lowerToolBar.add(numberedListButton);
	    this.add(upperToolBar);
	    this.add(lowerToolBar);
	    this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	}
	
	private void initListeners() {
		
	}

}
