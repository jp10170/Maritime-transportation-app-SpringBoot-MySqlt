package com.example.Marina.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Korisnik
 */
@Entity
public class Route {
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer route_id;
    
    @NotEmpty(message = "Route name may not be empty")
    @Column(unique=true)
    private String route_name;
    @NotNull(message = "Route price may not be null")
    private Integer route_price;
    
    @JsonIgnore
    @OneToMany(targetEntity = Journey.class, mappedBy = "route" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Journey> journeys = new ArrayList<Journey>();
    
    public Route(){}

    public Route(Integer route_id, String route_name, Integer route_price) {
        this.route_id = route_id;
        this.route_name = route_name;
        this.route_price = route_price;
    }

    public Integer getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Integer route_id) {
        this.route_id = route_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public Integer getRoute_price() {
        return route_price;
    }

    public void setRoute_price(Integer route_price) {
        this.route_price = route_price;
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }   
}
