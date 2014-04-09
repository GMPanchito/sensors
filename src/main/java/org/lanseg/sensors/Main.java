package org.lanseg.sensors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.lanseg.sensors.data.Feature;
import org.lanseg.sensors.data.ObservationType;
import org.lanseg.sensors.data.Sensor;
import org.lanseg.sensors.data.api.SensorDataStorage;
import org.lanseg.sensors.data.impl.FileObservationSource;
import org.lanseg.sensors.data.impl.FileSensorDataStorage;

/**
 *
 * @author lans
 */
public class Main {

    private static final String ROOT = "root/";

    public static void main(String... args) throws IOException {
        /* FileObservationSource source = new FileObservationSource("/home/lans/whatever");
         long mintime = LocalDateTime.of(2014, 07, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
         long maxtime = LocalDateTime.of(2014, 07, 1, 23, 59).toEpochSecond(ZoneOffset.UTC);

         source.close(); */

        SensorDataStorage sensorSource = new FileSensorDataStorage(ROOT);

        List<Feature> features = new ArrayList<>();
        features.add(new Feature("Temp - 1",
                ObservationType.TEMPERATURE,
                new FileObservationSource(ROOT + "/s1f1")));

        features.add(new Feature("Humd - 1",
                ObservationType.HUMIDITY,
                new FileObservationSource(ROOT + "/s1f2")));
        
        Sensor s = new Sensor("sensor - 1");
        s.setFeatures(features);
        s.addData("Temp - 1", System.currentTimeMillis(), Math.random());
        s.addData("Humd - 1", System.currentTimeMillis(), Math.random());

        s.getFeatures().get("Temp - 1").getSource().getObservations(0, System.currentTimeMillis() * 10).forEach(
                (o) -> System.out.println("Temperature: " + o));
        s.getFeatures().get("Humd - 1").getSource().getObservations(0, System.currentTimeMillis() * 10).forEach(
                (o) -> System.out.println("Humidity: " + o)
        );  
        
        sensorSource.addSensor(s);
        
        (new ObjectMapper()).writeValue(new File("data"), sensorSource);
        System.out.println(s);

    }
}
