package org.lanseg.sensors.data;

import org.lanseg.sensors.data.api.ObservationSource;

/**
 *
 * @author lans
 */
public class Feature {

    private String id;
    private ObservationType type;
    private ObservationSource source;

    public Feature() {
    };
    
    public Feature(String id, ObservationType type, ObservationSource source) {
        this.id = id;
        this.type = type;
        this.source = source;
    }

    public ObservationType getType() {
        return type;
    }

    public void setType(ObservationType type) {
        this.type = type;
    }

    public ObservationSource getSource() {
        return source;
    }

    public void setSource(ObservationSource source) {
        this.source = source;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }

}
