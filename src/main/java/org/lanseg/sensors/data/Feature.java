package org.lanseg.sensors.data;

/**
 *
 * @author lans
 */
public class Feature {
    
    private ObservationType type;
    private ObservationSource source;

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
    
}
