package view;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.jthemedetecor.OsThemeDetector;

public class View {
	
	private static AppFrame frame;
	
	public void launchFrame() {
	    try {
	    	if (OsThemeDetector.getDetector().isDark()) {
	    		UIManager.setLookAndFeel(new FlatDarkLaf());
	    	} else {
	    		UIManager.setLookAndFeel(new FlatLightLaf());
	    	}
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		frame = new AppFrame();
	}

	public static AppFrame getFrame() {
		return frame;
	}
}
