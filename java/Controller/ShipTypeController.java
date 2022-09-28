package Controller;

import Service.ShipTypeService;
import com.example.Marina.Models.Ship_type;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class ShipTypeController {
    @Autowired
    private ShipTypeService service;
    
    @RequestMapping(path = {"employee/manageTypes"}, method = {RequestMethod.GET})
    public String Journeys( Model model){  
        ArrayList<Ship_type> types = service.findShipTypes();
        model.addAttribute("types", types);
        return "ManageTypes/showTypes";
    }
    
    @RequestMapping(path = {"/employee/changePrices/{ID}"}, method = {RequestMethod.GET})
    public String changePricesForm( Model model, @PathVariable(value = "ID") Integer ID ){  
        Ship_type type = service.findShipType(ID);
        model.addAttribute("type", type);
        model.addAttribute("newType", new Ship_type());
        return "ManageTypes/changePrices";
    }
    
    @RequestMapping(path = {"/employee/processChangePrices/{ID}"}, method = {RequestMethod.GET, RequestMethod.PUT})
    public RedirectView changePrices(@ModelAttribute Ship_type newType, @PathVariable(value = "ID") Integer ID ){
        
        Ship_type oldType = service.findShipType(ID);       
        service.updatePrices(oldType, newType);
        return new RedirectView("http://localhost:8080/employee/manageTypes");
    }
    
}
