package sortvisualizer.main;

import javax.swing.JFrame;
import sortvisualizer.panels.ContentPanel;

public class Display extends JFrame{
	
	private ContentPanel contentpanel;

	public Display() {
		//initialize the display and add the content panel
		init();
	}
	private void init() {
		
		contentpanel = new ContentPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setContentPane(contentpanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		contentpanel.sortingpanel.run();
	}

}
