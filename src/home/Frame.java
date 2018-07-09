package home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Frame extends JFrame {
	public static Dimension fullLoginScreenSize;

	public Frame() {
		super("Cloudeer");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullLoginScreenSize = new Dimension(screenSize.width/2, screenSize.height/2);

		add(new Home(), BorderLayout.CENTER);
        add(new CopyRight(), BorderLayout.PAGE_END);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(fullLoginScreenSize);
        //Display the window.
        pack();
        setVisible(true);
	}
	public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
            	UIManager.put("swing.boldMetal", Boolean.FALSE);
            	new Frame();
            }
        });
    }
}