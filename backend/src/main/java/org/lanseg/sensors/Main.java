package org.lanseg.sensors;

import java.awt.geom.Point2D;
import java.io.IOException;
import org.lanseg.sensors.data.api.ObservationSource;
import org.lanseg.sensors.data.api.SensorDataStorage;
import org.lanseg.sensors.data.impl.DemoSensorStorage;
import org.lanseg.sensors.geo.GeoUtils;
import org.lanseg.sensors.utils.Utils;

/**
 *
 * @author lans
 */
public class Main {

    private static final String ROOT = "root/";

    public static void main(String... args) throws IOException {
        /*   SensorDataStorage sensorSource = new FileSensorDataStorage(ROOT);

         List<Feature> features = new ArrayList<>();
         features.add(new Feature("Temp - 1",
         ObservationType.TEMPERATURE,
         new FileObservationSource(ROOT + "/s1f1")));

         features.add(new Feature("Humd - 1",
         ObservationType.HUMIDITY,
         new FileObservationSource(ROOT + "/s1f2")));
        
         Sensor s = new Sensor("sensor - 1");
         s.setFeatures(features);
         s.getFeatures().get("Temp - 1").getSource().getObservations(0, System.currentTimeMillis() * 10).forEach(
         (o) -> System.out.println("Temperature: " + o));
         s.getFeatures().get("Humd - 1").getSource().getObservations(0, System.currentTimeMillis() * 10).forEach(
         (o) -> System.out.println("Humidity: " + o)
         );  
        
         sensorSource.addSensor(s);
        
         (new ObjectMapper()).writeValue(new File("data"), sensorSource);*/

        SensorDataStorage sensors = new DemoSensorStorage(12, 3, GeoUtils.WORLD);
        sensors.getSensors().forEach((s) -> {
            System.out.println("Sensor " + s);
            s.getFeatures().entrySet().forEach((e) -> {
                System.out.println("\tFeature " + e.getValue().getType());
                ObservationSource src = e.getValue().getSource();
                src.getObservations(0, src.getMaxTime()).forEach((o) -> {
                    System.out.println("\t\t" + o.getValue());
                });
            });
        });     
         System.out.println(Utils.MAPPER.writeValueAsString(new Point2D.Double(100, 100)));

    }
}
