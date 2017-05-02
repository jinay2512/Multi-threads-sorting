/**
 * 
 */
package com.multithread;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author JINAY JANI
 *
 */
	//Creating Thread
public class SortingEngine implements Runnable{

	private Integer[] numberItems;
	public Integer[] sortedNumberItems;
	
	/**
	 * Constructor
	 */
	public SortingEngine(Integer[] numberItems) {
		this.numberItems = numberItems;
	}
	 //Using Collection.sort interface (comparator)
	private Integer[] sortNumberItems(){
		List<Integer> numbersList= Arrays.asList(this.numberItems);
		Collections.sort(numbersList);
		this.sortedNumberItems = (Integer[]) numbersList.toArray();
		return this.sortedNumberItems;
	}

	@Override
	public void run() {
		System.out.println("Starting thread");
		this.sortNumberItems();
		System.out.println("Thread Completed");
	}

}
