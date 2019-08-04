package com.cs471.prodcons;

import java.util.Random;

/**
 * Represents a utility class
 * @author Abel Weldaregay
 *
 */
public class UtilityClass {
	/**
	 * The minimum sleep length in ms
	 */
	static int SLEEP_LENGTH_MIN_MS = 5;
	/**
	 * The maximum sleep length in ms
	 */
	static int SLEEP_LENGTH_MAX_MS = 40;
	/**
	 * Randomly sleeps between
	 * 5 and 40 milliseconds
	 */
	public static void nap() {
		int sleep_ms = (int) (SLEEP_LENGTH_MIN_MS + (Math.random() * (double) (SLEEP_LENGTH_MAX_MS-SLEEP_LENGTH_MIN_MS)));
		try {
			Thread.sleep(sleep_ms);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
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
