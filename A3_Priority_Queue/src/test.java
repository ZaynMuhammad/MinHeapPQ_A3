
public class test {
	
	 public static void main(String[] args) {
	        // insert a bunch of strings
	        String[] strings = { "b", "a", "c", "d", "e", "f", "g", "h", "i", "j" };

	        MinHeapPQ<String> pq = new MinHeapPQ<String>(strings.length);
	        for (int i = 0; i < strings.length; i++) {
	            pq.insert(strings[i], i);
	            System.out.println(i + " " + strings[i]);
	        }
	        
	        System.out.println();
	        System.out.println(pq.min());
	        
	        pq.remove();
	        
	        System.out.println(pq.min());
	        
	        pq.remove();
	        
	        System.out.println(pq.min());
	    }
	}

