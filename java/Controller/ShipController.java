package Controller;

import Service.ShipService;
import Service.ShipTypeService;
import com.example.Marina.Models.Ship;
import com.example.Marina.Models.Ship_type;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Korisnik
 */
@Controller
public class ShipController {
    @Autowired
    private ShipService service;
    @Autowired
    private ShipTypeService typeService;
    
    public ShipController(ShipService service){
        this.service = service;
    }
    
    @GetMapping("employee/manageShips")
    public String showShips(Model model){
        ArrayList<Ship> ships = service.findAll();
        ArrayList<Ship_type> types = typeService.findShipTypes();
        model.addAttribute("ships", ships);
        model.addAttribute("types", types);
        model.addAttribute("newShip", new Ship());
        return "Ships/showShips";
    }
    
    @GetMapping("employee/shipAddFailed")
    public String showShipsAddFailed(Model model){
        ArrayList<Ship> ships = service.findAll();
        ArrayList<Ship_type> types = typeService.findShipTypes();
        model.addAttribute("ships", ships);
        model.addAttribute("types", types);
        model.addAttribute("newShip", new Ship());
        return "Ships/showShipsAddFailed";
    }
    
    @RequestMapping(value = "employee/processAddShip",  method = {RequestMethod.POST, RequestMethod.GET})
    public RedirectView createShip(@ModelAttribute Ship newShip){
        if(service.createShip(newShip) == false){
            String http_route = "http://localhost:8080/employee/shipAddFailed";
            return new RedirectView(http_route);
        }
        else{
            return new RedirectView("http://localhost:8080/employee/manageShips");
        }
    }
    
    @RequestMapping(value = "employee/deleteShip/{ID}",  method = {RequestMethod.DELETE, RequestMethod.GET})
    public RedirectView deleteShip(@PathVariable(value = "ID") Integer ID){
        service.repository.deleteById(ID);
        return new RedirectView("http://localhost:8080/employee/manageShips");
    }
}
