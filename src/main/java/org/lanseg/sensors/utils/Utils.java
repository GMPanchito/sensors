package org.lanseg.sensors.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonNode;
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
public class Utils {

    public static final ObjectMapper MAPPER = new ObjectMapper();
    
    public static SensorDataStorage getStorage(String settingsFile) throws IOException {
        JsonNode node = MAPPER.readTree(new File(settingsFile));
        FileSensorDataStorage result = new FileSensorDataStorage();
        result.setRoot(new File(node.get("root").asText()));
        for (JsonNode sensor: node.get("sensors")) {
            Sensor s = new Sensor();
            System.out.println(sensor.get("id").asText());
            s.setId(sensor.get("id").asText());
            List<Feature> features = new ArrayList<>();
            for (JsonNode feature: sensor.get("features")) {
                Feature f = new Feature();
                f.setId(feature.get("id").asText());
                f.setType(ObservationType.valueOf(feature.get("type").asText()));
                JsonNode source = feature.get("source");
                f.setSource(new FileObservationSource(source.get("filename").asText()));
                features.add(f);
            }
            s.setFeatures(features);
            result.addSensor(s);
        }
        return result;
    }
}
