package com.example.Marina.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Korisnik
 */
@Entity
public class Possible_destination {
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer city_id;
    
    @NotEmpty
    private String city_name;

    public Possible_destination() {
    }
    
    public Possible_destination(Integer city_id, String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
    
    
}
