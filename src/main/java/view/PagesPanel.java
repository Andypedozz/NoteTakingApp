package view;

import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JTabbedPane;
import model.Page;
import controller.Controller;

public class PagesPanel extends JTabbedPane {
	
	private Controller controller;
	private LinkedList<PagePanel> pagePanels;
	
	public PagesPanel() {
		init();
	}

	private void init() {
		this.controller = Controller.getInstance();
		this.pagePanels = new LinkedList<PagePanel>();
		HashMap<Integer, Page> pages = this.controller.getPages();
		pages.forEach((key, value) -> {
			PagePanel pagePanel = new PagePanel();
			this.pagePanels.add(pagePanel);
			this.addTab(value.getTitolo(), pagePanel);
		});
	}
	
	public void showPage(Page page) {
		
	}
}
