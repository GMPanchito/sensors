package org.lanseg.thatpage;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author lans
 */
@Path("/sensors")
public class SensorApi {

    private static final String JSON = "application/json";

    @GET
    @Path("/data")
    @Produces(JSON)
    public List<String> getTweets() {
        return Collections.EMPTY_LIST;
    }

}
