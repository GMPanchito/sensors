package org.lanseg.sensors.data;

import java.util.List;

/**
 *
 * @author lans
 */
public class Sensor {
    
    private String id;
    private List<Feature> features;

    public Sensor(){}
    
    public Sensor(String id, List<Feature> features) {
        this.id = id;
        this.features = features;
    }
            
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
