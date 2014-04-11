package org.lanseg.sensors.data;

import java.awt.geom.Point2D;
import org.lanseg.sensors.geo.GeoPoint;

/**
 *
 * @author lans
 */
public class SensorDetails {

    private GeoPoint location;
    private String title;
    private String description;

    public SensorDetails() {
    }

    public SensorDetails(GeoPoint location, String title, String description) {
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Details {pos: %s, title: %s, description:%s}",
                location, title, description);
    }

}
