package myDeque;
import java.lang.reflect.Array;

public class Util {
	
	public static boolean isPalindrome(String s) {
	    //create a CircularDeque to store characters from the string
	    CircularDeque<Character> deque = new CircularDeque<>(s.length());

	    //create an empty string to hold letters and digits only
	    String result = "";
	    //loop through the string and add letters/digits to result
	    for (char c : s.toCharArray()) {
	        if (Character.isLetterOrDigit(c)) {
	            result += Character.toLowerCase(c);
	        }
	    }
	    //add each character of result to the deque
	    for (char c : result.toCharArray()) {
	        deque.addRear(c);
	    }
	    //compare characters from the front and back of the deque
	    while (deque.size() > 1) {
	        if (deque.removeFront() != deque.removeRear()) {
	            return false;
	        }
	    }
	    //return true if all characters match
	    return true;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T[] slidingWindowMax(T[] data, int k) {
	    /*
	     * check if the input array is null, empty, or if the window size is invalid 
	     * (≤ 0). If any of these conditions are met, return an empty array.
	     */
	    if (data == null || k <= 0 || data.length == 0 || k >= data.length) {
	        return (T[]) Array.newInstance(data.getClass().getComponentType(), 0);
	    }
	    /*
	     * create a CircularDeque to store indices of elements in the current 
	     * sliding window. the deque maintains the indices of potential max values.
	     */
	    CircularDeque<Integer> cd = new CircularDeque<>(k);
	    /*
	     * the result array stores the maximum values for each sliding window. 
	     * its size is (data.length - k + 1) because that's how many windows fit.
	     */
	    T[] result = (T[]) Array.newInstance(
	        data.getClass().getComponentType(), data.length - k + 1);
	    for (int i = 0; i < data.length; i++) {
	        /*
	         * remove elements from the front of the deque if they are outside 
	         * the current window. the condition (cd.peekFront() < i - k + 1) 
	         * ensures that we only keep indices within the valid window range.
	         */
	        if (!cd.isEmpty() && cd.peekFront() < i - k + 1) {
	            cd.removeFront();
	        }
	        /*
	         * maintain a decreasing order in the deque.
	         * remove elements from the rear if they are smaller than or equal 
	         * to data[i]. this ensures that the largest element remains at the front.
	         */
	        while (!cd.isEmpty() && data[cd.peekRear()].compareTo(data[i]) <= 0) {
	            cd.removeRear();
	        }
	        //Add the current index to the deque
	        cd.addRear(i);
	        /*
	         * once it loops at least "k" elements, its creates a valid window.
	         * the front of the deque (peekFront) always contains the index of 
	         * the maximum element. store that max value in the result array.
	         */
	        if (i >= k - 1) {
	            result[i - k + 1] = data[cd.peekFront()];
	        }
	    }
	    //return the array containing maximum values for each window
	    return result;
	}
}


