package Home;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CopyRight extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4969533712747204047L;

	public CopyRight() {
		super();
		setBackground(Color.BLACK);
		JLabel copyRight = new JLabel("@ 2018. All rights reserved. Powered by Kate Kim.");
		copyRight.setForeground(Color.WHITE);
		add(copyRight);
	}
}
