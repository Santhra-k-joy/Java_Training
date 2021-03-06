package com.epsilon.training.entity;
import java.util.Scanner;

public class KeyboardUtil {

	// when ever you have a class (utility) with out any member variables
		// make all functions static, class as final and
		// no-arg constructor as private

		private KeyboardUtil() {
		}

		// mark the function as static, if the function does not use
		// the members of the object
		public static int getInt(String msg) {
			System.out.print(msg);
			Scanner sc = new Scanner(System.in);
			return sc.nextInt();
		}

		public static String getString(String msg) {
			System.out.print(msg);
			Scanner sc = new Scanner(System.in);
			return sc.nextLine();
		}
		public static String getStringPass(String msg,String val) {
			System.out.print(msg + "{"+ val+"}");
			Scanner sc = new Scanner(System.in);
			return sc.nextLine();
		}
		public static double getDouble(String string) {
			System.out.print(string);
			Scanner sc = new Scanner(System.in);
			return sc.nextDouble();
		}
		public static String  getDoublePass(String string,Double val) {
			System.out.print(string + "{"+ val+"}");
			Scanner sc = new Scanner(System.in);
			return sc.nextLine();
		}
}
