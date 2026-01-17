package myDeque;

import java.util.Arrays;

public class CircularDeque<T> {
    
    private T[] deque; // The circular array to store deque elements
    private int front;  // Index of the front of the deque
    private int rear;   // Index of the rear of the deque
    private int size;   // Current number of elements in the deque
    private final int capacity; // Maximum capacity of the deque
    
    @SuppressWarnings("unchecked")
    public CircularDeque(int capacity) {
    	//checks if the capacity is zero
    	if(capacity == 0) {
    		throw new IllegalArgumentException
    			("Capacity must be greater than 0.");
    	}
    	this.capacity = capacity;
    	//sets all to zero
    	front = 0;
    	rear = 0;
    	size = 0;
    	deque = (T[]) new Object[capacity];
    }
    
    public void addFront(T element) {
    	//checks if size is at capacity
    	if(size() == capacity) {
            throw new IllegalStateException("Deque is full.");
        }
    	//this will constantly decrease by one
    	//if front is currently 0, and capacity is 5
    	//it will be (0-1 + 5) % 5
    	//which should be 4%5 which results in index being 4.
        front = (front - 1 + capacity) % capacity;
        deque[front] = element;
        //increments size by 1
        size+=1;
    }
    
    public void addRear(T element) {
    	//checks if size is at capacity.
        if(size() == capacity) {
            throw new IllegalStateException("Deque is full.");
        }
        //set rear element before changing the rear index
        deque[rear] = element;
        //adds one to current rear index and mods
        //it to restart if it goes beyond the capacity
        rear = (rear + 1) % capacity;
        //increments by 
        size+=1;
    }
    
    public T removeFront() {
    	//checks if size is at zero, or empty
        if(size() == 0) {
            throw new IllegalStateException("Deque is empty.");
        }
        T element = deque[front];
        //changes the index in the same way addRear does 
        //it but with the front index
        front = (front + 1) % capacity;
        //minuses size by 1
        size-=1;
        return element;
    }
    
    public T removeRear() {
    	//checks if size is at zero or empty
        if(size() == 0) {
            throw new IllegalStateException("Deque is empty.");
        }
        //changes the index in the same way addFront
        //does but with the rear index
        rear = (rear - 1 + capacity) % capacity;
        T element = deque[rear];
        //subtracts size by 1
        size-=1;
        return element;
    }
    
    public T peekFront() {
    	//check if empty
        if(size() == 0) {
            throw new IllegalStateException("Deque is empty.");
        }
        return deque[front];
    }
    
    public T peekRear() {
    	//checks if empty
    	if(size() == 0) {
    		throw new IllegalStateException("Deque is empty.");
    	}
    	//checks if the index is 0
    	if(rear == 0) {
    		return deque[0];
    	}
    	//otherwise return rear - 1
    	return deque[rear-1];
    }
    
    public boolean isEmpty() {
    	//returns true or false whether or not if size is 0 or not
    	return size == 0;
    }
    
    public boolean isFull() {
    	//returns true or false whether or not if size is equal to capacity.
    	return size == capacity;
    }

    public void clear() {
    	//resets everything to 0 to allow for new 
    	//elements to added to the array without setting to null
    	size = 0;
    	rear = 0;
    	front = 0;
    }
    
    public int size() {
    	//made a copy to protect the original size
    	//returns the copySize variable
    	int copySize = this.size;
    	return copySize;
    }
    
    @Override
    public String toString() {
        return "CircularDeque [deque=" + Arrays.toString(deque)
        	+ ", front=" + front + ", rear=" + rear + ", size=" + size 
        	+ ", capacity=" + capacity + "]";
    }
}

