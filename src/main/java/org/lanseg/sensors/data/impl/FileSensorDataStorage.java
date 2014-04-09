package org.lanseg.sensors.data.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lanseg.sensors.data.Feature;
import org.lanseg.sensors.data.Sensor;
import org.lanseg.sensors.data.api.SensorDataStorage;

/**
 *
 * @author lans
 */
public class FileSensorDataStorage implements SensorDataStorage {

    private final Map<String, Sensor> sensors;
    private final File root;

    public FileSensorDataStorage(String rootFolder) {
        root = new File(rootFolder);
        if (root.exists() && !root.isDirectory()) {
            throw new RuntimeException(rootFolder + " already exists and not a folder!");
        }
        if (!root.exists()) {
            root.mkdir();
        }
        sensors = new HashMap<>();
    }

    @Override
    public void addSensor(Sensor s) {
        sensors.put(s.getId(), s);
    }

    @Override
    public List<Sensor> getSensors() {
        List<Sensor> result = new ArrayList<>();
        result.addAll(sensors.values());
        return result;
    }

    @Override
    public void removeSensor(String id) {
        sensors.remove(id);
    }

    @Override
    public Sensor createSensor(String id, List<Feature> features) {
        Sensor s = new Sensor(id);
        s.setFeatures(features);
        sensors.put(id, s);
        return s;
    }

}
