package org.lanseg.sensors.data.api;

import java.util.List;
import org.lanseg.sensors.data.Sensor;

/**
 *
 * @author lans
 */
public interface SensorDataStorage {

    public void addSensor(Sensor s);
    public List<Sensor> getSensors();
    public void removeSensor(String id);
}
