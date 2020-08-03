/**
 * 
 * @author Katharina Schwab
 *
 */
public class DateFIFO {

	Date[] elements;
	int sizeOfElements = 0;

	DateFIFO(int startSize) {
		elements = new Date[startSize];
	}

	/**
	 * Adds a new element at the end of the list
	 *
	 * @param newElement
	 *            the element to add
	 *
	 */
	public void push(Date aDate) {
		if (sizeOfElements == elements.length) {
			Date[] temp = new Date[sizeOfElements + 10];
			System.arraycopy(elements, 0, temp, 0, elements.length);
			elements = temp;
		}

		elements[sizeOfElements] = aDate;
		sizeOfElements++;
	}

	/**
	 * Removes and returns the first element in the list
	 *
	 * @return the element that was the first in the list
	 *
	 */
	public Date pop() {
		Date toReturn = elements[0];

		for (int i = 0; i < sizeOfElements - 1; i++) {
			elements[i] = elements[i + 1];
		}
		elements[sizeOfElements - 1] = null;
		sizeOfElements--;

		return toReturn;
	}

	/**
	 * Searches the list for the given date and returns the index of the date
	 * within the list or -1 if the given date is not included in the list. Two
	 * dates are equal, if all of their attribute values are equal.
	 *
	 * @return the index within the list for the given date, or -1 if the given
	 *         date is not included within the list
	 *
	 */
	public int find(Date aDate) {
		for (int index = 0; index < elements.length - 1; index++) {
			if (elements[index].toString().equals(aDate.toString())) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * Returns but does not remove the first element in the list
	 *
	 * @return the first element, or null, if there is no such element
	 *
	 */
	public Date peek() {
		if (isEmpty()) {
			return null;
		} 
		
		else 
		{
			return elements[0];
		}
	}

	/**
	 * Tests if this list is empty
	 *
	 * @return true if this list contains no elements; false otherwise
	 */
	public boolean isEmpty() {
		if (sizeOfElements == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Creates a deep copy of the current DateFIFO Object. This means that the
	 * elements within the list must be copied too, not only the references.
	 *
	 * @return a deep copy of this DateFIFO Object
	 */
	public DateFIFO deepCopy() {
		DateFIFO deepCopy = new DateFIFO(elements.length);
		for (int i = 0; i < elements.length - 1; i++) {
			Date temp = new Date(elements[i]);
			deepCopy.push(temp);
		}
		// System.arraycopy(this.elements, 0, deepCopy, 0,
		// this.elements.length);
		return deepCopy;
	}

	public static void main(String[] args) {
		DateFIFO testFIFO_1 = new DateFIFO(20);
		int day = 1;
		int month = 3;
		int year = 2016;
		for (int i = 0; i < 20; i++) {
			day = day + 1;
			testFIFO_1.push(new Date(day, month, year));
		}

		System.out.println("Test for peek()");
		System.out.println("Erstes Element: " + testFIFO_1.peek());

		System.out.println("Test for pop()");
		Date test1 = new Date();
		test1 = testFIFO_1.pop();
		System.out.println("Erstes Element: " + testFIFO_1.peek());
		System.out.println("Entferntes Datum: " + test1);

		System.out.println("Test for find(date)");
		day = 23;
		month = 5;
		year = 2001;
		Date test2 = new Date(day, month, year);
		int index = testFIFO_1.find(test2);
		System.out.println("Zu suchendes Datum: " + test2);
		System.out.println("Indexposition oder -1: " + index);

		day = 5;
		month = 3;
		year = 2016;
		Date test3 = new Date(day, month, year);
		index = testFIFO_1.find(test3);
		System.out.println("Zu suchendes Datum: " + test3);
		System.out.println("Indexposition oder -1: " + index);

		System.out.println("Test for isEmpty()");
		boolean result = testFIFO_1.isEmpty();
		System.out.println("Erwartet wird false: " + result);
		DateFIFO testFIFO_2 = new DateFIFO(20);
		result = testFIFO_2.isEmpty();
		System.out.println("Erwartet wird true: " + result);

		System.out.println("Test for deepCopy()");
		DateFIFO testFIFO_3 = new DateFIFO(20);
		testFIFO_3 = testFIFO_1.deepCopy();
		System.out.println("Erstes Element des Orginals: " + testFIFO_1.peek());
		System.out.println("Erstes Element der Kopie: " + testFIFO_3.peek());

		test1 = testFIFO_1.pop();
		System.out.println("Entferntes Datum aus Orginal: " + test1);

		System.out.println("Neues erstes Element des Orginals: " + testFIFO_1.peek());
		System.out.println("Erstes Element der Kopie: " + testFIFO_3.peek());

	}

}
