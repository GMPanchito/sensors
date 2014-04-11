package org.lanseg.thatpage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.lanseg.sensors.data.Feature;
import org.lanseg.sensors.data.Observation;
import org.lanseg.sensors.data.Sensor;
import org.lanseg.sensors.data.api.SensorDataStorage;
import org.lanseg.sensors.data.impl.DemoSensorStorage;
import org.lanseg.sensors.geo.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lans
 */
@Path("/sensors")
public class SensorApi {

    private static final String JSON = "application/json";
    
    @Autowired
    private final SensorDataStorage sensorStorage = new DemoSensorStorage(12, 3, GeoUtils.WORLD);

    public SensorApi() {
    }

    @GET
    @Path("/api")
    @Produces("text/html")
    public String getApi() {
        StringBuilder result = new StringBuilder();
        result.append("<table border=1>");
        for (Method method : this.getClass().getMethods()) {
            if (method.getAnnotation(Path.class) != null) {
                result.append("<tr>")
                        .append("<td>")
                        .append(method.getAnnotation(Path.class).value())
                        .append("</td>")
                        .append("<td>");
                for (int i = 0; i < method.getParameterCount(); i++) {
                    for (Annotation a: method.getParameterAnnotations()[i]) {
                        result.append(a).append("<br/>");
                    }
                }
                result.append("</td></tr>");
            }
        }
        return result.append("</table>").toString();
    }

    @GET
    @Path("/data")
    @Produces(JSON)
    public List<String> getTweets() {
        return Collections.EMPTY_LIST;
    }

    @GET
    @Path("/list")
    @Produces(JSON)
    public List<Sensor> getAllSensors() {
        return sensorStorage.getAllSensors();
    }

    @GET
    @Path("/sensors/{sensorId}/features")
    @Produces(JSON)
    public Collection<Feature> getFeatures(@PathParam("sensorId") String sensorId) {
        Sensor sensor = sensorStorage.getSensor(sensorId);
        if (sensor == null) {
            return Collections.emptyList();
        }
        return sensor.getFeatures().values();
    }

    @GET
    @Path("/sensors/{sensorId}/{featureId}")
    @Produces(JSON)
    public List<Observation> getObservations(@PathParam("sensorId") String sensorId,
            @PathParam("featureId") String featureId,
            @RequestParam("since") long since,
            @RequestParam("until") long until) {
        Sensor sensor = sensorStorage.getSensor(sensorId);
        if (sensor == null) {
            return Collections.emptyList();
        }
        Feature feature = sensor.getFeatures().get(featureId);
        if (feature == null) {
            return Collections.emptyList();
        }
        return feature.getSource().getObservations(since, until);
    }

    @GET
    @Path("/sensors/{sensorId}/{featureId}/add_observation")
    @Produces(JSON)
    public void addData(@PathParam("sensorId") String sensorId,
            @PathParam("featureId") String featureId,
            @RequestParam("time") long time,
            @RequestParam("value") double value) {
        sensorStorage.getSensor(sensorId)
                .getFeatures().get(featureId).getSource()
                .putObservation(new Observation(time, value));
    }
}
