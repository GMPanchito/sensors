package org.lanseg.thatpage;

import java.util.concurrent.Executors;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/main")
public class Main {

    private static final int TASKS_AMOUNT = 3;
    private static final Logger LOG = Logger.getLogger(Main.class.getName());


    public Main() {
    }

    @GET
    @Path("/fortune")
    public String getFortune() {
        return "whatever";
    }
}
