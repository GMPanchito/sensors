package org.lanseg.sensors.data;

import java.util.List;

/**
 *
 * @author lans
 */
public class Sensor {
    
    private String name;
    private List<Feature> features;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
    
    
}
