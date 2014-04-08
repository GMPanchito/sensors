package org.lanseg.sensors;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.lanseg.sensors.data.Feature;
import org.lanseg.sensors.data.ObservationType;
import org.lanseg.sensors.data.Sensor;
import org.lanseg.sensors.data.impl.FileObservationSource;

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

         source.close();

         List<Feature> features = new ArrayList<>();
         features.add(new Feature("Temperature - 1",
         ObservationType.TEMPERATURE,
         new FileObservationSource(ROOT + "/s1f1")));
        
         features.add(new Feature("Humidity - 1", 
         ObservationType.HUMIDITY, 
         new FileObservationSource(ROOT + "/s1f2")));
         Sensor s = new Sensor("sensor - 1");
         s.setFeatures(features);
         s.addData("Temperature - 1", System.currentTimeMillis(), Math.random());
         s.addData("Humidity - 1", System.currentTimeMillis(), Math.random());
        
         s.getFeatures().get("Temperature - 1").getSource().getObservations(0, System.currentTimeMillis() * 10).forEach(System.out::println);
         s.getFeatures().get("Humidity - 1").getSource().getObservations(0, System.currentTimeMillis() * 10).forEach(System.out::println);*/
//        (new ObjectMapper()).writeValue(new File("data"), s);
        ObjectMapper m = new ObjectMapper();
        m.enableDefaultTyping();
        Sensor s = m.readValue(new File("data"), new TypeReference<Sensor>(){});
        System.out.println(s);
        
    }
}
