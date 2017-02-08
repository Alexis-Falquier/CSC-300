package algs13;
import stdlib.*;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;

/**
 * This is a skeleton file for your homework.
 * Complete the functions below. 
 * You may also edit the function "main" to test your code.
 * 
 * You should not use any loops or recursions, except in "delete"
 * Delete may use one loop or recursive helper.
 *
 * You must not add fields or static variables.
 * As always, you must not change the declaration of any method.
 * Do not change any of the methods I have provided, such as "toString" and "check".
 */

public class MyDeque {
	Node first = null;
	Node last = null;
	int N = 0;

	static class Node {
		public Node() { }
		public double item;
		public Node next;
		public Node prev;
	}

	public MyDeque ()         { };
	public boolean isEmpty () { return N == 0; }
	public int size ()        { return N; }

	public void pushLeft (double item) {
		Node x = new Node();
		x.prev = null;
		x.item = item;
		x.next = first;
		if (N == 0){
			last = x;
			first = x;
			N++;
			return;
		}
		first.prev = x;
		first = x;
		N++;
		//TODO
	}

	public void pushRight (double item) {
		Node x = new Node();
		x.prev = last;
		x.item = item;
		x.next = null;
		if (N == 0){
			first = x;
			last = x;
			N++;
			return;
		}
		last.next = x;
		last = x;
		N++;
		//TODO
	}

	public double popLeft () {
		if (N == 0) throw new NoSuchElementException ();
		Node x = first;
		if (N == 1){
			last = null;
			first = null;
			N = 0;
			return x.item;
		}
		first = x.next;
		first.prev = null;
		N--;
		return x.item;
		//TODO
	}

	public double popRight () {
		if (N == 0) throw new NoSuchElementException ();
		Node x = last;
		if (N == 1){
			last = null;
			first = null;
			N = 0;
			return x.item;
		}
		last = x.prev;
		last.next = null;
		N--;
		return x.item;
		//TODO
	}

	/* The concat method should take the Nodes from "that" and add them to "this"
	 * After execution, "that" should be empty.
	 * See the tests in the main program.
	 *
	 * Do not use a loop or a recursive definition.
	 * This method should create no new Nodes;
	 * therefore it should not call pushLeft or pushRight.
	 */
	public void concat (MyDeque that) {
		if (that.N == 0) return;
		if (N == 0){
			first = that.first;
			last = that.last;
			N += that.N;
			that.first = null;
			that.last = null;
			that.N = 0;
			return;
		}
		last.next = that.first;
		that.first.prev = last;
		last = that.last;
		N += that.N;
		that.first = null;
		that.last = null;
		that.N = 0;
		//TODO
	}

	/* Delete should delete and return the kth element from the left (where k is between 0 and N-1).
	 * See the tests in the main program.
	 *
	 * You may use a loop or a recursive definition here.
	 * This method should create no new Nodes;
	 * therefore it should not call pushLeft or pushRight.
	 */
	public double delete (int k) {
		if (k < 0 || k >= N) throw new IllegalArgumentException ();
		Node x = first;
        if (N == 1){
			first = null;
			last = null; 
			N = 0;
			return x.item;
		}
		for (int i = 0; i < k; i++) x = x.next;
		if (x == first){
			x.next.prev = null;
			first = x.next;
			N--;
			return x.item;
		}
		else if (x == last){
			x.prev.next = null;
			last = x.prev;
			N--;
			return x.item;
		}
		x.next.prev = x.prev;
		x.prev.next = x.next;
		N--;
		return x.item;
		// TODO 
	}

	public MyDeque (String s) {
		String[] nums = s.split (" ");
		for (int i = nums.length-1; i >= 0; i--) {
			try { 
				pushLeft (Double.parseDouble (nums[i]));			
			} catch (NumberFormatException e) {	}
		}
	}
	public String toString () { 
		DecimalFormat format = new DecimalFormat ("#.###");
		StringBuilder result = new StringBuilder ("[ ");
		for (Node x = first; x != null; x = x.next) {
			result.append (format.format (x.item));
			result.append (" ");
		}
		result.append ("]");
		return result.toString ();
	}

	static void showError (String message) {
		Trace.draw ();
		StdOut.println (message);
		//throw new Error (); // stops execution
	}
	public static void checkInvariants (String message, MyDeque that) {
		int N = that.N;
		MyDeque.Node first = that.first;
		MyDeque.Node last = that.last;

		if (N < 0) throw new Error ();
		if (N == 0) {
			if (first != null || last != null) {
				showError (String.format ("%s: Expected first,last == null.", message));
			}
		} else {
			if (first == null || last == null) {
				showError (String.format ("%s: Expected first,last != null.", message));
			}
		}
		if (N > 0) {
			MyDeque.Node prev = null;
			MyDeque.Node current = first;
			for (int i = 0; i < N; i++) {
				if (current == null) {
					showError (String.format ("%s: Expected %d next nodes, but got less.", message, N));
				}
				if (current.prev != prev) { 
					showError (String.format ("%s: Broken prev link.", message));
				}
				prev = current;
				current = current.next;
			}
			if (current != null) {
				showError (String.format ("%s: Expected %d next nodes, but got more.", message, N));
			}
			MyDeque.Node next = null;
			current = last;
			for (int i = 0; i < N; i++) {
				if (current == null) {
					showError (String.format ("%s: Expected %d prev nodes, but got less.", message, N));
				}
				if (current.next != next) {
					showError (String.format ("%s: Broken next link.", message));
				}
				next = current;
				current = current.prev;
			}
			if (current != null) {
				showError (String.format ("%s: Expected %d prev nodes, but got more.", message, N));
			}
		}
	}
	private static void check (String message, MyDeque actual, String expected) {
		checkInvariants (message, actual);
		if (expected != null) {
			if (!expected.equals (actual.toString ())) {
				showError ("Expected \"" + expected + "\", got \"" + actual + "\"");
			}
		}
	}
	private static void check (String message, MyDeque actual, String expected, double dActual, double dExpected) {
		if (dExpected != dActual) {
			showError ("Expected \"" + dExpected + "\", got \"" + dActual + "\"");
		}
		check (message, actual, expected);
	}
	// TODO: replace constructor with factory
	public static void main (String args[]) {
		//Trace.drawStepsOfMethod ("main");
		//Trace.run ();

		// Here are some tests to get you started.
		// You can edit this all you like.
		MyDeque d1, d2, d3;
		double k;

		////////////////////////////////////////////////////////////////////
		// push/pop tests
		////////////////////////////////////////////////////////////////////
		d1 = new MyDeque ();
		d1.pushLeft (11);
		check ("left", d1, "[ 11 ]");
		d1.pushLeft (12);
		check ("left", d1, "[ 12 11 ]");
		d1.pushLeft (13);
		check ("left", d1, "[ 13 12 11 ]");
		k = d1.popLeft ();
		check ("left", d1, "[ 12 11 ]", k, 13);
		k = d1.popLeft ();
		check ("left", d1, "[ 11 ]", k, 12);
		k = d1.popLeft ();
		check ("left", d1, "[ ]", k, 11);

		d1 = new MyDeque ();
		d1.pushRight (11);
		check ("right", d1, "[ 11 ]");
		d1.pushRight (12);
		check ("right", d1, "[ 11 12 ]");
		d1.pushRight (13);
		check ("right", d1, "[ 11 12 13 ]");
		k = d1.popRight ();
		check ("right", d1, "[ 11 12 ]", k, 13);
		k = d1.popRight ();
		check ("right", d1, "[ 11 ]", k, 12);
		k = d1.popRight ();
		check ("right", d1, "[ ]", k, 11);
		
		d1 = new MyDeque ();
		d1.pushLeft (11);
		check ("left/right", d1, "[ 11 ]");
		d1.pushRight (21);
		check ("left/right", d1, "[ 11 21 ]");
		d1.pushLeft (12);
		check ("left/right", d1, "[ 12 11 21 ]");
		d1.pushRight (22);
		check ("left/right", d1, "[ 12 11 21 22 ]");
		k = d1.popLeft ();
		check ("left/right", d1, "[ 11 21 22 ]", k, 12);
		k = d1.popLeft ();
		check ("left/right", d1, "[ 21 22 ]", k, 11);
		k = d1.popLeft ();
		check ("left/right", d1, "[ 22 ]", k, 21);
		k = d1.popLeft ();
		check ("left/right", d1, "[ ]", k, 22);
		
		d1 = new MyDeque ();
		d1.pushLeft (11);
		check ("left/right", d1, "[ 11 ]");
		d1.pushRight (21);
		check ("left/right", d1, "[ 11 21 ]");
		d1.pushLeft (12);
		check ("left/right", d1, "[ 12 11 21 ]");
		d1.pushRight (22);
		check ("left/right", d1, "[ 12 11 21 22 ]");
		k = d1.popRight ();
		check ("left/right", d1, "[ 12 11 21 ]", k, 22);
		k = d1.popRight ();
		check ("left/right", d1, "[ 12 11 ]", k, 21);
		k = d1.popRight ();
		check ("left/right", d1, "[ 12 ]", k, 11);
		k = d1.popRight ();
		check ("left/right", d1, "[ ]", k, 12);
		
		StdOut.println ("Finished push pop tests");

		////////////////////////////////////////////////////////////////////
		//  test exceptions
		////////////////////////////////////////////////////////////////////
		try {
			d1.popLeft ();
			showError ("Expected exception");
		} catch (NoSuchElementException e) {}
		try {
			d1.popRight ();
			showError ("Expected exception");
		} catch (NoSuchElementException e) {}

		////////////////////////////////////////////////////////////////////
		// concat tests (and more push/pop tests)
		////////////////////////////////////////////////////////////////////
		d1 = new MyDeque ();
		d1.concat (new MyDeque ());
		check ("concat", d1, "[ ]");
		d1.pushLeft (11);
		d1.concat (new MyDeque ());
		check ("concat", d1, "[ 11 ]");

		d1 = new MyDeque ();
		d2 = new MyDeque ();
		d2.pushLeft (11);
		d1.concat (d2);
		check ("concat", d1, "[ 11 ]");

		d1 = new MyDeque ();
		for (int i = 10; i < 15; i++) { d1.pushLeft (i); checkInvariants ("left", d1); }
		for (int i = 20; i < 25; i++) { d1.pushRight (i); checkInvariants ("right", d1); }
		check ("concat", d1, "[ 14 13 12 11 10 20 21 22 23 24 ]");
		d2 = new MyDeque ();
		d1.concat (d2);
		check ("concat", d1, "[ 14 13 12 11 10 20 21 22 23 24 ]");
		check ("concat", d2, "[ ]");

		for (int i = 30; i < 35; i++) { d2.pushLeft (i); checkInvariants ("left", d2); }
		for (int i = 40; i < 45; i++) { d2.pushRight (i); checkInvariants ("right", d2); }
		check ("concat", d2, "[ 34 33 32 31 30 40 41 42 43 44 ]");

		d3 = new MyDeque ();
		d2.concat (d3);
		check ("concat", d2, "[ 34 33 32 31 30 40 41 42 43 44 ]");
		check ("concat", d3, "[ ]");

		d1.concat (d2);
		check ("concat", d1, "[ 14 13 12 11 10 20 21 22 23 24 34 33 32 31 30 40 41 42 43 44 ]");
		check ("concat", d2, "[ ]");
		for (int i = 0; i < 20; i++) { d1.popLeft (); checkInvariants ("left", d1); }
		
		StdOut.println ("Finished concat tests");

		////////////////////////////////////////////////////////////////////
		// delete tests
		////////////////////////////////////////////////////////////////////
		d1 = new MyDeque ();
		d1.pushLeft (11);
		k = d1.delete (0);
		check ("delete", d1, "[ ]", k, 11);
		for (int i = 10; i < 20; i++) { d1.pushRight (i); checkInvariants ("right", d1); }
		k = d1.delete (0);
		check ("delete", d1, "[ 11 12 13 14 15 16 17 18 19 ]", k, 10);
		k = d1.delete (8);
		check ("delete", d1, "[ 11 12 13 14 15 16 17 18 ]", k, 19);
		k = d1.delete (4);
		check ("delete", d1, "[ 11 12 13 14 16 17 18 ]", k, 15);
		StdOut.println ("Finished tests");
	}
}
