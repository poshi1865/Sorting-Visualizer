package sortvisualizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortingVisualizer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static int width = 1280;
	public static int height = 720;
	
	private Random random = new Random();
	private boolean running = true;
	
	JFrame frame;
	String title = "Sorting Visualizer";

	public int arr[];
	
	public SortingVisualizer() {
		init();
		initArray();
	}
	
	private void init() {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.gray);
	}
	
	private void initArray() {
		arr = new int[width];
		for(int i = 0; i < width; i++) {
			int r = random.nextInt(500);
			arr[i] = r;
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(graphics);
		g.setColor(Color.white);
		for(int i = 0; i < width; i+=12) {
			graphics.fillRect(i, height-arr[i], 10, arr[i]);
		}
		graphics.dispose();
	}
	public void run() {
		//sleepFor(2000000000);
		while(running) {
			if(checkIfSorted(arr)) {
				shuffleArray();
				running = false;
				return;
			}
			bubbleSort(arr); 
		}

	}
	
	public void shuffleArray() {
		Random r = new Random();
		while(checkIfSorted(arr)) {
			for(int i = 0; i < arr.length; i++) {
				arr[i] = r.nextInt(500);
				repaint();
			}
		}
	}
	
	public void sleepFor(long nanoseconds) {
		long timeElapsed;
		final long startTime = System.nanoTime();
		do {
			timeElapsed = System.nanoTime() - startTime;
			
		}while(timeElapsed < nanoseconds);
	}
	
	public void bubbleSort(int arr[]) {
		int n = arr.length;
		
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < (n-i); j++) {
				
				if(arr[j-1] > arr[j]) {
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;	
				}
			}
			repaint();
			sleepFor(12000000);
		}
	}
	
	boolean checkIfSorted(int arr[]) {
		int n = arr.length;
		
		for(int i = 0; i < n-1; i++) {
			if(arr[i] > arr[i+1]) return false;
		}
		return true;
	}
	
	public static void main(String args[]) {
		System.setProperty("sun.java2d.opengl", "true");
		SortingVisualizer screen = new SortingVisualizer();
		
		screen.frame = new JFrame(screen.title);
		screen.frame.setSize(new Dimension(width, height));
		screen.frame.setLocationRelativeTo(null);
		screen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.frame.setResizable(false);
		screen.frame.add(screen);
		screen.frame.pack();
		screen.frame.setVisible(true);
		
		screen.run();
	}
	
}
