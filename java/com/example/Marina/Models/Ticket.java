package com.example.Marina.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.stereotype.Component;

/**
 *
 * @author Korisnik
 */

@Component
@Entity
public class Ticket {
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer ticket_id;
    private Integer accommodation_number;
    private String accomodation_type;
    private Integer ticket_price;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "journey_id")
    private Journey journey;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Ticket() {
    }

    public Ticket(Integer ticket_id, Integer accommodation_number, String accomodation_type, Integer ticket_price, Journey journey, User user) {
        this.ticket_id = ticket_id;
        this.accommodation_number = accommodation_number;
        this.accomodation_type = accomodation_type;
        this.ticket_price = ticket_price;
        this.journey = journey;
        this.user = user;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getAccommodation_number() {
        return accommodation_number;
    }

    public void setAccommodation_number(Integer accommodation_number) {
        this.accommodation_number = accommodation_number;
    }

    public String getAccomodation_type() {
        return accomodation_type;
    }

    public void setAccomodation_type(String accomodation_type) {
        this.accomodation_type = accomodation_type;
    }

    public Integer getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Integer ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    } 
}
