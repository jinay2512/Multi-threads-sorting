/**
 * 
 */
package com.multithread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author JINAY JANI
 *
 */
public class ThreadManager {

		private static Scanner scan;

		/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Creating arrays
		Integer array[];
	    Integer[] firstCollArr;
		Integer[] secondCollArr;
	    array = new Integer[20];
	    firstCollArr = new Integer[array.length/2];
	    secondCollArr = new Integer[array.length/2];
	    
	    scan = new Scanner(System.in);
		System.out.print("Enter Unsorted Array of length "+array.length+" :\t " );
	    for (int i = 0; i < array.length; i++) {
	      array[i] = scan.nextInt();
	     }
	    System.out.println();
	    
	    //Splitting the array in two
	    for (int i = 0; i < array.length/2; i++) {
	     firstCollArr[i] = array[i];
	     secondCollArr[i] = array[i+array.length/2];
	    }
	    
	    // Printing First Sub-Array
	    System.out.print("First Sub-Array : " );
	    for (int i = 0; i < firstCollArr.length; i++) {
	      System.out.print("\t" + firstCollArr[i]);
	    }
	    System.out.println();

	    // Printing Second Sub-Array
	    System.out.print("Second Sub-Array : " );
	    for (int i = 0; i < secondCollArr.length; i++) {
	      System.out.print("\t" + secondCollArr[i]);
	    }
	    System.out.println();
		
	    SortingEngine firstCollectionEngine = new SortingEngine(firstCollArr);
		SortingEngine secondCollectionEngine = new SortingEngine(secondCollArr);
		
		
		Thread t1 = new Thread(firstCollectionEngine);
		Thread t2 = new Thread(secondCollectionEngine);
		
		// Calling start() method to start executing the thread 1, thread 2 and thread 3
	    try {
	    	
			System.out.println();
			System.out.println("Run thread: "+t1.getName());
		    t1.start();
			
			System.out.println();
			System.out.println("Run thread: "+t2.getName());
		    t2.start();
		    
		    // Making sure the threads are complete
			t1.join();
			t2.join();
			
			Integer[] mergedFirstArr = firstCollectionEngine.sortedNumberItems;
			Integer[] mergedSecondArr = secondCollectionEngine.sortedNumberItems;
			
			System.out.println();
			System.out.println("First Sorted Collection : "+Arrays.asList(mergedFirstArr));
			System.out.println("Second Sorted Collection : "+Arrays.asList(mergedSecondArr));
			
			Integer[] mergedArr = mergeArrays(mergedFirstArr, mergedSecondArr);
			
			SortingEngine thirdCollectionEngine = new SortingEngine(mergedArr);
			Thread t3 = new Thread(thirdCollectionEngine);
			System.out.println();
			System.out.println("Run thread: "+t3.getName());
			t3.start();
			t3.join();
			
			Integer[] mergedSortedArr = thirdCollectionEngine.sortedNumberItems;
			System.out.println();
			System.out.println("Merged Sorted Collection : "+Arrays.asList(mergedSortedArr));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}

	/**
	 * @param tempArr
	 * @param tempArr2
	 */
	private static Integer[] mergeArrays(Integer[] tempArr, Integer[] tempArr2) {
		List<Integer> tempList = new ArrayList<Integer>(Arrays.asList(tempArr));
		tempList.addAll(Arrays.asList(tempArr2));
		
		Object[] mergedObjArr = tempList.toArray();
		Integer[] mergedArr = Arrays.copyOf(mergedObjArr, mergedObjArr.length, Integer[].class);
		System.out.println();
		System.out.println("Merged Unsorted Collection: "+Arrays.toString(mergedArr));
		return mergedArr;
	}
}
