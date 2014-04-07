package org.lanseg.sensors.data.api;

import java.util.List;
import org.lanseg.sensors.data.Observation;

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
