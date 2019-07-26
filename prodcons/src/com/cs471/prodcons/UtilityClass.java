package com.cs471.prodcons;

import java.util.Random;

/**
 * Represents a utility class
 * @author Abel Weldaregay
 *
 */
public class UtilityClass {

	private static final int SLEEP_LENGTH_MIN = 5;
	private static final int SLEEP_LENGTH_MAX = 25;

	public static void nap() {
		int amountofsleep = SLEEP_LENGTH_MIN + (int) (Math.random() * ((SLEEP_LENGTH_MAX - SLEEP_LENGTH_MIN) +1));
		System.out.println("Sleep for " + amountofsleep+ " seconds ");
		
	try { Thread.sleep(amountofsleep*1000); }
		catch (InterruptedException e) {
		System.out.println("ERROR in sleep() : " + e);
		}
	}
	/**
	 * generates a integer in given range
	 * @param min
	 * @param max
	 * @return int between min and max
	 */
	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	/**
	 * Creates a float in given range
	 * @param d
	 * @param e
	 * @return
	 */
	public static float getRandomFloatInRange(double d, double e) {

		if (d >= e) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		
		return (float) (r.nextFloat() * (e - d) + d);
	}
}
