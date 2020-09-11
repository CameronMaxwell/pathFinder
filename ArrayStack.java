import java.util.Arrays;

/**
 * @author Cameron Maxwell
 * this is a program that solves mazes using a stack that pushes and pops values.
 */

public class ArrayStack<T> implements ArrayStackADT<T> {

	private T[] stack;  	// store data items of the stack
	private int top;  	// stores the position of the last data item
	private int initialCapacity;  // This is the initial size of the array stack.
	private int sizeIncrease;  // When array stack full and new item to be added to it, size of the array will increase by this amount.
	private int sizeDecrease;  // if after removing a data item from array stack, if less than 1/4 the size of array, and size > initial capacity, will decrease by this amount.
	
	/**
	 * constructor for class ArrayStack taking in 
	 * @param initialCap
	 * @param sizeInc
	 * @param sizeDec
	 */
	public ArrayStack(int initialCap, int sizeInc, int sizeDec) {
		
		//stack = (T[])(new Object[initialCap]);
		
		top = -1;
		this.sizeIncrease = sizeInc; 
		this.sizeDecrease = sizeDec;
		this.initialCapacity = initialCap;
		
		stack = (T[])(new Object[initialCap]);
	}
	
	/**
	 * push adds a dataItem onto the top of the stack.
	 * @param dataItem
	 */
	public void push(T dataItem) {
		
		if (top == stack.length -1) {
			
		    extendCapacity();
		}
		top++;
	    stack[top] = dataItem;
		
	}

	/**
	 * this private method extends the capacity for an increased stack if stack is full.
	 */
	private void extendCapacity() {
		
	    T[] increasedStack = (T[])(new Object[stack.length + sizeIncrease]);
	    
	    for (int index = 0; index < stack.length; index ++) {
	    	increasedStack[index] = stack[index];
	    }
	   
	    stack = increasedStack;
	}
	
	/**
	 * pop throws exception that stack is empty (top = -1), otherwise removes top item from 
	 * the stack to continue search through a backtrack.
	 */
	public T pop() throws EmptyStackException {
		
		if (isEmpty()) {
			throw new EmptyStackException("Stack Empty");
		}

       T resultStack = stack[top];
        stack[top+1] = null;
        top--;

        if ((top+1) < (stack.length / 4) && (stack.length > initialCapacity)) {

            stack = Arrays.copyOf(stack, stack.length - sizeDecrease);
        }

        return resultStack;
        
	}
	
	/**
	 * Returns without removing the top element of this stack. 
	 * @return T data item on top of the stack
	 */
	public T peek() throws EmptyStackException {
				
		if (isEmpty()) {
			throw new EmptyStackException("Stack");
		}
		
		return stack[top];	  
	}
	
	/**
	 * Returns true if this stack contains no elements. 
   *   @return true if the stack is empty; false otherwise 
	 */
	public boolean isEmpty() {
		
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the number of data items in this stack. 
	 * @return int number of data items in this stack
	 */
	public int size() {
		
		return top + 1;
	}
	
	/**
	 * the length of the stack 
	 * @return the length
	 */
	public int length() {
		
		return stack.length;
	}
	
	/**
	 * Returns a string representation of this stack. 
	 * @return String representation of this stack
	 */
	public String toString() {
		
	    String output = "";

	    for (int element = 0; element < top; element++) {
	      output = output + stack[element] + ", ";
	    }

	    output = output + stack[top];
	    return ("Stack: " + output);
	}

}
