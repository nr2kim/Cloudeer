package Util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import Home.HomeSetup;

public class Util {
	/**
	 * Helper function making text panel
	 * @param text
	 * @return
	 */
	public static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        TableModel tableModel = new AbstractTableModel() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 5833244915186107505L;
			private String headers[] = {
        			"Name", "Size", "Type"
        	};
        	public int getColumnCount() { return 3; }
        	public int getRowCount() { return 25; }
        	public String getColumnName(int col) {
        		return headers[col];
        	}
        	public Object getValueAt(int row, int col) { return null; }
        };

        JTable table = new JTable(tableModel);
        
        table.getColumnModel().getColumn(0).setPreferredWidth((int) (HomeSetup.homeFrameSize.width * 0.6));
        table.getColumnModel().getColumn(1).setPreferredWidth((int) (HomeSetup.homeFrameSize.width * 0.2));
        table.getColumnModel().getColumn(2).setPreferredWidth((int) (HomeSetup.homeFrameSize.width * 0.2));
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(1).setMinWidth(50);
        table.getColumnModel().getColumn(2).setMinWidth(50);
        
        table.setRowHeight((int) ((HomeSetup.homeFrameSize.height-102)/25));

        UIManager.put("JTable.showFrid", true);
        JScrollPane tableContainer = new JScrollPane(table);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.add(tableContainer, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(100,100));
        return panel;
    }
	
	
	
	/**
	 * Helper function creating image icon
	 * @param path path to that image
	 * @param size size of the image
	 * @return
	 */
    public static ImageIcon createImageIcon(String path, Dimension size) {
    	Image img = Toolkit.getDefaultToolkit().getImage(path);
    	Image resizedImage = img.getScaledInstance(size.width-20, size.height-10, java.awt.Image.SCALE_REPLICATE);
        if (resizedImage != null) {
            return new ImageIcon(resizedImage);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}