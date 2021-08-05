package sortvisualizer.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;
import javax.swing.JPanel;
import sortvisualizer.panels.InterfacePanel;

public class SortingVisualizer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static int width = 1280;
	private static int height = 600;
	
	
	private Random random = new Random();
	private boolean running = true;
	
	private String timeComplexity = "";
	private String sortName = "";
	private String description = "";
	private String description2 = "";
	private Font headFont;
	private Font descFont;

	public InterfacePanel interfacePanel;
	
	Keyboard key;

	public int arr[];
	public int prevarr[];
	
	private int barwidth = 3;
	private int arraysize = width/barwidth;

	private Color color = Color.white;
	
	private int olderSliderValue;
	private Color olderColorValue;

	private long delay = 12000000;
	private long shuffleDelay = delay / 5; 
	
	public SortingVisualizer() {
		init();
		initArray();
		
		key =  new Keyboard();
		this.setFocusable(true);
		this.requestFocus();
		addKeyListener(key);
		
		headFont = new Font("Book Antiqua", Font.BOLD, 25);
		descFont = new Font("Book Antiqua", Font.BOLD, 16);

		interfacePanel = new InterfacePanel();

	}
	
	private void init() {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.decode("0x454142"));
	}
	
	private void initArray() {
		arr = new int[arraysize];
		prevarr = new int[arraysize];
		for(int i = 0; i < arraysize; i++) {
			int r = random.nextInt(500);
			arr[i] = r;
			prevarr[i] = arr[i];
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(graphics);

		//*****************************TURN ON ANTIALIASING******************************************
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//*******************************************************************************************
		
		int x = 0;
		graphics.setFont(headFont);
		graphics.setColor(Color.white);
		graphics.drawString(sortName, 2, 20);
		graphics.drawString(timeComplexity, 2, 45);
		graphics.setFont(descFont);
		graphics.drawString(description, 2, 70);
		graphics.drawString(description2, 2, 90);
		
		graphics.setColor(interfacePanel.getComboBoxColorValue());
		
		for(int i = 0; i < arraysize; i++) {
			graphics.fillRect(x, height-arr[i], barwidth, arr[i]);
			x += barwidth;
		}
		for(int j = 0; j < arraysize; j++) {
			prevarr[j] = arr[j];
		}

		graphics.dispose();
	}

	public void run() {
		while(running) update();
	}
	
	public void shuffleArray() {
		for(int i = 0; i < arraysize; i++) {
			int r = random.nextInt(500);
			arr[i] = r; 
			prevarr[i] = arr[i];
			repaint();
			sleepFor(shuffleDelay);
		}
		sortName = "";
		repaint();
	}
	
	//Listen for key presses
	public synchronized void update() {
		if(key.m) {
			sortName = "Merge Sort";
			timeComplexity = "Time Complexity = O(nlogn)";
			description = "Merge Sort is a divide and conquer algorithm that was invented by John Von Neumann in 1945 .It is an efficient, general purpose and comparision-based sorting algorithm.";
			description2 = "We divide the unsorted array into n sub-arrays of one element each, and then merge the sub-arrays to produce new sorted sublists until only one sorted array is remaining.";
			mergeSort(arr, arraysize);
		}
		if(key.b) {
			sortName = "Bubble Sort";
			timeComplexity = "Time Complexity = O(n^2)";
			description = "Bubble sort, is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements and swaps them if they are in the wrong order.";
			description2 = "This simple algorithm performs poorly in real world use and is used primarily as an educational tool. Efficient for (quite) small data sets.";
			bubbleSort(arr);
		}
		if(key.s) {
			color = Color.white;
			sortName = "Shuffling the array....";
			description = "";
			description2 = "";
			timeComplexity = "";
			shuffleArray();
		}
		if(key.i) {
			sortName = "Insertion Sort";
			timeComplexity = "Time Complexity = O(n^2)";
			description = "Insertion sort iterates, consuming one input element each repetition, and grows a sorted output list. At each iteration, insertion sort removes one element from";
			description2 = "the input data, finds the location it belongs within the sorted list, and inserts it there. It repeats until no input elements remain. Efficient for (quite) small data sets.";
			insertionSort(arr);
		}
		if(key.q) {
			sortName = "Quick Sort";
			timeComplexity = "Time Complexity = O(nlogn)";
			description = "Quicksort was developed by British Computer scientist Tony Hoare in 1959. It is a divide-and-conquer algorithm which works by selecting a 'pivot'";
			description2= "and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the pivot. The sub-arrays are then sorted recursively.";
			quickSort(arr, 0, arraysize-1);
		}
		if(key.e) {
			sortName = "Selection Sort";
			timeComplexity = "Time Complexity = O(n^2)";
			description = "Selection sort is an in-place sorting algorithm. It is inefficient on large lists. However, it is noted for its simplicity and has";
			description2= "performance advantages over more complicated algorithms in certain situations, particularly where auxillary memory is limited.";
			selectionSort(arr);
		}

		key.update();
		
		//slider updation
		
		if(interfacePanel.getDensitySliderValue() != olderSliderValue) {
			barwidth = interfacePanel.getDensitySliderValue() / 10 +1;
			arraysize = width / barwidth;
			initArray();
			repaint();
		}
		
		if(interfacePanel.getComboBoxColorValue() != olderColorValue) 
			repaint();

		olderSliderValue = interfacePanel.getDensitySliderValue();
		olderColorValue = interfacePanel.getComboBoxColorValue();
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
			sleepFor(interfacePanel.getSpeedSliderValue());
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
				sleepFor(interfacePanel.getSpeedSliderValue());
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
             sleepFor(interfacePanel.getSpeedSliderValue());
	         arr[j + 1] = key;
	     }
	 }
	 
	 
	
	//Function to merge sort
	private void mergeSort(int arr[], int n)
	{
		if(checkIfSorted(arr)) return;
		long mergedelay = 0; 
		int curr_size;
					 
		int left_start;
						 
		 
		for (curr_size = 1; curr_size <= n-1;
					  curr_size = 2*curr_size)
		{
			 
			for (left_start = 0; left_start < n-1;
						left_start += 2*curr_size)
			{
				int mid = Math.min(left_start + curr_size - 1, n-1);
		 
				int right_end = Math.min(left_start
							 + 2*curr_size - 1, n-1);
		 
				merge(arr, left_start, mid, right_end);
				repaint();
				sleepFor(mergedelay);
				mergedelay += (interfacePanel.getSpeedSliderValue()/100);
			}
		}
	}
	 
	/* Function to merge the two haves arr[l..m] and
	arr[m+1..r] of array arr[] */
	private void merge(int arr[], int l, int m, int r)
	{

		int i, j, k;
		int n1 = m - l + 1;
		int n2 = r - m;
	 
		/* create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];
	 
		/* Copy data to temp arrays L[]
		and R[] */
		for (i = 0; i < n1; i++)
			L[i] = arr[l + i];
		for (j = 0; j < n2; j++)
			R[j] = arr[m + 1+ j];
	 
		/* Merge the temp arrays back into
		arr[l..r]*/
		i = 0;
		j = 0;
		k = l;
		while (i < n1 && j < n2)
		{
			if (L[i] <= R[j])
			{
				arr[k] = L[i];
				i++;
			}
			else
			{
				arr[k] = R[j];
				j++;
			}
			k++;
		}
	 
		/* Copy the remaining elements of
		L[], if there are any */
		while (i < n1)
		{
			arr[k] = L[i];
			i++;
			k++;
		}
	 
		/* Copy the remaining elements of
		R[], if there are any */
		while (j < n2)
		{
			arr[k] = R[j];
			j++;
			k++;
		}
	}
	
	//QUICK SORT
    private int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
 
        // index of smaller element
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;
 
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
			repaint();
			sleepFor((interfacePanel.getSpeedSliderValue() / 10) * 3);
        }
 
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
 
        return i + 1;
    }
 

    private void quickSort(int arr[], int l, int h)
    {
    	if(checkIfSorted(arr)) return;
        // Create an auxiliary stack
        int[] stack = new int[h - l + 1];
 
        // initialize top of stack
        int top = -1;
 
        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;
 
        // Keep popping from stack while is not empty
        while (top >= 0) {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];
 
            // Set pivot element at its correct position
            // in sorted array
            int p = partition(arr, l, h);
            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }
 
            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
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
