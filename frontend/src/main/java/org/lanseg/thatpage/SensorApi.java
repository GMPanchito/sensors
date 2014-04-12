package org.lanseg.thatpage;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.lanseg.sensors.data.Feature;
import org.lanseg.sensors.data.Observation;
import org.lanseg.sensors.data.ObservationType;
import org.lanseg.sensors.data.Sensor;
import org.lanseg.sensors.data.api.SensorDataStorage;
import org.lanseg.sensors.data.impl.DemoSensorStorage;
import org.lanseg.sensors.data.impl.FileObservationSource;
import org.lanseg.sensors.geo.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lans
 */
@Path("/sensors")
public class SensorApi {

    private static final Logger LOG = Logger.getLogger("SensorApi");
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
                    for (Annotation a : method.getParameterAnnotations()[i]) {
                        result.append(a).append("<br/>");
                    }
                }
                result.append("</td></tr>");
            }
        }
        return result.append("</table>").toString();
    }

    @GET
    @Path("/list")
    @Produces(JSON)
    public List<Sensor> getDetails() {
        return sensorStorage.getAllSensors();
    }

    @GET
    @Produces(JSON)
    @Path("/add_sensor/{sensorId}")
    public void addSensor(@NotNull @PathParam("sensorId") String sensorId) {
        LOG.log(Level.INFO, "Adding new sensor: {1}", sensorId);
        if (sensorStorage.getSensor(sensorId) != null) {
            LOG.log(Level.WARNING, "Sensor {1} already exists!", sensorId);
            return;
        }
        Sensor s = new Sensor(sensorId);
        sensorStorage.addSensor(s);
    }

    @GET
    @Produces(JSON)
    @Path("/features/{sensorId}/add/{featureId}")
    public void addFeature(@NotNull @PathParam("sensorId") String sensorId,
            @NotNull @PathParam("featureId") String featureId,
            @NotNull @QueryParam("type") ObservationType type) throws IOException {
        LOG.log(Level.INFO, "Adding new feature {2} ({3}) for sensor {1}",
                new Object[]{sensorId, featureId, type});
        Sensor s = sensorStorage.getSensor(sensorId);
        if (s == null) {
            LOG.log(Level.WARNING, "Sensor {1} not found!", sensorId);
            return;
        }
        if (s.getFeatures().containsKey(featureId)) {
            LOG.log(Level.WARNING, "Sensor {1} already has feature {2}!",
                    new Object[]{sensorId, featureId});
            return;
        }
        Feature f = new Feature(featureId, type,
                new FileObservationSource("/tmp/sensors/" + featureId));
        s.addFeature(f);
    }

    @GET
    @Produces(JSON)
    @Path("/features/{sensorId}")
    public Collection<Feature> getFeatures(@PathParam("sensorId") String sensorId) {
        Sensor sensor = sensorStorage.getSensor(sensorId);
        if (sensor == null) {
            return Collections.emptyList();
        }
        return sensor.getFeatures().values();
    }

    @GET
    @Path("/data/{sensorId}/{featureId}")
    @Produces(JSON)
    public List<Observation> getObservations(@PathParam("sensorId") String sensorId,
            @PathParam("featureId") String featureId,
            @QueryParam("since") long since,
            @QueryParam("until") long until) {
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
    @Path("/add_observation/{sensorId}/{featureId}")
    @Produces(JSON)
    public void addData(@PathParam("sensorId") String sensorId,
            @PathParam("featureId") String featureId,
            @QueryParam("time") long time,
            @QueryParam("value") double value) {
        Observation toAdd = new Observation(time, value);
        LOG.log(Level.INFO, "Adding observation: {1}", toAdd);
        sensorStorage.getSensor(sensorId)
                .getFeatures().get(featureId).getSource()
                .addObservation(new Observation(time, value));
    }
}
