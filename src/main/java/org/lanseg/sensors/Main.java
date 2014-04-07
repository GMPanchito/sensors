package org.lanseg.sensors;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.lanseg.sensors.data.Feature;
import org.lanseg.sensors.data.ObservationType;
import org.lanseg.sensors.data.Sensor;
import org.lanseg.sensors.data.impl.FileObservationSource;

/**
 *
 * @author lans
 */
public class Main {

    public static void main(String... args) throws IOException {
        FileObservationSource source = new FileObservationSource("/home/lans/whatever");
        long mintime = LocalDateTime.of(2014, 07, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
        long maxtime = LocalDateTime.of(2014, 07, 1, 23, 59).toEpochSecond(ZoneOffset.UTC);

        source.getObservationStream().filter((o) -> o.getTime() > mintime && o.getTime() < maxtime).forEach(System.out::println);
        source.close();

        List<Feature> features = new ArrayList<>();
        features.add(new Feature("Temperature - 1",
                ObservationType.TEMPERATURE,
                new FileObservationSource("s1f1")));
        features.add(new Feature("Humidity - 1", ObservationType.HUMIDITY, new FileObservationSource("s1f2")));
        
        Sensor s = new Sensor("sensor - 1", features);
        (new ObjectMapper()).writeValue(new File("data"), s);

    }
}
