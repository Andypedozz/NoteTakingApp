package view;

import javax.swing.UIManager;

public class View {
	
	private AppFrame frame;
	
	public void launchFrame() {
	    try {
            // Set cross-platform Java L&F (also called "Metal")
	        UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
	    } 
	    catch (Exception e) {
	       // handle exception
	    }
		this.frame = new AppFrame();
	}

	public AppFrame getFrame() {
		return frame;
	}
}
