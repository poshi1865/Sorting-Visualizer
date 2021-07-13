package sortvisualizer.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

public class SortingVisualizer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static int width = 1280;
	private static int height = 600;
	
	private Random random = new Random();
	private boolean running = true;
	
	String title = "Sorting Visualizer";
	
	Keyboard key;

	public int arr[];
	public int prevarr[];
	
	private int barwidth = 10;
	private int arraysize = width/barwidth;

	private Color color = Color.white;

	private long delay = 80000000;
	
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
		setBackground(Color.gray);
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
		
		int x = 0;
		graphics.setColor(color);
		for(int i = 0; i < arraysize; i++) {
			if(arr[i] != prevarr[i]) {
				graphics.setColor(Color.white);
				graphics.fillRect(x, height-arr[i], barwidth, arr[i]);
			}
			else 
			graphics.fillRect(x, height-arr[i], barwidth, arr[i]);
			x += barwidth;
		}
		for(int i = 0; i < arraysize; i++) {
			prevarr[i] = arr[i];
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
			sleepFor(10000000);
		}
	}
	
	//Listen for key presses
	public synchronized void update() {
		if(key.m) {
			mergeSort(arr, arraysize);
		}
		if(key.b) {
			bubbleSort(arr);
		}
		if(key.s) {
			color = Color.white;
			shuffleArray();
		}
		if(key.i) {
			insertionSort(arr);
		}
		if(key.q) {
			quickSort(arr, 0, arraysize-1);
		}
		if(key.e) {
			selectionSort(arr);
		}

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
			sleepFor(delay);
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
				sleepFor(delay);
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
             sleepFor(delay);
	         arr[j + 1] = key;
	     }
	 }
	 
	 
	
	//Function to merge sort
	private void mergeSort(int arr[], int n)
	{
		int mergedelay = 20000000; 
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
				mergedelay += 900000;
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
			sleepFor(7000000);
        }
 
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
 
        return i + 1;
    }
 

    private void quickSort(int arr[], int l, int h)
    {
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
