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
	private InstructionsPanel instructionsPanel;

	public ContentPanel() {

		sortingpanel = new SortingVisualizer();
		instructionsPanel = new InstructionsPanel();
		setPreferredSize(new Dimension(width, height));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


		add(instructionsPanel);
		add(sortingpanel.interfacePanel);
		add(sortingpanel);
	}
}
