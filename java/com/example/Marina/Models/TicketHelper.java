package com.example.Marina.Models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Korisnik
 */
public class TicketHelper {
    
    private Integer numberTickets;
    private HashMap<String, Integer> accomodations_price;

    public TicketHelper() {
    }

    public TicketHelper(Integer numberTickets, HashMap<String, Integer> accomodations_price, String accommodation_type) {
        this.numberTickets = numberTickets;
        this.accomodations_price = accomodations_price;
    }

    

    public Integer getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(Integer numberTickets) {
        this.numberTickets = numberTickets;
    }

    public HashMap<String, Integer> getAccomodations_price() {
        return accomodations_price;
    }

    public void setAccomodations_price(HashMap<String, Integer> accomodations_price) {
        this.accomodations_price = accomodations_price;
    }
}
