package com.cs471.prodcons;

public class SleepUtil {

	private static final int SLEEP_MIN = 5;
	private static final int SLEEP_MAX = 25;

	public static void sleep () {
		sleep(SLEEP_MAX - SLEEP_MIN);
	}
	
	public static void sleep (int howLong) {
		int amountofsleep = SLEEP_MIN + (int) (Math.random() * ((SLEEP_MAX - SLEEP_MIN) +1));
		System.out.println("Sleep for " + amountofsleep+ " seconds ");
		
	try { Thread.sleep(amountofsleep*1000); }
		catch (InterruptedException e) {
		System.out.println("ERROR in sleep() : " + e);
		}
	}
}
