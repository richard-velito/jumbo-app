package com.jumbo.app.util;

import com.jumbo.app.model.Store;
import org.apache.lucene.util.SloppyMath;

import java.util.Map;
import java.util.TreeMap;

public class JumboHelper {

    /* using SloppyMath return distance between 2 points in meters */
    public static double distance(
            double latitude1, double longitude1, double latitude2, double longitude2) {
        return SloppyMath.haversinMeters(latitude1, longitude1, latitude2, longitude2);
    }

    public static TreeMap<Double, String> calculateDistancesToStore(
            Store origin, Map<String, Store> storeMap) {
        TreeMap<Double, String> sortedMap = new TreeMap<Double, String>();

        for (String key: storeMap.keySet()) {
            Store target = storeMap.get(key);
            sortedMap.put(
                    distance(origin.getLatitude(), origin.getLongitude(), target.getLatitude(), target.getLongitude()), target.getUuid());
        }

        return sortedMap;
    }
}
