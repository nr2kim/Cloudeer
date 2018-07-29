package Home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HomeSetup {
	public static Dimension homeFrameSize;

	public HomeSetup() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		homeFrameSize = new Dimension(screenSize.width/2, screenSize.height/2);
		
		JFrame homeFrame = new JFrame("Cloudeer");
		JPanel topPanel = new TopPanel();
		JPanel homePanel = new HomePanel();
		
		homeFrame.add(topPanel, BorderLayout.PAGE_START);
		homeFrame.add(homePanel, BorderLayout.CENTER);
		homeFrame.add(new CopyRight(), BorderLayout.PAGE_END);
		
		// Add ComponentListener for resizing later
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homeFrame.setPreferredSize(homeFrameSize);
		homeFrame.pack();
		homeFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
            	try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	new HomeSetup();
            }
        });
    }
}