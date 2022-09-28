
package Service;

import Repository.JourneyRepo;
import Repository.RouteRepo;
import Repository.ShipRepo;
import com.example.Marina.Models.Journey;
import com.example.Marina.Models.JourneyHelper;
import com.example.Marina.Models.Route;
import com.example.Marina.Models.Ship;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class JourneyService {
    
    @Autowired
    JourneyRepo repository;
    @Autowired
    RouteRepo routeRepo;
    @Autowired
    ShipRepo shipRepo;

    public JourneyService(JourneyRepo repository) {
        this.repository = repository;
    }
    
    public ArrayList<Journey> findAll(){
        
        return (ArrayList<Journey>) repository.findAll();
    }
    
    public void deleteById(int id){
        repository.deleteById(id);
    }
    
    public Journey findById(int id){
        return repository.findById(id);
    }

    public boolean createJourney(Journey newJourney, JourneyHelper journeyHelper) {
        
        String departDatetime = journeyHelper.getDepart_d() + " " + journeyHelper.getDepart_t();
        String arrivalDatetime = journeyHelper.getArrival_d() + " " + journeyHelper.getArrival_t();
        newJourney.setDepart_time(Timestamp.valueOf(departDatetime));
        newJourney.setArrival_time(Timestamp.valueOf(arrivalDatetime));
        
        
        if(checkIfShipScheduled(newJourney) == true){
            return false;
        }
        
        else{
            Ship ship = newJourney.getShip();
            newJourney.setAvailable_cabins(ship.getShip_type().getTotal_cabins());
            newJourney.setAvailable_deck(ship.getShip_type().getTotal_deck());
            newJourney.setAvailable_seats(ship.getShip_type().getTotal_seats());
            int total_available = ship.getShip_type().getTotal_cabins() + ship.getShip_type().getTotal_deck() + ship.getShip_type().getTotal_seats();
            newJourney.setTotal_available(total_available);
            repository.save(newJourney);
            return true;
        }     
    }
    
    public boolean checkIfShipScheduled(Journey newJourney){
        boolean bol = false;
        
        LocalDateTime now = LocalDateTime.now();
        Timestamp currentTime = Timestamp.valueOf(now);
        if(currentTime.compareTo(newJourney.getDepart_time())>=0){
            return true;
        }
        
        ArrayList<Journey> journeys = (ArrayList<Journey>) repository.findAll();
        if(journeys == null){
            return bol;
        }
        var ship = shipRepo.findByIdAndFetchShipsEagerly(newJourney.getShip().getShip_id());
        if(ship != null){
            
            for(int i = 0; i<ship.getJourneys().size(); i++){ 
                Journey journey = ship.getJourneys().get(i);
                if(((journey.getDepart_time().compareTo(newJourney.getDepart_time()) >= 0 && journey.getDepart_time().compareTo(newJourney.getArrival_time()) >=0) ||
                    (journey.getArrival_time().compareTo(newJourney.getDepart_time()) <= 0 && journey.getArrival_time().compareTo(newJourney.getArrival_time()) <=0)))
                {
                    bol = false;
                }
                else{
                    return true;
                }
            }   
        }
        
        if(newJourney.getDepart_time().compareTo(newJourney.getArrival_time()) >0){
            return true;
        }
        return bol;
    }
    
    public boolean checkIfShipScheduledUpdate(Journey newJourney){
        int br = 0;
        boolean bol = false;
        
        LocalDateTime now = LocalDateTime.now();
        Timestamp currentTime = Timestamp.valueOf(now);
        if(currentTime.compareTo(newJourney.getDepart_time())>=0){
            return true;
        }
        
        ArrayList journeys = (ArrayList) repository.findAll();
        if(journeys == null){
            return bol;
        }
        var ship = shipRepo.findByIdAndFetchShipsEagerly(newJourney.getShip().getShip_id());
        if(ship != null){
            for(int i = 0; i<ship.getJourneys().size(); i++){
                Journey journey = ship.getJourneys().get(i);
                if(((journey.getDepart_time().compareTo(newJourney.getDepart_time()) >= 0 && journey.getDepart_time().compareTo(newJourney.getArrival_time()) >=0) ||
                    (journey.getArrival_time().compareTo(newJourney.getDepart_time()) <= 0 && journey.getArrival_time().compareTo(newJourney.getArrival_time()) <=0)))
                {
                    bol = false;
                }
                else{
                    br++;
                    if(br >= 2){
                         return true;
                    }
                }
            }   
        }
        if(newJourney.getDepart_time().compareTo(newJourney.getArrival_time()) >0){
            return true;
        }
        return bol;
    }
    

    public boolean updateJourney(Journey newJourney, JourneyHelper journeyHelper, Integer ID) {
        Journey updatedJourney = findById(ID);
        String departDatetime = journeyHelper.getDepart_d() + " " + journeyHelper.getDepart_t();
        String arrivalDatetime = journeyHelper.getArrival_d() + " " + journeyHelper.getArrival_t();
        updatedJourney.setDepart_time(Timestamp.valueOf(departDatetime));
        updatedJourney.setArrival_time(Timestamp.valueOf(arrivalDatetime));     
        if(checkIfShipScheduledUpdate(updatedJourney) == true){
            return false;
        }
        
        else{
            repository.save(updatedJourney);
            return true;
        }     
    }

    public List<Journey> findByKeyword(String keyword) {
        List<Route> routes = routeRepo.findByKeyword(keyword);
        List<Journey> journeys = repository.findAll();
        List<Journey> searchJourney = new ArrayList();
        if(routes == null){
            return searchJourney;
        }
        for(int i=0; i < journeys.size(); i++){
            for(int j=0; j < routes.size(); j++){
                if(routes.get(j).getRoute_id().equals(journeys.get(i).getRoute().getRoute_id())){
                    searchJourney.add(journeys.get(i));
                }
            }
        }
        return searchJourney;
   }
    
}
