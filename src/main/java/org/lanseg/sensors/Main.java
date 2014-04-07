package org.lanseg.sensors;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import org.lanseg.sensors.data.FileObservationSource;
import org.lanseg.sensors.data.Observation;

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
        /* long minTime = source.getMinTime();
         System.out.println(new Date(minTime));
         for (long i = 0; i < 360 * 24 * 60; i++) {
         source.putObservations(Arrays.asList(new Observation(
         minTime + i * 60000,
         Math.sin(2 * Math.PI * i / (360*24*60)))));
         }*/
        source.close();
    }
}
