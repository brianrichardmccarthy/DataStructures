import java.util.ArrayList;

public class CustomStack<T> {

	private ArrayList<T> stack;

	/**
	 * Constructor.
	 */
	public CustomStack() {
		stack = new ArrayList<>();
	}

	/**
	 * If the stack is empty return null. Else remove and return the top element
	 * of the stack.
	 * 
	 * @return T element
	 */
	public T pop() {
		return (isEmpty()) ? null : stack.remove(stack.size() - 1);
	}

	/**
	 * If the stack is empty return null. Else return the top element of the
	 * stack.
	 * 
	 * @return T element
	 */
	public T peek() {
		return (isEmpty()) ? null : stack.get(stack.size() - 1);
	}

	/**
	 * 
	 * Push object onto the stack
	 * 
	 * @param t
	 *            (object)
	 */
	public void push(T t) {
		stack.add(t);
	}

	/**
	 * Returns the size of the stack
	 * 
	 * @return int
	 */
	public int size() {
		return stack.size();
	}

	/**
	 * Returns true if the stack size is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	@Override
	public String toString() {
		String string = "[ ";

		for (T element : stack) {
			string += element + ", ";
		}

		return string + "]";
	}

}
