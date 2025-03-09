package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class MyToolBar extends JPanel {
	private TextToolbar textToolbar;
	
	public MyToolBar() {
		init();
	}

	private void init() {
		this.textToolbar = new TextToolbar();
		this.setLayout(new GridLayout(1,1));
		this.add(textToolbar);
	}

}
