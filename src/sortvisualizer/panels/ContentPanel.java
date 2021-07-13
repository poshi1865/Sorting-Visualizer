package sortvisualizer.panels;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import sortvisualizer.main.SortingVisualizer;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel {
	public final int width = 1280;
	public final int height = 720;

	public SortingVisualizer sortingpanel;
	private InterfacePanel interfacepanel;

	public ContentPanel() {

		sortingpanel = new SortingVisualizer();
		interfacepanel = new InterfacePanel();
		setPreferredSize(new Dimension(width, height));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(interfacepanel);
		add(sortingpanel);
		
	}
}
