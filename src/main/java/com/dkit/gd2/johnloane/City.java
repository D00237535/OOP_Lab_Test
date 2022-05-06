package com.dkit.gd2.johnloane;

import java.util.Collection;

public class City
{
    private String name;
    //Add appropriate collection to store connections here
    private Collection<City> connections;


    //Complete
    public City(String name)
    {
        this.name = name;
        //Set up collection added above


    }

    //Complete
    public void connect(City city)
    {

    }

    //Uncomment and complete
//    public Collection<City> getConnections()
//    {
//
//    }

    //Uncommment and complete
//    public boolean isConnected(City city)
//    {
//
//    }
}
