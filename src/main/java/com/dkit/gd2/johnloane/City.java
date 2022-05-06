package com.dkit.gd2.johnloane;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    //Add appropriate collection to store connections here
    List<String> connections = new ArrayList<String>();


    //Complete
    public City(String name) {
        this.name = name;
        //Set up collection added above
    }

    //Complete
    public void connect(City city) {
        //Add city to connections
        connections.add("Dublin");
        connections.add("Galway");
        connections.add("Cork");
        connections.add("Limerick");
        connections.add("Waterford");
    }

    //Uncomment and complete
    public List<String> getConnections() {
        //Return connections
        return connections;
    }

    //Uncommment and complete
    public boolean isConnected(City city) {
        //Return true if city is in connections
        return connections.contains(city.getName());
    }

    private String getName() {
        return name;
    }
}