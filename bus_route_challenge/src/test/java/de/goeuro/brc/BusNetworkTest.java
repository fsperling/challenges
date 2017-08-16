package de.goeuro.brc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class BusNetworkTest {

    @Test
    public void testReturnsFalseWithoutDirectConnection() {
        BusNetwork instance = new BusNetwork("data/example");
        boolean result = instance.hasDirectConnection(1111, 5);
        assertFalse(result);
    }

    @Test
    public void testReturnsTrueWithDirectConnection() {
        BusNetwork instance = new BusNetwork("data/example");
        boolean result = instance.hasDirectConnection(169, 11);
        assertTrue(result);
    }

    @Test
    public void testPerformanceOnBigBusNetwork() {
        BusNetwork instance = new BusNetwork("data/bigbusnetwork");
        assertTrue(instance.hasDirectConnection(2473, 189));
        assertFalse(instance.hasDirectConnection(1, 10001)); // id that cant be present
    }

    @Ignore
    @Test
    // disabled because doesn't have to be generated every time
    // tends to generate way to big busnetworks
    public void generateBigBusNetwork() throws IOException {
        int maxNumberOfRoutes = 100000;
        int maxNumberOfStations = 1000000;
        int maxNumberOfStationsPerRoute = 1000;

        Random rnd = new Random();
        int numberOfRoutes = rnd.nextInt(maxNumberOfRoutes);
        int numberOfStations = 0;

        FileWriter f = new FileWriter("data/bigbusnetwork");
        f.write(numberOfRoutes + "\n");

        for (int routeId = 0; routeId < numberOfRoutes; routeId++) {
            int numberOfStationsThisRoute = rnd.nextInt(maxNumberOfStationsPerRoute);
            ArrayList<Integer> stationIds = new ArrayList<>();

            for (int j = 0; j < numberOfStationsThisRoute; j++) {
                int id = rnd.nextInt(10000);
                if (!stationIds.contains(id)) {
                    stationIds.add(id);
                }
            }
            String s = stationIds.toString().replace("[", "").replace("]", "");
            s = s.replaceAll(",", "");
            f.write(routeId + " " + s + "\n");
            numberOfStations += stationIds.size();
        }

        if (numberOfStations > maxNumberOfStations) {
            System.out.printf("Generated %d stations. More than the max of %d.\n",
                    numberOfStations, maxNumberOfStations);
        }
        f.close();
    }
}
