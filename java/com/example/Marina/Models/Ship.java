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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/**
 *
 * @author Korisnik
 */
@Component
@Entity
public class Ship {
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer ship_id;
    @NotEmpty(message = "Ship name may not be empty")
    @Column(unique=true)
    private String ship_name;
    
    @JsonIgnore
    @OneToMany(targetEntity = Journey.class, mappedBy = "ship", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Journey> journeys = new ArrayList();
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ship_type_id")
    private Ship_type ship_type;
      
    
    public Ship(){}

    public Ship(Integer ship_id, String ship_name, Ship_type ship_type) {
        this.ship_id = ship_id;
        this.ship_name = ship_name;
        this.ship_type = ship_type;
    }

    public Integer getShip_id() {
        return ship_id;
    }

    public void setShip_id(Integer ship_id) {
        this.ship_id = ship_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }

    public Ship_type getShip_type() {
        return ship_type;
    }

    public void setShip_type(Ship_type ship_type) {
        this.ship_type = ship_type;
    }
    
    

    

    
    
    
    
  
    
    
    
}
