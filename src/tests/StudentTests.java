package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


import  myDeque.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	//CircularDeque class
	@Test
	public void testingCircularDeque() {
		int cap = 5;
		int zero = 0;
		//check if capacity is 0
		try {
			CircularDeque<Integer> cd = new CircularDeque<>(zero);
		} catch(Exception e) {
			assertEquals("Capacity must be greater than 0.", e.getMessage());
		}
	}
	
	@Test
	public void testingAddPeekRemoveFront() {
		int cap = 3;
		CircularDeque<Integer> cd = new CircularDeque<>(cap);
		cd.addFront(1);
		cd.addFront(2);
		cd.addFront(3);
		//testing for full exception
		try {
			cd.addFront(4);
		} catch(Exception e) {
			assertEquals("Deque is full.", e.getMessage());
		}
		//testing size and if peekFront works
		assertEquals(cd.peekFront(), (Integer)3);
		assertEquals(cd.size(), 3);
		//testing if remove works
		cd.removeFront();
		assertEquals(cd.peekFront(), (Integer)2);
		assertEquals(cd.size(), 2);
		//testing if removeFront and peekFront will detect if deque is empty
		cd.removeFront();
		cd.removeFront();
		try {
			cd.removeFront();
		} catch(Exception e) {
			assertEquals("Deque is empty.", e.getMessage());
		}
		try {
			cd.peekFront();
		} catch(Exception e) {
			assertEquals("Deque is empty.", e.getMessage());
		}
	}
	@Test
	public void testingAddPeekRemoveRear() {
		int cap = 3;
		CircularDeque<Integer> cd = new CircularDeque<>(cap);
		cd.addRear(1);
		cd.addRear(2);
		cd.addRear(3);
		//testing for full exception
		try {
			cd.addRear(4);
		} catch(Exception e) {
			assertEquals("Deque is full.", e.getMessage());
		}
		//testing size and if peekRear works
		assertEquals(cd.peekRear(), (Integer)1);
		assertEquals(cd.size(), 3);
		//testing if remove works
		cd.removeRear();
		assertEquals(cd.peekRear(), (Integer)2);
		assertEquals(cd.size(), 2);
		//testing if removeRear and peekRear will detect if deque is empty
		try {
			cd.removeRear();
		} catch(Exception e) {
			assertEquals("Deque is empty.", e.getMessage());
		}
		try {
			cd.peekRear();
		} catch(Exception e) {
			assertEquals("Deque is empty.", e.getMessage());
		}
	}
	
	@Test
	public void testingFrontAndRear() {
		int cap = 5;
		CircularDeque<Integer> cd = new CircularDeque<>(cap);
		//adding to front and rear
		cd.addFront(1);
		cd.addRear(2);
		cd.addFront(3);
		cd.addRear(4);
		cd.addFront(5);
		//testing for full exception
		try {
			cd.addRear(6);
		} catch(Exception e) {
			assertEquals("Deque is full.", e.getMessage());
		}
		//testing if it pulls the correct integer
		assertEquals(cd.peekRear(), (Integer)4);
		assertEquals(cd.size(), 5);
		cd.removeRear();
		//testing if the peek is correct after removing an element
		assertEquals(cd.peekFront(), (Integer)5);
		assertEquals(cd.peekRear(), (Integer)2);
		assertEquals(cd.size(), 4);
	}
	
	@Test
	public void testingIsEmptyAndClear() {
		int cap = 3;
		CircularDeque<Integer> cd = new CircularDeque<>(cap);
		//testing before adding elements
		assertTrue(cd.isEmpty());
		cd.addFront(1);
		cd.addFront(1);
		//testing after adding elements
		assertFalse(cd.isEmpty());
		cd.clear();
		//testing after clear
		assertTrue(cd.isEmpty());
	}
	
	@Test
	public void testingIsFull() {
		int cap = 3;
		CircularDeque<Integer> cd = new CircularDeque<>(cap);
		//testing when empty
		assertFalse(cd.isFull());
		cd.addFront(1);
		cd.addFront(2);
		cd.addFront(3);
		//testing after adding all the elements
		assertTrue(cd.isFull());
		cd.clear();
		//testing after clear
		assertFalse(cd.isFull());
	}
	
	@Test
	public void testingSize() {
		int cap = 3;
		CircularDeque<Integer> cd = new CircularDeque<>(cap);
		//testing size if empty
		assertEquals(cd.size(), 0);
		cd.addFront(1);
		cd.addFront(2);
		//testing size after adding elements
		assertEquals(cd.size(), 2);
		//testing size after clear
		cd.clear();
		assertEquals(cd.size(), 0);
	}
	
	@Test
	public void testingToString() {
		int cap = 3;
		CircularDeque<Integer> cd = new CircularDeque<>(cap);
		//testing when not full capacity
		cd.addFront(1);
		cd.addRear(2);
		String answer1 = "CircularDeque [deque=[2, null, 1]"
    	+ ", front=2, rear=1, size=2, capacity=3]";
		assertEquals(answer1, cd.toString());
		//testing when cleared
		cd.clear();
		String answer2 = "CircularDeque [deque=[2, null, 1]"
		    	+ ", front=0, rear=0, size=0, capacity=3]";
		assertEquals(answer2, cd.toString());
		//replacing all elements with null
		cd.addFront(null);
		cd.addRear(null);
		cd.clear();
		String answer3 = "CircularDeque [deque=[null, null, null]"
		    	+ ", front=0, rear=0, size=0, capacity=3]";
		assertEquals(answer3, cd.toString());
	}
	
	//Util Class
	@Test
	public void testingIsPalindrome() {
		//all tests will include special and random case
		String non = "HeLlo.";
		String stdPal = "tACo.cAT";
		String weirdPal = "Draw, O Caesar, eraSe a coward.";
		//testing non-palindrome
		assertFalse(Util.isPalindrome(non));
		//testing standard palindrome
		assertTrue(Util.isPalindrome(stdPal));
		//testing longer sentence palindrome
		assertTrue(Util.isPalindrome(weirdPal));
	}
	
	@Test
	public void testingSlidingWindowMax() {
		//testing valid num array
		Integer[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		int window = 5;
		Integer[] answer1 = {5, 6, 7, 8, 9, 9};
		Integer[] result1 = Util.slidingWindowMax(num, window);
		assertArrayEquals(result1, answer1);
		//testing empty num array
		Integer[] empty = {};
		Integer[] result2 = Util.slidingWindowMax(empty, window);
		Integer[] answer2 = {};
		assertArrayEquals(result2, answer2);
		//testing negative window size
		Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		int invalidWindow = -2;
		Integer[] answer3 = {};
		Integer[] result3 = Util.slidingWindowMax(arr, invalidWindow);
		assertArrayEquals(result3, answer3);
		//testing 0 window size
		Integer[] zeroArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		int zero = 0;
		Integer[] answer4 = {};
		Integer[] result4 = Util.slidingWindowMax(zeroArr, zero);
		assertArrayEquals(result4, answer4);
	}
}