package Controller;

import Service.JourneyService;
import Service.RouteService;
import Service.ShipService;
import com.example.Marina.Models.Journey;
import com.example.Marina.Models.JourneyHelper;
import com.example.Marina.Models.Route;
import com.example.Marina.Models.Ship;
import java.util.ArrayList;
import java.util.List;
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
public class JourneyController {
    
    @Autowired
    private JourneyService service;
    @Autowired
    private ShipService shipService;
    @Autowired
    private RouteService routeService;

    public JourneyController(JourneyService service) {
        this.service = service;
    }
    
    
    @RequestMapping(path = {"journeys", "journeys/search"}, method = {RequestMethod.GET})
    public String Journeys( Model model, String keyword){
        
        if(keyword!=null){
            List<Journey> journeys = service.findByKeyword(keyword);
            model.addAttribute("journeys", journeys);
        }
        else {
            List<Journey> journeys = service.findAll();
            model.addAttribute("journeys", journeys);
        }
        return "Journeys/journeys";
    }
    
    
    
    @RequestMapping(value = "employee/manageJourneys",method = {RequestMethod.GET})
    public String manageJourneys(Model model){
        
        ArrayList<Journey> journeys = service.findAll();
        ArrayList<Route> routes = routeService.findAll();
        ArrayList<Ship> ships = shipService.findAll();
        
        model.addAttribute("journeys", journeys);
        model.addAttribute("routes", routes);
        model.addAttribute("ships", ships);
        model.addAttribute("newJourney", new Journey());
        model.addAttribute("newJourneyHelper", new JourneyHelper());
        return "Journeys/manageJourneys";

    }
    
    @RequestMapping(value = "employee/manageJourneysAddFailed",method = {RequestMethod.GET})
    public String manageJourneysAddFailed(Model model){
        
        ArrayList<Journey> journeys = service.findAll();
        ArrayList<Route> routes = routeService.findAll();
        ArrayList<Ship> ships = shipService.findAll();
        
        model.addAttribute("journeys", journeys);
        model.addAttribute("routes", routes);
        model.addAttribute("ships", ships);
        model.addAttribute("newJourney", new Journey());
        model.addAttribute("newJourneyHelper", new JourneyHelper());
        return "Journeys/manageJourneysAddFailed";

    }
    
    @RequestMapping(value = "employee/processAddJourney",  method = {RequestMethod.POST, RequestMethod.GET})
    public RedirectView createRoute(@ModelAttribute Journey newJourney, @ModelAttribute JourneyHelper newJourneyHelper){
        
        if(service.createJourney(newJourney, newJourneyHelper) == false){
            String http_route = "http://localhost:8080/employee/manageJourneysAddFailed"; 
            return new RedirectView(http_route);
        }
        else{
            return new RedirectView("http://localhost:8080/employee/manageJourneys");
        }
    }
    
    
    
    @RequestMapping(value = "employee/updateJourney/{ID}",method = {RequestMethod.GET})
    public String updateJourney(Model model, @PathVariable(value = "ID") Integer ID){
        
        Journey oldJourney = service.findById(ID);
        ArrayList<Route> routes = routeService.findAll();
        ArrayList<Ship> ships = shipService.findAll();
        
        model.addAttribute("oldJourney", oldJourney);
        model.addAttribute("routes", routes);
        model.addAttribute("ships", ships);
        model.addAttribute("newJourney", new Journey());
        model.addAttribute("newJourneyHelper", new JourneyHelper());
        return "Journeys/updateJourney";
    }
    
    @RequestMapping(value = "employee/updateJourneyFailed/{ID}",method = {RequestMethod.GET})
    public String updateJourneyFailed(Model model, @PathVariable(value = "ID") Integer ID){
        
        Journey oldJourney = service.findById(ID);
        ArrayList<Route> routes = routeService.findAll();
        ArrayList<Ship> ships = shipService.findAll();
        
        model.addAttribute("oldJourney", oldJourney);
        model.addAttribute("routes", routes);
        model.addAttribute("ships", ships);
        model.addAttribute("newJourney", new Journey());
        model.addAttribute("newJourneyHelper", new JourneyHelper());
        return "Journeys/updateJourneyFailed";
    }
    
    @RequestMapping(value = "employee/processUpdateJourney/{ID}",  method = {RequestMethod.PUT, RequestMethod.GET})
    public RedirectView processUpdateJourney(@ModelAttribute JourneyHelper journeyHelper, @ModelAttribute Journey newJourney, @PathVariable(value = "ID") Integer ID){
        if(service.updateJourney(newJourney, journeyHelper, ID) == false){ 
            String http_route = "/employee/updateJourneyFailed/" + ID;
            return new RedirectView(http_route);
        }
        else{
            return new RedirectView("http://localhost:8080/employee/manageJourneys");
        }
    }
    
    
    
    
    @RequestMapping(value = "employee/deleteJourney/{ID}",  method = {RequestMethod.DELETE, RequestMethod.GET})
    public RedirectView deleteJourney(@PathVariable(value = "ID") Integer ID){
        service.deleteById(ID);
        return new RedirectView("http://localhost:8080/employee/manageJourneys");
    }
    
    
    
}
