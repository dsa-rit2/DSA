import travelBug.UI.*;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
    		try {
    			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    		} catch (Exception e) { e.printStackTrace(); }
    		
    		new Authentication();
        });
	}

}
