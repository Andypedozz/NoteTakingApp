package view;

import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JTabbedPane;
import model.Page;
import controller.Controller;

public class PagesPanel extends JTabbedPane {
	
	private LinkedList<PagePanel> pagePanels;
	
	public PagesPanel() {
		init();
	}

	private void init() {
		this.pagePanels = new LinkedList<PagePanel>();
		HashMap<Integer, Page> pages = Controller.getInstance().getPages();
		pages.forEach((key, value) -> {
			PagePanel pagePanel = new PagePanel(key);
			this.pagePanels.add(pagePanel);
			this.addTab(value.getTitolo(), pagePanel);
		});
	}
}
