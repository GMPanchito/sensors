package org.lanseg.sensors.data.impl;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.lanseg.sensors.data.Feature;
import org.lanseg.sensors.data.ObservationType;
import org.lanseg.sensors.data.Sensor;
import org.lanseg.sensors.data.SensorDetails;
import org.lanseg.sensors.geo.GeoPoint;
import org.lanseg.sensors.data.api.ObservationSource;
import org.lanseg.sensors.data.api.SensorDataStorage;
import org.lanseg.sensors.geo.GeoUtils;

/**
 *
 * @author lans
 */
public class DemoSensorStorage implements SensorDataStorage {

    private final int sensorsCount;
    private final List<Sensor> sensors;

    public DemoSensorStorage(int count, int maxFeatures) {
        sensorsCount = count;
        sensors = new ArrayList<>();
        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long end = LocalDateTime.now().plusMonths(4).toEpochSecond(ZoneOffset.UTC);
        for (int i = 0; i < sensorsCount; i++) {
            Sensor s = new Sensor("Demo sensor " + i);
            Rectangle2D bounds = GeoUtils.SAINT_PETERSBURG;
            SensorDetails details = new SensorDetails(new GeoPoint(
                    bounds.getMinX() + Math.random() * bounds.getWidth(),
                    bounds.getMinY() + Math.random() * bounds.getHeight()
            ) , "Whatever", "Lorem Ipsum");
            s.setDetails(details);
            
            List<Feature> features = new ArrayList<>();
            for (int j = 0; j <= (int) (Math.random() * maxFeatures) + 1; j++) {
                Feature f = new Feature("Sensor " + i + ", Feature " + j,
                        ObservationType.values()[(int) (Math.random() * ObservationType.values().length)],
                        new DemoObservationSource((int) (Math.random() * 100) + 1,
                                start, end, 0, 100));
                features.add(f);
            }
            s.setFeatures(features);
            sensors.add(s);
        }
    }

    @Override
    public void addSensor(Sensor s) {
    }

    @Override
    public List<Sensor> getSensors() {
        return sensors;
    }

    @Override
    public void removeSensor(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
