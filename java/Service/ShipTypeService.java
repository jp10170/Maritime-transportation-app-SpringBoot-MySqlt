package Service;

import Repository.Ship_typeRepo;
import com.example.Marina.Models.Ship_type;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class ShipTypeService {
    
    @Autowired
    public Ship_typeRepo repository;
    
    
    public ShipTypeService(Ship_typeRepo repository){
        this.repository = repository;
    }

    public ArrayList<Ship_type> findShipTypes() {
        return (ArrayList<Ship_type>) repository.findAll();
    }

    public Ship_type findShipType(Integer ID) {
        
        return repository.findById(ID).orElse(new Ship_type());
    }

    public void updatePrices(Ship_type oldType, Ship_type newType) {
        
        if(oldType.getCabin_price() > 0){
            oldType.setCabin_price(newType.getCabin_price());
        }
        
        if(oldType.getDeck_price() > 0){
            oldType.setDeck_price(newType.getDeck_price());
        }
        
        if(oldType.getSeat_price() > 0){
            oldType.setSeat_price(newType.getSeat_price());
        }
        repository.save(oldType);
        
    }
    
}
