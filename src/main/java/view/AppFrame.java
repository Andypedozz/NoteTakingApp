package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import controller.Controller;

public class AppFrame extends JFrame {
	private Controller controller;
	private MyToolBar toolBar;
	private PagesPanel pagesPanel;
	
	public AppFrame() {
		super();
		init();
	}
	
	private void init() {
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.controller = Controller.getInstance();
		
		this.toolBar = new MyToolBar();
		this.pagesPanel = new PagesPanel();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.getContentPane().add(pagesPanel, BorderLayout.CENTER);
	}
	
	private void initListeners() {
		
	}
}
