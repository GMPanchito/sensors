package org.lanseg.sensors.geo;

/**
 *
 * @author lans
 */
public class GeoPoint {

    private double lon;
    private double lat;

    public GeoPoint() {
    }

    public GeoPoint(double lon, double lat) {
        this.lat = lat;
        this.lon = lon;

    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return String.format("GeoPoint {lon: %f, lat: %f}", lon, lat);
    }
}
