package Service;

import Repository.JourneyRepo;
import Repository.TicketRepo;
import com.example.Marina.Models.Journey;
import com.example.Marina.Models.Ticket;
import com.example.Marina.Models.TicketHelper;
import com.example.Marina.Models.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class TicketService {
    
    @Autowired
    TicketRepo repository;
    @Autowired
    JourneyRepo journeyRepo;
    
    public HashMap<String, Integer> fillMap(Journey journey){
        HashMap<String, Integer> map = new HashMap();
        if(journey.getAvailable_cabins() > 0){
            int price = journey.getRoute().getRoute_price() + journey.getShip().getShip_type().getCabin_price();
            map.put("Cabin", price);
        }
        if(journey.getAvailable_deck() > 0){
            int price = journey.getRoute().getRoute_price() + journey.getShip().getShip_type().getDeck_price();
            map.put("Deck", price);
        }
        if(journey.getAvailable_seats() > 0){
            int price = journey.getRoute().getRoute_price() + journey.getShip().getShip_type().getSeat_price();
            map.put("Seat", price);
        }
        return map;
        
    }
    
    public boolean bookTicket(User user, Ticket ticket, Journey journey, TicketHelper ticketHelper){
        ticket.setJourney(journey);
        ticket.setUser(user);
        ticket.setTicket_price(ticketHelper.getAccomodations_price().get(ticket.getAccomodation_type()));
        return setAccommodationNumber(journey, ticket);      
    }
    
    public boolean setAccommodationNumber(Journey journey, Ticket ticket){
        try{
            if(ticket.getAccomodation_type().equals("Cabin")){
                ticket.setAccommodation_number(journey.getAvailable_cabins()-1);
                journey.setAvailable_cabins(journey.getAvailable_cabins()-1);
                journey.setTotal_available(journey.getTotal_available()-1);
                journeyRepo.save(journey);
                repository.save(ticket);
                
                return true;
            }
            else if(ticket.getAccomodation_type().equals("Seat")){
                ticket.setAccommodation_number(journey.getAvailable_seats()-1);
                journey.setAvailable_seats(journey.getAvailable_seats()-1);
                journey.setTotal_available(journey.getTotal_available()-1);
                journeyRepo.save(journey);
                repository.save(ticket);
                
                return true;
            }
            else{
                ticket.setAccommodation_number(journey.getAvailable_deck()-1);
                journey.setAvailable_deck(journey.getAvailable_deck()-1);
                journey.setTotal_available(journey.getTotal_available()-1);
                journeyRepo.save(journey);
                repository.save(ticket);
                return true;
            }
        }catch(Exception e){
            return false;
        }
    }
    
    public List<Ticket> getMyTickets(User user){
        
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) repository.findAll();
        ArrayList<Ticket> myTickets = new ArrayList();
        for(int i=0; i<tickets.size(); i++){
            Ticket ticket = tickets.get(i);
            if(ticket.getUser().getUser_id().equals(user.getUser_id())){
                myTickets.add(ticket);
            }
        }
        return myTickets;       
    }
    
    public boolean deleteTicket(Ticket ticket){
        try{
            Journey journey = ticket.getJourney();
            journey.setTotal_available(journey.getTotal_available()+1);
            if(ticket.getAccomodation_type().equals("Cabin")){
                journey.setAvailable_cabins(journey.getAvailable_cabins()+1);
                journeyRepo.save(journey);
                repository.delete(ticket);
            }
            else if(ticket.getAccomodation_type().equals("Deck")){
                journey.setAvailable_deck(journey.getAvailable_deck()+1);
                journeyRepo.save(journey);
                repository.delete(ticket);
            }
            else{
                journey.setAvailable_seats(journey.getAvailable_seats()+1);
                journeyRepo.save(journey);
                repository.delete(ticket);
            }           
        }catch(Exception e){
            return false;
        }
        return true;       
    }
    
    public Ticket findById(int id){
        return repository.findById(id);
    }
}
