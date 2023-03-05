package com.bears.util;

/**
 * Basic class for timing stuff. Uses two Java Instance objects and checks the duration between them
 */
public class StopWatch {

    private long start;
    private long end;
    private static final int NANO_TO_MICRO = 1000;

    public StopWatch() {
        start = 0;
        end = 0;
    }

    public void start() {
        start = System.nanoTime();
    }

    public void end() {
        end = System.nanoTime();
    }

    public void reset() {
        start = 0;
        end = 0;
    }

    /**
     * Function for getting the time between the start and end instances and returning it as a string
     *
     * @return a string with the timed duration in microseconds
     */
    public String getDuration() {
        if (start == 0 || end == 0) {
            throw new IllegalStateException("start or end cannot be 0");
        }
        long duration = (end - start) / NANO_TO_MICRO;

        return String.valueOf(duration);
    }
}
