package Home;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Util.Util;

public class TopPanel extends JPanel {
	public TopPanel() {
		JButton userButton = new JButton("Log In");
		JPanel searchBarPanel = new JPanel();
		
		ImageIcon searchIcon = Util.createImageIcon("", new Dimension(20, 20));
		JLabel searchBarIcon = new JLabel(searchIcon);
		
		JScrollPane searchScroll = new JScrollPane();
		JTextField searchField = new JTextField();
		searchScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		searchScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		searchScroll.add(searchBarIcon);
		searchScroll.add(searchField);

		searchBarPanel.add(searchScroll);
		
		this.add(userButton);
		this.add(searchBarPanel);
		
	}
}