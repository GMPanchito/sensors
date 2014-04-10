package org.lanseg.thatpage.utils;

import java.util.Comparator;
import java.util.List;
/**
 *
 * @author lans
 */
public final class Utils {

    private Utils() {
    }

    public static boolean containsAll(List<String> keywords, String text) {
        for (String s : keywords) {
            if (!text.contains(s)) {
                return false;
            }
        }
        return true;
    }
}
