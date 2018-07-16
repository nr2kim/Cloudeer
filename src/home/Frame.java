package Home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Home.Home;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8589198899364182488L;
	public static Dimension fullLoginScreenSize;
	private Home home;
	public Frame() {
		super("Cloudeer");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fullLoginScreenSize = new Dimension(screenSize.width/2, screenSize.height/2);

		home = new Home();
		add(home, BorderLayout.CENTER);
        add(new CopyRight(), BorderLayout.PAGE_END);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(fullLoginScreenSize);
        //Display the window.
        pack();
        setVisible(true);
        
        addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				fullLoginScreenSize = e.getComponent().getSize();
				home.resize(fullLoginScreenSize);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
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
            	new Frame();
            }
        });
    }
}