package org.lanseg.thatpage;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.lanseg.sensors.data.Sensor;
import org.lanseg.sensors.data.api.SensorDataStorage;

/**
 *
 * @author lans
 */
@Path("/sensors")
public class SensorApi {

    private static final String JSON = "application/json";
    private SensorDataStorage sensorStorage;
    
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
        return Collections.EMPTY_LIST;
    }
}
