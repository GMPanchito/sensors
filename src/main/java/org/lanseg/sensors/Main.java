package org.lanseg.sensors;

import java.io.IOException;
import org.lanseg.sensors.data.api.SensorDataStorage;
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

        SensorDataStorage sensors = Utils.getStorage("data");
        System.out.println(sensors);
        sensors.getSensors().forEach((s) -> {
            s.getFeatures().get("Temp - 1").getSource().getObservations(0, System.currentTimeMillis() * 10).forEach(
                    (o) -> System.out.println("Temperature: " + o));
            s.getFeatures().get("Humd - 1").getSource().getObservations(0, System.currentTimeMillis() * 10).forEach(
                    (o) -> System.out.println("Humidity: " + o)
            );
        });
        /*
         ObjectMapper m = new ObjectMapper();
         SensorDataStorage sensorSource = m.readValue(new File("data"), FileSensorDataStorage.class);
         System.out.println(sensorSource);*/

    }
}
