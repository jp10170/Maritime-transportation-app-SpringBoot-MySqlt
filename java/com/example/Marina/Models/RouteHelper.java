package com.example.Marina.Models;

/**
 *
 * @author Korisnik
 */
public class RouteHelper {
    
    private String Destination;
    private String Start;
    private Integer Route_price;

    public RouteHelper() {
    }

    public RouteHelper(String Destination,String start, Integer Route_price) {
        this.Destination = Destination;
        this.Route_price = Route_price;
        this.Start = Start;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String Start) {
        this.Start = Start;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public Integer getRoute_price() {
        return Route_price;
    }

    public void setRoute_price(Integer Route_price) {
        this.Route_price = Route_price;
    }
    
   
    
    
    
}
