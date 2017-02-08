package algs11;

import java.util.Arrays;
import stdlib.*;

/**
 * This is a skeleton file for your homework. Edit the sections marked TODO. You
 * may add new functions. You may also edit the function "main" to test your
 * code.
 *
 * You must not add static variables. You MAY add static functions, just not
 * static variables.
 *
 * It is okay to add functions, such as
 *
 * <pre>
 *     public static double sumHelper (double[] list, int i, double sumSoFar) {
 * </pre>
 *
 * but it is NOT okay to add static variables, such as
 *
 * <pre>
 * public static int x;
 * </pre>
 *
 * As for homework 1, you must not change the declaration of any method.
 * 
 * You can edit the main function all you want. I will not run your main
 * function when grading.
 */
public class MySecondHomework {

	/**
	 * As a model, here is a minValue function, both iteratively and recursively
	 */
	/** iterative version */
	public static double minValueI (double[] list) {
		double result = list[0];
		int i = 1;
		while (i < list.length) {
			if (list[i] < result) result = list[i];
			i = i + 1;
		}
		return result;
	}

	/** recursive version */
	public static double minValue (double[] list) {
		return minValueHelper (list, 1, list[0]);
	}
	private static double minValueHelper (double[] list, int i, double result) {
		if (i < list.length) {
			if (list[i] < result) result = list[i];
			result = minValueHelper (list, i + 1, result);
		}
		return result; 
	}

	/**
	 * PROBLEM 1: Translate the following sum function from iterative to
	 * recursive.
	 *
	 * You should write a helper method. You may not use any "fields" to solve
	 * this problem (a field is a variable that is declared "outside" of the
	 * function declaration --- either before or after).
	 */
	public static double sumI (double[] a) {
		double result = 0.0;
		int i = 0;
		while (i < a.length) {
			result = result + a[i];
			i = i + 1;
		}
		return result;
	}
	public static double sum (double[] a) {
		return sumHelper(a, 0, 0.0); // Done
	}
	
	public static double sumHelper(double[] a, int i, double total){
		if (i < a.length){
			total = total + sumHelper(a, i+1, a[i]);
		}
		return total;
	}

	/**
	 * PROBLEM 2: Do the same translation for this in-place reverse function
	 *
	 * You should write a helper method. You may not use any "fields" to solve
	 * this problem (a field is a variable that is declared "outside" of the
	 * function declaration --- either before or after).
	 */
	public static void reverseI (double[] a) {
		int hi = a.length - 1;
		int lo = 0;
		while (lo < hi) {
			double loVal = a[lo];
			double hiVal = a[hi];
			a[hi] = loVal;
			a[lo] = hiVal;
			lo = lo + 1;
			hi = hi - 1;
		}
	}
	public static void reverse (double[] a) {
		if (a.length <= 1)reverseHelper(a,1,0);
		else reverseHelper(a, 0, a.length-1); //Done
	}
	public static double[] reverseHelper (double[] a, int lo, int hi){
		if (lo < hi){
			double loVal = a[lo];
			double hiVal = a[hi];
			a[hi] = loVal;
			a[lo] = hiVal;
			reverseHelper(a,lo+1,hi-1);
		}
		return a;
	}

	/**
	 * PROBLEM 3: The following function draws mickey mouse, if you call it like
	 * this from main:
	 *
	 * <pre>
	 * draw (.5, .5, .25);
	 * </pre>
	 *
	 * Change the code to draw mickey moose instead. Your solution should be
	 * recursive.
	 * 
	 * Before picture: http://fpl.cs.depaul.edu/jriely/ds1/images/MickeyMouse.png
	 * After picture: http://fpl.cs.depaul.edu/jriely/ds1/images/MickeyMoose.png 
	 *
	 * You may not use any "fields" to solve this problem (a field is a variable
	 * that is declared "outside" of the function declaration --- either before
	 * or after).
	 */
	public static void draw (double centerX, double centerY, double radius) {
		if (radius < .0005) return; //Done
		
		StdDraw.setPenColor (StdDraw.LIGHT_GRAY);
		StdDraw.filledCircle (centerX, centerY, radius);
		StdDraw.setPenColor (StdDraw.BLACK);
		StdDraw.circle (centerX, centerY, radius);

double change = radius * 0.90;
		
		draw (centerX+change, centerY+change, radius/2);
		
		draw (centerX-change, centerY+change, radius/2);
		
//		StdDraw.setPenColor (StdDraw.LIGHT_GRAY);
//		StdDraw.filledCircle (centerX+change, centerY+change, radius/2);
//		StdDraw.setPenColor (StdDraw.BLACK);
//		StdDraw.circle (centerX+change, centerY+change, radius/2);
//		
//		StdDraw.setPenColor (StdDraw.LIGHT_GRAY);
//		StdDraw.filledCircle (centerX-change, centerY+change, radius/2);
//		StdDraw.setPenColor (StdDraw.BLACK);
//		StdDraw.circle (centerX-change, centerY+change, radius/2);
	}

	/**
	 * PROBLEM 4: Run runTerribleLoop for one hour. You can stop the program using
	 * the red "stop" square in eclipse. Fill in the OUTPUT line below with the
	 * numbers you saw LAST --- edit the line, replacing the two ... with what
	 * you saw:
	 *
	 * OUTPUT: terribleFibonacci(56)=225851433717 // Done
	 *
	 * Comment: the code uses "long" variables, which are like "int", but
	 * bigger. It's because fibonacci numbers get really big really fast.
	 */
	public static void runTerribleLoop () {
		for (int N = 0; N < 100; N++)
			StdOut.format ("terribleFibonacci(%2d)=%d\n", N, terribleFibonacci (N));
	}
	public static void runTerribleSomeValues () {
		StdOut.format ("terribleFibonacci(%2d)=%d\n", 13, terribleFibonacci (13));
		StdOut.format ("terribleFibonacci(%2d)=%d\n", 7,  terribleFibonacci (7));
		StdOut.format ("terribleFibonacci(%2d)=%d\n", 21, terribleFibonacci (21));
	}
	public static long terribleFibonacci (int n) {
		if (n <= 1) return n;
		return terribleFibonacci (n - 1) + terribleFibonacci (n - 2);
	}

	/**
	 * PROBLEM 5: The implementation of terribleFibonacci is TERRIBLE! Write a
	 * more efficient version of fibonacci. Do not change runFibonacciLoop or
	 * runFibonacciSomeValues.
	 *
	 * To make fibonacci run faster, you want it so that each call to
	 * fibonacci(n) computes the fibonacci numbers between 0 and n once, not
	 * over and over again.
	 *
	 * Comment: You will want to use a local variable of type "long" rather than
	 * type "int", for the reasons discussed above.
	 *
	 * Comment: At some point, your fibonacci numbers might become negative.
	 * This is normal and expected.
	 * http://en.wikipedia.org/wiki/Integer_overflow We discuss this at length
	 * in our systems classes.
	 *
	 * You may not use any "fields" to solve this problem (a field is a variable
	 * that is declared "outside" of the function declaration --- either before
	 * or after).
	 */
	public static void runFibonacciLoop () {
		for (int N = 0; N < 100; N++)
			StdOut.format ("fibonacci(%2d)=%d\n", N, fibonacci (N));
	}
	public static void runFibonacciSomeValues () {
		StdOut.format ("fibonacci(%2d)=%d\n", 13, fibonacci (13));
		StdOut.format ("fibonacci(%2d)=%d\n", 7,  fibonacci (7));
		StdOut.format ("fibonacci(%2d)=%d\n", 21, fibonacci (21));
	}
	public static long fibonacci (int n) {
		if (n<1) return 0;
		long fib1 = 1;
		long fib2 = 1;
		long total = 1;
		int i = 2;
		while (i < n){
			total = fib1 + fib2;
			fib1 = fib2;
			fib2 = total;
			i++;
		}
		return total; // fixed
	}

	public static void main (String[] args) {
		double[] list0 = new double[] {};
		double[] list1 = new double[] { 5 };
		double[] list2 = new double[] { -3, 5 };
		double[] list3 = new double[] { 2, -3, 5 };
		double[] list4 = new double[] { -1, 2, -3, 5 };
		double[] list5 = new double[] { 0, -1, 2, -3, 5 };

		StdOut.println (sum (list0));
		StdOut.println (sum (list1));
		StdOut.println (sum (list2));
		StdOut.println (sum (list3));
		StdOut.println (sum (list4));
		StdOut.println (sum (list5));

		reverse (list0);
		StdOut.println (Arrays.toString (list0));
		reverse (list1);
		StdOut.println (Arrays.toString (list1));
		reverse (list2);
		StdOut.println (Arrays.toString (list2));
		reverse (list3);
		StdOut.println (Arrays.toString (list3));
		reverse (list4);
		StdOut.println (Arrays.toString (list4));
		reverse (list5);
		StdOut.println (Arrays.toString (list5));

		draw (.5, .5, .25);

		//runTerribleSomeValues ();
		//runTerribleLoop ();
		runFibonacciSomeValues ();
		runFibonacciLoop();
	}

}
