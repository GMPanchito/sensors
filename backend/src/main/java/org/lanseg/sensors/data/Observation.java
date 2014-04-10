package org.lanseg.sensors.data;

import java.util.Date;

/**
 *
 * @author lans
 */
public class Observation {
    
    private long time;
    private double value;

    public Observation(long time, double value) {
        this.time = time;
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return new Date(time) + ": " + value;
    }
    
}
