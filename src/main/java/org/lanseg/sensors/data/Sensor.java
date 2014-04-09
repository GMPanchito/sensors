package org.lanseg.sensors.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author lans
 */
public class Sensor {

    private String id;

    private final Map<String, Feature> features = new HashMap<>();

    public Sensor() {
    }

    public Sensor(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Feature> getFeatures() {
        return Collections.unmodifiableMap(features);
    }

    public void setFeatures(List<Feature> features) {
        this.features.clear();
        features.stream().forEach((f) -> {
            this.features.put(f.getId(), f);
        });
    }

    public void addData(String featureId, long time, double value) {
        features.get(featureId).getSource().putObservation(new Observation(time, value));
    }

    @Override
    public String toString() {
        return String.format("Sensor {id: %s, features: %d} ", id, features.size());
    }
}
