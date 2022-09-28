package com.example.Marina.Models;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class JourneyHelper {
    
    private String depart_d;
    private String depart_t;
    private String arrival_d;
    private String arrival_t;

    public JourneyHelper() {
        
    }

    public JourneyHelper(String depart_d, String depart_t, String arrival_d, String arrival_t) {
        this.depart_d = depart_d;
        this.depart_t = depart_t;
        this.arrival_d = arrival_d;
        this.arrival_t = arrival_t;
    }

    public String getDepart_d() {
        return depart_d;
    }

    public void setDepart_d(String depart_d) {
        this.depart_d = depart_d;
    }

    public String getDepart_t() {
        return depart_t;
    }

    public void setDepart_t(String depart_t) {
        this.depart_t = depart_t;
    }

    public String getArrival_d() {
        return arrival_d;
    }

    public void setArrival_d(String arrival_d) {
        this.arrival_d = arrival_d;
    }

    public String getArrival_t() {
        return arrival_t;
    }

    public void setArrival_t(String arrival_t) {
        this.arrival_t = arrival_t;
    }

    
    
    

   

    
    
    
    
}
