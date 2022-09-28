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
import org.springframework.stereotype.Component;

/**
 *
 * @author Korisnik
 */

@Component
@Entity
public class User {
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer user_id;

    private String  name;
    
    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    
    
    @JsonIgnore
    @OneToMany(targetEntity = Ticket.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Ticket> tickets = new ArrayList();
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_fk")
    private Role role;
    
    public User(){};

    public User(String name, String email, String password, Role role, List<Ticket> tickets) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tickets = tickets;
    }
    
    
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ticket> getTicket() {
        return tickets;
    }

    public void setTicket(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    
    
    
    
}
