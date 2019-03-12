/**
 * @author zayn
 *
 */


import java.util.*;

import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;

class MinHeapPQ<Element extends Comparable<Element>> {
	private int capacity; // Max number of elements allowed in priority queue
	private int numOfKeys; // Number of keys associated with elements currently in priority queue
	private int [] pq; // pq = priority queue
	private int [] inv_pq; // inverse of pq. Used to reverse lookup from of index to its entry in pq[], so pq[inv_pq[i]] == i.
	private Element[] elems; // The index of the elem determines the priority, elem[keys] = priority of keys
	
	public MinHeapPQ(int capacity) {
		this.capacity = capacity;
		numOfKeys = 0; 
		elems = (Element[]) new Comparable[capacity+1]; // Make this the length of the capacity
		pq = new int[capacity+1];
		inv_pq = new int[capacity+1];
		for (int i = 0; i <= capacity; i++) {
			inv_pq[i] = -1; // Using the -1 as placeholder if i is not on the queue
		}
		
	}
	
	 public boolean isEmpty() {
	        return numOfKeys == 0;
	    }
	
	// Used to check if index is already taken when using insert
	public boolean contains(int i) {
        return inv_pq[i] != -1;
    }

	
	public void exchange(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		inv_pq[pq[i]] = i;
		inv_pq[pq[j]] = j;
		
	}
	
	public boolean greater(int i, int j) {
		return pq[i] > pq[j];
	}
	
	/***
	// Check which elem is greater
	public boolean greater(int i, int j) {
		return elems[pq[i]].compareTo(elems[pq[j]]) > 0;
	}
	***/
	
	public void swim(int k) {
		while(k > 1 && greater(k/2, k)) {
			exchange(k, k/2);
			k = k/2;
		}
	}
	
	public void sink(int k) {
		while(2*k <= numOfKeys) {
			int j = 2*k;
			if (j < numOfKeys && greater(j, j+1)) 
				j++;
			if(!greater(k, j))
				break;
			exchange(k, j);
			k = j;
		}
	}
	
	// Use key to determine index, and element are associated with keys
	public void insert(Element el, int key) {
		numOfKeys++;
		inv_pq[key] = numOfKeys;
		pq[numOfKeys] = key;
		elems[key] = el;
		swim(numOfKeys);
	}
	
	public int min() {
		return pq[1];
	}
	
	public int remove() {
		int minimum = pq[1];
		exchange(1, numOfKeys--);
		sink(1);
		assert minimum == pq[numOfKeys+1];
		inv_pq[minimum] = -1;  
		elems[minimum] = null;
		pq[numOfKeys+1] = -1;
		return minimum;
	}
	
	
	
	
}
