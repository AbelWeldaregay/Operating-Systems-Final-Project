package com.cs471.prodcons;

import java.util.Random;

/**
 * @description Utilities for causing a thread
 * 				to sleep
 * @author AbelWeldaregay
 *
 */
public class Sleep {
    /**
     * @description represents the minimum (mm) sleep time
     */
    public static final int SLEEP_MIN = 5;
    /**
     * @description represents the maximum (mm) sleep time
     */
    public static final int SLEEP_MAX = 40;
    
    /**
     * invoke sleep
     */
    public static void nap() {
    	sleep();
    }
    /**
     * @description sleeps for a random length of time
     *              between SLEEP_MIN and SLEEP_MAX
     */
    public static void sleep() {
        Random random = new Random();
        int randomNumber = random.nextInt(SLEEP_MAX + 1 - SLEEP_MIN) + SLEEP_MIN;
        System.out.println("SLEEP LENGTH (between 5mm and 40mm): " + randomNumber);

        try {
            Thread.sleep(randomNumber * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
