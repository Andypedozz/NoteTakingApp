package view;

import javax.swing.UIManager;

public class View {
	
	private AppFrame frame;
	
	public void launchFrame() {
	    try {
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		this.frame = new AppFrame();
	}

	public AppFrame getFrame() {
		return frame;
	}
}
