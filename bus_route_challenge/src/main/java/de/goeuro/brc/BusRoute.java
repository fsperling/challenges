/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class BusRoute {
    private TreeMap<Integer, HashSet> routes;
    private int routeCount;

    BusRoute(String s) {
        Scanner scanner = null;    
            
        try {
            File file = new File(s);
            if (file.exists()) {
                scanner = new Scanner(file);
            }
        } catch (IOException ioe) {
            System.err.println("Could not open file " + s);
        }
        
        if (scanner != null) {
            parseFile(scanner);
        }        
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
            for(int j = 1; j <= parts.length-1; j++) {
                hashSet.add(parts[j]);
            }
            
            routes.put(Integer.parseInt(parts[0]), hashSet);
        }
    }
    
    public Map<Integer, HashSet> getRoutes() {
        return routes;
    }
    
    public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(routeCount).append("\n");
        Set<Integer> keySet = routes.keySet();
        for (Integer i : keySet) {
          HashSet h = routes.get(i);
          s.append(i).append(" ").append(h.toString()).append("\n");
        }
        
        return s.toString();
    }
    
}
