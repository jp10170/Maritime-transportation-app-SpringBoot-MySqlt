package com.example.Marina.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 *
 * @author Korisnik
 */
@Component
@Entity
public class Journey {
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer journey_id;
    
    @JsonIgnore
    @OneToMany(targetEntity = Ticket.class, mappedBy = "journey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList();
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    private Route route;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ship_id")
    private Ship ship;
    
    @NotNull(message = "Depart time may not be empty")
    private Timestamp depart_time;
    @NotNull(message = "Arrival time may not be empty") 
    private Timestamp arrival_time;  
    
    private Integer available_cabins;
    private Integer available_seats;
    private Integer available_deck;
    private Integer total_available;

    public Journey() {
    }

    public Journey(Integer journey_id, Route route, Ship ship, Timestamp depart_time, Timestamp arrival_time, Integer available_cabins, Integer available_seats, Integer available_deck, Integer total_available) {
        this.journey_id = journey_id;
        this.route = route;
        this.ship = ship;
        this.depart_time = depart_time;
        this.arrival_time = arrival_time;
        this.available_cabins = available_cabins;
        this.available_seats = available_seats;
        this.available_deck = available_deck;
        this.total_available = total_available;
    }

    public Integer getJourney_id() {
        return journey_id;
    }

    public void setJourney_id(Integer journey_id) {
        this.journey_id = journey_id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Timestamp getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(Timestamp depart_time) {
        this.depart_time = depart_time;
    }

    public Timestamp getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Timestamp arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Integer getAvailable_cabins() {
        return available_cabins;
    }

    public void setAvailable_cabins(Integer available_cabins) {
        this.available_cabins = available_cabins;
    }

    public Integer getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Integer available_seats) {
        this.available_seats = available_seats;
    }

    public Integer getAvailable_deck() {
        return available_deck;
    }

    public void setAvailable_deck(Integer available_deck) {
        this.available_deck = available_deck;
    }    

    public Integer getTotal_available() {
        return total_available;
    }

    public void setTotal_available(Integer total_available) {
        this.total_available = total_available;
    }
    
    
}
