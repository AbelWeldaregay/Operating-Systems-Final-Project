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
		int min_ms = 5;
		int max_ms = 40;
		int sleep_ms = (int) (min_ms + (Math.random() * (double) (max_ms-min_ms)));
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
