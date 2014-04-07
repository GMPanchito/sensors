package org.lanseg.sensors.data;

import java.util.List;

/**
 *
 * @author lans
 */
public interface ObservationSource {
    
    public long getMinTime();
    public long getMaxTime();
    
    public List<Observation> getObservations(long fromTime, long toTime);
    public void putObservations(List<Observation> data);
    
}
