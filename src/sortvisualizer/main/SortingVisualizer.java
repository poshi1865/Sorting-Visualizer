package sortvisualizer.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortingVisualizer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static int width = 1280;
	private static int height = 720;
	
	private Random random = new Random();
	private boolean running = true;
	
	JFrame frame;
	String title = "Sorting Visualizer";
	
	Keyboard key;

	public int arr[];
	public int temp[];
	
	public SortingVisualizer() {
		init();
		initArray();
		
		key =  new Keyboard();
		this.setFocusable(true);
		this.requestFocus();
		addKeyListener(key);
	}
	
	private void init() {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.lightGray);
	}
	
	private void initArray() {
		arr = new int[width];
		for(int i = 0; i < width; i++) {
			int r = random.nextInt(500);
			arr[i] = r+100;
			System.out.println(arr[i]);
		}
		temp = arr;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(graphics);
		
		graphics.setColor(Color.gray);
		for(int i = 0; i < width; i+=12) {
			graphics.fillRect(i, height-arr[i], 10, arr[i]);
		}
		graphics.dispose();
	}
	public void run() {
			while(running) update();
	}
	
	public void shuffleArray() {
		Random r = new Random();
		for(int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(500)+100;
			repaint();
			sleepFor(1000000);
		}
	}
	
	//Listen for key pressesbb
	public synchronized void update() {
		if(key.m)
			selectionSort(arr);
		if(key.b) 
			bubbleSort(arr);
		if(key.s)
			shuffleArray();
		if(key.i)
			insertionSort(arr);

		key.update();
	}
	
	//Function to sleep for a given amount of time
	public void sleepFor(long nanoseconds) {
		long timeElapsed;
		final long startTime = System.nanoTime();
		do {
			timeElapsed = System.nanoTime() - startTime;
			
		}while(timeElapsed < nanoseconds);
	}
	
	//Function to sort the array which calls repaint() each swap
	public void bubbleSort(int arr[]) {
		if(checkIfSorted(arr)) return;
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
			sleepFor(8000000);
		}
	}
	//Selection sort
	 void selectionSort(int arr[]) {
		 	if(checkIfSorted(arr)) return;
	        int n = arr.length;
	 
	        // One by one move boundary of unsorted subarray
	        for (int i = 0; i < n-1; i++)
	        {
	            // Find the minimum element in unsorted array
	            int min_idx = i;
	            for (int j = i+1; j < n; j++)
	                if (arr[j] < arr[min_idx])
	                    min_idx = j;
	 
	            // Swap the found minimum element with the first
	            // element
	            int temp = arr[min_idx];
	            arr[min_idx] = arr[i];
	            arr[i] = temp;
	            repaint();
				sleepFor(8000000);
	        }
	    }
	
	//insertion sort
	 public void insertionSort(int arr[])
	 {
		 if(checkIfSorted(arr)) return;
		 int n = arr.length;
	     int i, key, j;
	     for (i = 1; i < n; i++)
	     {
	         key = arr[i];
	         j = i - 1;
	  
	         /* Move elements of arr[0..i-1], that are
	         greater than key, to one position ahead
	         of their current position */
	         while (j >= 0 && arr[j] > key)
	         {
	             arr[j + 1] = arr[j];
	             j = j - 1;
	             
	         }
	         repaint();
             sleepFor(8000000);
	         arr[j + 1] = key;
	     }
	 }
	 
	 
	
	//Function to merge sort
	public void merge(int b[], int c[], int a[], int p, int q, int n) {
		int i = 0, j = 0, k = 0;
		
		while(i < p && j < q) {

			if(b[i] < c[j]) {
				a[k] = b[i];
				i++;
			}
			else {
				a[k] = c[j];
				j++;
			}
			k++;
		}
		
		if(i == p) {
			while(j < q) {
				a[k] = c[j];
				k++;
				j++;
			}
		}
		else {
			while(i < p && k < n)
				a[k++] = b[i++];
		}
	}
	
	public void mergeSort(int a[],int n)
	{
		int b[] = new int [n/2];
		int c[] = new int [n-n/2];
		int i, j;
		
		if(n>1) {
			
			for(i=0;i<n/2;i++)
				b[i]=a[i];
			for(i=n/2,j=0;i<n;i++,j++)
				c[j]=a[i];
			
			mergeSort(b, n/2);
			mergeSort(c, n-n/2);
			merge(b, c, a, n/2 ,n-n/2, n);
			
		}
	}
	
	//Function to check if the array is sorted
	private boolean checkIfSorted(int arr[]) {
		int n = arr.length;
		
		for(int i = 0; i < n-1; i++) {
			if(arr[i] > arr[i+1]) return false;
		}
		return true;
	}
	
	
}
