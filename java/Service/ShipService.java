package Service;

import Repository.ShipRepo;
import com.example.Marina.Models.Ship;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class ShipService {
    
    @Autowired
    public ShipRepo repository;
    
    
    public ShipService(ShipRepo repository){
        this.repository = repository;
    }
    public void save(Ship ship){
        repository.save(ship);
    }
    
    public ArrayList<Ship> findAll(){
        return (ArrayList<Ship>) repository.findAll();
    }

    public boolean createShip(Ship newShip) {
        String capitalize = newShip.getShip_name().substring(0, 1).toUpperCase() + newShip.getShip_name().substring(1);
        newShip.setShip_name(capitalize);
        if(checkIfNameExists(newShip.getShip_name()) == true){
            return false;
        }
        else{
            repository.save(newShip);
            return true;
        }
            
        
    }
    
    public void deleteShip(Ship ship){
        repository.delete(ship);
    }
    
    public boolean checkIfNameExists(String ship_name){
        ArrayList<Ship> ships = (ArrayList<Ship>) repository.findAll();
        int br = 0;
        boolean bol = false;
        for(int i=0; i<ships.size(); i++){
            if(ships.get(i).getShip_name().equalsIgnoreCase(ship_name)){
                br++;
                if(br >=1){
                    bol = true;
                }
            }
        }
        return bol;
    }
    
    
}
