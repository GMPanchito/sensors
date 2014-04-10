package org.lanseg.sensors.data.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lanseg.sensors.data.Sensor;
import org.lanseg.sensors.data.api.SensorDataStorage;

/**
 *
 * @author lans
 */
public class FileSensorDataStorage implements SensorDataStorage {

    private final Map<String, Sensor> sensors = new HashMap<>();;
    private File root;

    public FileSensorDataStorage() {

    }

    public FileSensorDataStorage(String rootFolder) {
        root = new File(rootFolder);
        initStorage();
    }

    private void initStorage() {
        if (root.exists() && !root.isDirectory()) {
            throw new RuntimeException(root + " already exists and not a folder!");
        }
        if (!root.exists()) {
            root.mkdir();
        }
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

    public void setSensors(List<Sensor> sensors) {
        this.sensors.clear();
        sensors.stream().forEach((s) -> {
            this.sensors.put(s.getId(), s);
        });
    }

    public File getRoot() {
        return root;
    }

    public void setRoot(File root) {
        this.root = root;
        initStorage();
    }
    
    @Override
    public String toString(){
        return String.format("Sensors {%s}", sensors);
    }
}
