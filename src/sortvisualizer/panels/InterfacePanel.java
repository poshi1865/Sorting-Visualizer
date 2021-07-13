package sortvisualizer.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfacePanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public final int width = 1280;
	private final int height = 120;
	
	JPanel logopanel;
	JPanel textpanel;
	public InterfacePanel() {
		init();
	}
		
	private void init() {
		setPreferredSize(new Dimension(width, height-15));
		setBackground(Color.lightGray);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		//logopanel
		logopanel = new JPanel();
		logopanel.setSize(100, height);
		logopanel.setBackground(Color.white);
		ImageIcon icon = new ImageIcon("res/Logo.png");
		JLabel logo = new JLabel(icon);
		logopanel.add(logo);
		
		//adding logo to Interface Panel
		add(logopanel);
		
		//Text Panel
		Font font = new Font("Consolas", Font.BOLD, 20);
		//Border border = BorderFactory.createMatteBorder(1,1,1,1,Color.green);
		textpanel = new JPanel();
		textpanel.setBackground(Color.lightGray);
		textpanel.setPreferredSize(new Dimension(width-325, height-26));
		textpanel.setLayout(new GridLayout(3, 2));

		//Creating all the labels
		JLabel bubblelabel = new JLabel("Press S to Shuffle the array");
		JLabel selectionlabel = new JLabel("Press E to perform Selection Sort"); 
		JLabel insertionlabel = new JLabel("Press I to perform Insertion Sort");
		JLabel mergelabel = new JLabel("Press M to perform Merge Sort");
		JLabel quicklabel = new JLabel("Press Q to perform Quick Sort"); 
		JLabel radixlabel = new JLabel("Press B to perform Bubble Sort");
		
		//setting fonts to the labels
		bubblelabel.setFont(font);
		//bubblelabel.setBorder(border); 

		selectionlabel.setFont(font);
		//selectionlabel.setBorder(border);
		
		insertionlabel.setFont(font);
		//insertionlabel.setBorder(border);

		mergelabel.setFont(font);
		//mergelabel.setBorder(border);
		
		quicklabel.setFont(font);
		//quicklabel.setBorder(border);
		
		radixlabel.setFont(font);
		//radixlabel.setBorder(border);

		//adding labels to the text panel
		textpanel.add(bubblelabel);
		textpanel.add(insertionlabel);
		textpanel.add(selectionlabel);
		textpanel.add(mergelabel);
		textpanel.add(quicklabel);
		textpanel.add(radixlabel);

		//adding text panel to InterfacePanel
		add(textpanel);
	}
	
}
