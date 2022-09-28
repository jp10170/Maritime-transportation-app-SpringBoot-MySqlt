package Service;

import Repository.DestinationRepo;
import Repository.RouteRepo;
import com.example.Marina.Models.Possible_destination;
import com.example.Marina.Models.Route;
import com.example.Marina.Models.RouteHelper;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class RouteService {
    
    @Autowired
    public RouteRepo repository;
    @Autowired
    public DestinationRepo destRepo;
    
    public void save(Route route){
        repository.save(route);
    }
    
    public ArrayList<Route> findAll(){
        return repository.findAll();
    }
    
     public ArrayList<Possible_destination> findAllCitys(){
        return (ArrayList<Possible_destination>) destRepo.findAll();
    }
    
    public Route findById(int id){
        return repository.findById(id);
    }
    
    public void deleteById(int id){
        repository.deleteById(id);
    }
    
    public boolean createRoute(RouteHelper route_helper){
        String route_name = route_helper.getStart() + "-" + route_helper.getDestination();
        boolean bol;
        if(route_helper.getDestination().equalsIgnoreCase(route_helper.getStart()) == true){
            bol = false;
            return bol;
        }
        bol = checkIfNameExistsAdd(route_name);
        if(bol == false){
            Route route = new Route();
            route.setRoute_name(route_name);
            route.setRoute_price(route_helper.getRoute_price());
            repository.save(route);
            bol = true;
            return bol;
        }
        else{
            bol = false;
            return bol;
        }
    }
    
    public boolean updateRoute(RouteHelper route_helper, int id){
        String route_name = route_helper.getStart() + "-" + route_helper.getDestination();
        boolean bol;
        if(route_helper.getDestination().equalsIgnoreCase(route_helper.getStart()) == true){
            bol = false;
            return bol;
        }
        bol = checkIfNameExistsUpdate(route_name);
        try{
            if(bol == false){
                Route route = repository.findById(id);
                route.setRoute_name(route_name);
                route.setRoute_price(route_helper.getRoute_price());
                repository.save(route);
                bol = true;
                return bol;
            }
            else{
                bol = false;
                return bol;
            }
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean checkIfNameExistsAdd(String route_name){
        ArrayList<Route> routes = repository.findAll();
        int br = 1;
        boolean bol = false;
        for(int i=0; i<routes.size(); i++){
            if(routes.get(i).getRoute_name().equalsIgnoreCase(route_name)){
                br++;
                if(br >=2){
                    bol = true;
                }
            }
        }
        return bol;
    }
    
    public boolean checkIfNameExistsUpdate(String route_name){
        ArrayList<Route> routes = repository.findAll();
        int br = 0;
        boolean bol = false;
        for(int i=0; i<routes.size(); i++){
            if(routes.get(i).getRoute_name().equalsIgnoreCase(route_name)){
                br++;
                if(br >=2){
                    bol = true;
                }
            }
        }
        return bol;
    }
    
}
