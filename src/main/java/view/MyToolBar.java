package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;

public class MyToolBar extends JPanel {
	private Controller controller;
	private TextToolbar textToolbar;
	private JButton saveButton;
	
	public MyToolBar() {
		init();
		initListeners();
	}

	private void init() {
		this.controller = Controller.getInstance();
		this.setLayout(new BorderLayout());
		this.textToolbar = new TextToolbar();
		this.saveButton = new JButton("Salva");
		
		this.add(saveButton, BorderLayout.WEST);
		this.add(textToolbar, BorderLayout.CENTER);
	}
	
	private void initListeners() {
		this.saveButton.addActionListener(e -> {
			controller.saveNotes();
		});
	}

	public TextToolbar getTextToolbar() {
		return textToolbar;
	}
}
