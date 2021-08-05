package sortvisualizer.main;

import javax.swing.JFrame;
import sortvisualizer.panels.ContentPanel;

public class Display extends JFrame{
	private static final long serialVersionUID = 1L;

	private ContentPanel contentpanel;

	public Display() {
		//initialize the display and add the content panel
		init();
	}

	private void init() {
		contentpanel = new ContentPanel();
		
		setTitle("Sorting Visualizer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setContentPane(contentpanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		contentpanel.sortingpanel.run();
	}

}
