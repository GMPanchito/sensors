package org.lanseg.sensors.geo;

import java.awt.geom.Rectangle2D;

/**
 *
 * @author lans
 */
public final class GeoUtils {
    
    private GeoUtils(){
        
    }
    
    public static final Rectangle2D SAINT_PETERSBURG
            = new Rectangle2D.Double(29.808, 59.472, 30.960 - 29.808, 60.336 - 59.472);
    public static final Rectangle2D WORLD
            = new Rectangle2D.Double(-180, -90, 360, 180);
    public static final Rectangle2D SOCHI
            = new Rectangle2D.Double(39, 43, 3, 2);
    public static final Rectangle2D UKRAINE
            = new Rectangle2D.Double(20.960497, 44.676894, 44.676894 - 20.960497, 51.72653 - 44.676894);

    public static Rectangle2D getRegion(String name) {
        switch (name) {
            case "saint_petersburg":
                return SAINT_PETERSBURG;
            case "sochi":
                return SOCHI;
            case "ukraine":
                return UKRAINE;
        }
        return WORLD;
    }

}
