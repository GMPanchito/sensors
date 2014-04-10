package org.lanseg.sensors.data.impl;

import java.util.ArrayList;
import java.util.List;
import org.lanseg.sensors.data.Observation;
import org.lanseg.sensors.data.api.ObservationSource;

/**
 *
 * @author lans
 */
public class DemoObservationSource implements ObservationSource {

    private final long minTime;
    private final long maxTime;
    private final List<Observation> observations;
    
    public DemoObservationSource(int recordCount, long minTime, long maxTime, double min, double max) {
        this.minTime = minTime;
        this.maxTime = maxTime;
        observations = new ArrayList<>(recordCount);
        long timeDelta = (maxTime - minTime) / recordCount;
        for (int i = 0; i < recordCount; i++) {
            observations.add(new Observation(minTime + i * timeDelta, Math.random() * max + min));
        }
    }
    
    @Override
    public long getMinTime() {
        return minTime;
    }

    @Override
    public long getMaxTime() {
        return maxTime;
    }

    @Override
    public List<Observation> getObservations(long fromTime, long toTime) {
        return observations;
    }

    @Override
    public void putObservations(List<Observation> data) {}

    @Override
    public void putObservation(Observation data) {}
    
}
