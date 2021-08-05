package sortvisualizer.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfacePanel extends JPanel {
	private static final long serialVersionUID = 1L;


	private final int width = 1280;
	private final int height = 50;
	
	private int sliderMax = 700;
	
	private Font font;
	
	private Color componentColor = Color.white;

	//Density panel declaration
	private JPanel densityPanel;
	public JSlider densitySlider;

	//Color panel declaration
	private JPanel colorPanel;
	private JComboBox<String> colorBox;
	
	private JPanel speedPanel;
	private JSlider speedSlider;

	public InterfacePanel() {
		init();
		
		font = new Font("Berlin Sans FB", Font.PLAIN, 17);
		
		//Density Panel
		densityPanel = new JPanel();
		densityPanel.setLayout(new BoxLayout(densityPanel, BoxLayout.Y_AXIS));
		densityPanel.setBackground(componentColor);
		
		JLabel denseLabel = new JLabel("Array Density");
		denseLabel.setFont(font);

		densitySlider = new JSlider();
		densitySlider.setBackground(componentColor);
		densitySlider.setMaximum(sliderMax);
		densitySlider.setValue(sliderMax/2);
		densitySlider.setFocusable(false);
		densitySlider.setInverted(true);

		densityPanel.add(denseLabel);
		densityPanel.add(densitySlider);
		
		
		
		//Color panel
		colorPanel = new JPanel();
		colorPanel.setBackground(componentColor);
		colorBox = new JComboBox<>();
		colorBox.setBackground(componentColor);
		colorBox.setFont(font);
		colorBox.addItem("White");
		colorBox.addItem("Green");
		colorBox.addItem("Red");
		colorBox.addItem("Blue");
		colorBox.addItem("Orange");
		colorBox.addItem("Magenta");
		colorBox.setFocusable(false);
		colorPanel.add(colorBox);
		
		//Speed Panel
		speedPanel = new JPanel();
		speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.Y_AXIS));
		speedPanel.setBackground(componentColor);

		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setFont(font);

		speedSlider = new JSlider(1000000, 100000000);
		speedSlider.setBackground(componentColor);
		speedSlider.setInverted(true);
		speedSlider.setFocusable(false);
		speedPanel.add(speedLabel);
		speedPanel.add(speedSlider);
		
		//ADDING EVERYTHING TO INTERFACE PANEL
		add(densityPanel);
		add(colorPanel);
		add(speedPanel);
	}
	
	private void init() {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.black);
		setLayout(new FlowLayout(FlowLayout.LEFT, 200, height/7));
	}
	
	public int getDensitySliderValue() {
		return densitySlider.getValue();
	}
	
	public Color getComboBoxColorValue() {
		String s = (String) colorBox.getSelectedItem();
		
		switch(s) {
			case "White":
				return Color.white;
			case "Green":
				return Color.green;
			case "Red":
				return Color.red;
			case "Blue":
				return Color.blue;
			case "Orange":
				return Color.orange;
			case "Magenta":
				return Color.magenta;
		}
		return Color.white;
	}
	
	public int getSpeedSliderValue() {
		return speedSlider.getValue();
	}
	
}












