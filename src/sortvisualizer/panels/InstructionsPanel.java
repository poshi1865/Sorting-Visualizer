package sortvisualizer.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionsPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public final int width = 1280;
	private final int height = 120;
	
	private JPanel logoPanel;
	private JPanel textpanel;

	private Font font;

	public InstructionsPanel() {
		init();
	}
		
	private void init() {
		setPreferredSize(new Dimension(width, height-15));
		setBackground(Color.gray);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		font = new Font("Berlin Sans FB", Font.PLAIN, 23);

		//logopanel
		logoPanel = new JPanel();
		logoPanel.setSize(100, height);
		logoPanel.setBackground(Color.white);
		ImageIcon icon = new ImageIcon("res/Logo.png");
		JLabel logo = new JLabel(icon);
		logoPanel.add(logo);
		
		//adding logo to Interface Panel
		add(logoPanel);
		
		//Text Panel
		textpanel = new JPanel();
		textpanel.setBackground(Color.gray);
		textpanel.setPreferredSize(new Dimension(width-350, height-38));
		textpanel.setLayout(new GridLayout(3, 3));
		

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
