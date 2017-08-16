package de.goeuro.brc;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author felix
 */
public class BusNetwork {

    private TreeMap<Integer, HashSet> routes;
    private int routeCount;

    BusNetwork(String path) {
        Scanner scanner = null;

        try {
            File file = new File(path);
            if (file.exists()) {
                scanner = new Scanner(file);
            }
        } catch (IOException ioe) {
            System.err.println("Could not open file " + path);
        }

        if (scanner != null) {
            parseFile(scanner);
        }
        
        // assuming here that if the file exists it has the correct format
    }

    private void parseFile(Scanner scanner) {
        String line;
        routeCount = scanner.nextInt();
        scanner.nextLine(); // skip rest of first line with count of routes

        routes = new TreeMap<>();

        for (int i = 0; i < routeCount; i++) {
            line = scanner.nextLine();
            String[] parts = line.split(" ");

            HashSet hashSet = new HashSet();
            for (int j = 1; j <= parts.length - 1; j++) {
                hashSet.add(Integer.parseInt(parts[j]));
            }

            routes.put(Integer.parseInt(parts[0]), hashSet);
        }
    }

    public Map<Integer, HashSet> getRoutes() {
        return routes;
    }

    public boolean hasDirectConnection(int busstop1, int busstop2) {
        boolean exists = false;

        Set<Integer> keys = routes.keySet();
        for (Integer routeId : keys) {
            HashSet stopIds = routes.get(routeId);
            if (stopIds.contains(busstop1) && stopIds.contains(busstop2)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(routeCount).append("\n");
        Set<Integer> keys = routes.keySet();
        for (Integer routeId : keys) {
            HashSet stopIds = routes.get(routeId);
            s.append(routeId).append(" ").append(stopIds.toString()).append("\n");
        }
        return s.toString();
    }

}
