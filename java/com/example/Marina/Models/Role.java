package com.example.Marina.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Component;

/**
 *
 * @author Korisnik
 */
@Component
@Entity
public class Role {
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer role_id;
    
    private String role_name;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList();
    
    public Role(){}

    public Role(String role_name,List<User> users) {
        this.role_name = role_name;
        this.users = users;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
