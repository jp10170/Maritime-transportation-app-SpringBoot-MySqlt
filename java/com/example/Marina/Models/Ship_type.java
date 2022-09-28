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

/**
 *
 * @author Korisnik
 */
@Entity
public class Ship_type {
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer ship_type_id;
    
    @NotEmpty(message = "Ship type may not be empty")
    @Column(unique=true)
    private String type_name;
    
    private Integer total_seats;
    private Integer total_cabins;
    private Integer total_deck;
    private Integer seat_price;
    private Integer cabin_price;
    private Integer deck_price;
    
    @JsonIgnore
    @OneToMany(targetEntity = Ship.class, mappedBy = "ship_type", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ship> ships = new ArrayList();

    public Ship_type() {
    }

    public Ship_type(Integer ship_type_id, String type_name, Integer total_seats, Integer total_cabins, Integer total_deck, Integer seat_price, Integer cabin_price, Integer deck_price) {
        this.ship_type_id = ship_type_id;
        this.type_name = type_name;
        this.total_seats = total_seats;
        this.total_cabins = total_cabins;
        this.total_deck = total_deck;
        this.seat_price = seat_price;
        this.cabin_price = cabin_price;
        this.deck_price = deck_price;
    }


    public Integer getShip_type_id() {
        return ship_type_id;
    }

    public void setShip_type_id(Integer ship_type_id) {
        this.ship_type_id = ship_type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    

    public Integer getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(Integer total_seats) {
        this.total_seats = total_seats;
    }

    public Integer getTotal_cabins() {
        return total_cabins;
    }

    public void setTotal_cabins(Integer total_cabins) {
        this.total_cabins = total_cabins;
    }

    public Integer getTotal_deck() {
        return total_deck;
    }

    public void setTotal_deck(Integer total_deck) {
        this.total_deck = total_deck;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public Integer getSeat_price() {
        return seat_price;
    }

    public void setSeat_price(Integer seat_price) {
        this.seat_price = seat_price;
    }

    public Integer getCabin_price() {
        return cabin_price;
    }

    public void setCabin_price(Integer cabin_price) {
        this.cabin_price = cabin_price;
    }

    public Integer getDeck_price() {
        return deck_price;
    }

    public void setDeck_price(Integer deck_price) {
        this.deck_price = deck_price;
    }
    
    
  
}
