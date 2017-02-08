package algs13;
import java.text.DecimalFormat;
import stdlib.*;

/* 
 * Complete the methods below.
 * None of the methods should modify the list, unless that is the purpose of the method.
 * 
 * You may not add any fields to the node or list classes.
 * You may not add any methods to the node class.
 * 
 * You MAY add private methods to the list class (helper functions for the recursion). 
 */
public class MyLinked1 {
	static class Node {
		public Node (double item, Node next) { this.item = item; this.next = next; }
		public double item;
		public Node next;
	}
	Node first;


	// write a function to compute the size of the list, using a loop
	// empty list has size 0
	public int sizeLoop () {
		int i = 0;
		Node x = first;
		while (x != null){
			i++;
			x = x.next;
		}
		return i; //TODO: fix this
	}

	// write a function to compute the size of the list, using an optimistic, forward recursion
	// empty list has size 0
	public int sizeForward () {
		return sizeForwardHelper(first, 0);
		 //TODO: fix this
	}
	
	private static int sizeForwardHelper(Node x, int i){
		if (x != null){
			i++;
			i=sizeForwardHelper(x.next, i);
		}
		return i;
		}

	// write a function to compute the size of the list, using an optimistic, backward recursion
	// empty list has size 0
	public int sizeBackward () {
		return sizeBackwardHelper(first); //TODO: fix this
	}
	
	private static int sizeBackwardHelper(Node x){
		if (x == null) return 0;
		int i = sizeBackwardHelper(x.next);
		i++;
		return i;
	}
	

	// compute the position of the first 5.0 in the list, counting as an offset from the beginning.  
	// if 5.0 is the FIRST element, the position is 0
	// if 5.0 does not appear, return a negative number
	// you can write this using a loop or recursion, in any style, but you should only have one loop or recursive helper
	// I would expect
	// [0,1,2,5,5,5,5,5,8,9].positionOfFirstFiveFromBeginning() == 3
	// [0,1,2,5,5,5,5,5,8,9].positionOfLastFiveFromEnd() == 2
	public int positionOfFirstFiveFromBeginning () {
		int i = 0;
		Node x = first;
		while (x != null){
			if (x.item == 5.0) return i;
			i++;
			x = x.next;
		}
		return -1; //TODO: fix this
	}

	// compute the position of the last 5.0 in the list, counting as an offset from the end.  
	// if 5.0 is the LAST element, the position is 0
	// if 5.0 does not appear, return a negative number
	// you can write this using a loop or recursion, in any style, but you should only have one loop or recursive helper
	// Hint: Use a backward recursion.  
	// Hint: If the number does not appear, return the distance to the END of the list as a NEGATIVE number.
	public int positionOfLastFiveFromEnd () {
		return lastFiveFromEndHelper(first); //TODO: fix this
	}
	
	private static int lastFiveFromEndHelper(Node x){
		if (x == null) return -1;
		int result = lastFiveFromEndHelper(x.next);
		if (result >= 0){
			return result;
		}
		if(x.item == 5.0){
			result = Math.abs(result + 1);
			return result;
		} 
		result--;
		return result;
	}

	// delete the first element
	public void deleteFirst () {
		//Node x = first;
		if (first == null) return;
		//if (x.next == null)first = null;
		first = first.next;
		// TODO
	}

	
	public static void main (String args[]) {
		//mainDebug ();
		mainRunTests ();
	}
	private static void mainDebug () {
		// Use this for debugging!
		// Add the names of helper functions if you use them.
		Trace.drawStepsOfMethod ("sizeLoop");
		Trace.drawStepsOfMethod ("sizeForward");
		Trace.drawStepsOfMethod ("sizeBackward");
		Trace.drawStepsOfMethod ("positionOfFirstFiveFromBeginning");
		Trace.drawStepsOfMethod ("positionOfLastFiveFromEnd");
		Trace.drawStepsOfMethod ("deleteFirst");
		Trace.run (); 
		// TODO:  Put the test here you want to debug:
		testSizeLoop (4, "11 -21.2 31 41");
		testDeleteFirst ("0 1 2 5 5 5 5 5 8 9");
	}
	private static void mainRunTests () {
		testSizeLoop (0, "");
		testSizeLoop (1, "11");
		testSizeLoop (2, "11 21");	
		testSizeLoop (4, "11 -21.2 31 41");

		testSizeForward (0, "");
		testSizeForward (1, "11");
		testSizeForward (2, "11 21");	
		testSizeForward (4, "11 -21.2 31 41");

		testSizeBackward (0, "");
		testSizeBackward (1, "11");
		testSizeBackward (2, "11 21");	
		testSizeBackward (4, "11 -21.2 31 41");

		testPositionOfFirstFiveFromBeginning (-1, "");
		testPositionOfFirstFiveFromBeginning (-1, "11");
		testPositionOfFirstFiveFromBeginning (-1, "11 21 31 41");
		testPositionOfFirstFiveFromBeginning (0, "5 11 21 31 41");
		testPositionOfFirstFiveFromBeginning (1, "11 5 21 31 41");
		testPositionOfFirstFiveFromBeginning (2, "11 21 5 31 41");
		testPositionOfFirstFiveFromBeginning (3, "11 21 31 5 41");
		testPositionOfFirstFiveFromBeginning (4, "11 21 31 41 5");
		testPositionOfFirstFiveFromBeginning (3, "0 1 2 5 5 5 5 5 8 9");

		testPositionOfLastFiveFromEnd (-1, "");
		testPositionOfLastFiveFromEnd (-2, "11");
		testPositionOfLastFiveFromEnd (-5, "11 21 31 41");
		testPositionOfLastFiveFromEnd (4, "5 11 21 31 41");
		testPositionOfLastFiveFromEnd (3, "11 5 21 31 41");
		testPositionOfLastFiveFromEnd (2, "11 21 5 31 41");
		testPositionOfLastFiveFromEnd (1, "11 21 31 5 41");
		testPositionOfLastFiveFromEnd (0, "11 21 31 41 5");
		testPositionOfLastFiveFromEnd (2, "0 1 2 5 5 5 5 5 8 9");
		testPositionOfLastFiveFromEnd (-11, "0 1 2 2 2 2 2 2 8 9");
		
		testDeleteFirst ("");
		testDeleteFirst ("11");
		testDeleteFirst ("11 21 31 41");
		testDeleteFirst ("5 11 21 31 41");
		testDeleteFirst ("11 5 21 31 41");
		testDeleteFirst ("11 21 5 31 41");
		testDeleteFirst ("11 21 31 5 41");
		testDeleteFirst ("11 21 31 41 5");
		testDeleteFirst ("0 1 2 5 5 5 5 5 8 9");
		
		StdOut.print("Finished Tests");
	}
	

	/* ToString method to print */
	public String toString () { 
		// Use DecimalFormat #.### rather than String.format 0.3f to leave off trailing zeroes
		DecimalFormat format = new DecimalFormat ("#.###");
		StringBuilder result = new StringBuilder ("[ ");
		for (Node x = first; x != null; x = x.next) {
			result.append (format.format (x.item));
			result.append (" ");
		}
		result.append ("]");
		return result.toString ();
	}

	/* Method to create lists */
	public static MyLinked1 of(String s) {
		Node first = null;
		String[] nums = s.split (" ");
		for (int i = nums.length-1; i >= 0; i--) {
			try { 
				double num = Double.parseDouble (nums[i]); 
				first = new Node (num, first);  
			} catch (NumberFormatException e) {
				// ignore anything that is not a double
			}
		}
		MyLinked1 result = new MyLinked1 ();
		result.first = first;
		return result;
	}

	// lots of copy and paste in these test!
	private static void testSizeLoop (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.sizeLoop();
		if (expected != actual) {
			StdOut.format ("Failed %s.sizeLoop(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.sizeLoop(): List changed to %s\n", sStart, sEnd);
		}
	}	
	private static void testSizeForward (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.sizeForward ();
		if (expected != actual) {
			StdOut.format ("Failed %s.sizeForward(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.sizeForward(): List changed to %s\n", sStart, sEnd);
		}
	}	
	private static void testSizeBackward (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.sizeBackward ();
		if (expected != actual) {
			StdOut.format ("Failed %s.sizeBackward(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.sizeBackward(): List changed to %s\n", sStart, sEnd);
		}
	}
	private static void testPositionOfFirstFiveFromBeginning (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.positionOfFirstFiveFromBeginning ();
		//if ((expected >= 0 && expected != actual) || (expected < 0 && actual > 0)) {
		if (expected != actual) {
			StdOut.format ("Failed %s.positionOfFirstFiveFromBeginning(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.positionOfFirstFiveFromBeginning(): List changed to %s\n", sStart, sEnd);
		}
	}
	private static void testPositionOfLastFiveFromEnd (int expected, String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		String sStart = list.toString ();
		int actual = list.positionOfLastFiveFromEnd ();
		//if ((expected >= 0 && expected != actual) || (expected < 0 && actual > 0)) {
		if (expected != actual) {
			StdOut.format ("Failed %s.testPositionOfLastFiveFromEnd(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
		}
		String sEnd = list.toString ();
		if (! sStart.equals (sEnd)) {
			StdOut.format ("Failed %s.testPositionOfLastFiveFromEnd(): List changed to %s\n", sStart, sEnd);
		}
	}
	
	private static void testDeleteFirst (String sList) {
		MyLinked1 list = MyLinked1.of (sList);
		list.deleteFirst();
		StdOut.print("Original: [ " + sList + " ] ");
		StdOut.print("Delete First Result: " + list + "\n");
	}
}
