package Controller;

import Service.RouteService;
import com.example.Marina.Models.Possible_destination;
import com.example.Marina.Models.Route;
import com.example.Marina.Models.RouteHelper;
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
public class RouteController {
    @Autowired
    private RouteService service;
    
    public RouteController(RouteService service){
        this.service = service;
    }
    
    @GetMapping("employee/routes")
    public String showRoutes(Model model){
        ArrayList<Route> routes = service.findAll();
        ArrayList<Possible_destination> citys = service.findAllCitys();
        model.addAttribute("routes", routes);
        model.addAttribute("citys", citys);
        model.addAttribute("newRoute", new RouteHelper());
        return "Routes/showRoutes";
    }
    
    @GetMapping("employee/routesAddFailed")
    public String showRoutesAddRouteFailed(Model model){
        ArrayList<Route> routes = service.findAll();
        ArrayList<Possible_destination> citys = service.findAllCitys();
        model.addAttribute("routes", routes);
        model.addAttribute("citys", citys);
        model.addAttribute("newRoute", new RouteHelper());
        return "Routes/showRouteAddNameFailed";
    }
    
    @GetMapping("employee/updateRoute/{ID}")
    public String showUpdateForm(Model model, @PathVariable(value = "ID") Integer ID){
        
        Route route = service.findById(ID);
        ArrayList<Possible_destination> citys = service.findAllCitys();
        model.addAttribute("oldRoute", route);
        model.addAttribute("newRoute", new RouteHelper());
        model.addAttribute("citys", citys);
        return "Routes/updateRoute";
    }
    
    @GetMapping("employee/updateFailedRoute/{ID}")
    public String showUpdateFailedForm(Model model, @PathVariable(value = "ID") Integer ID){       
        Route route = service.findById(ID);
        ArrayList<Possible_destination> citys = service.findAllCitys();
        model.addAttribute("oldRoute", route);
        model.addAttribute("newRoute", new RouteHelper());
        model.addAttribute("citys", citys);
        return "Routes/updateRouteNameFailed";
    }
    
    @RequestMapping(value = "employee/processUpdateRoute/{ID}",  method = {RequestMethod.PUT, RequestMethod.GET})
    public RedirectView updateRoute(@ModelAttribute RouteHelper newroute, @PathVariable(value = "ID") Integer ID){
        Route oldroute = service.findById(ID);
        if(service.updateRoute(newroute, ID) == false){
            String http_route = "/employee/updateFailedRoute/" + oldroute.getRoute_id();
            return new RedirectView(http_route);
        }
        else{
            return new RedirectView("http://localhost:8080/employee/routes");
        }
    }
    
    @RequestMapping(value = "employee/processAddRoute",  method = {RequestMethod.POST, RequestMethod.GET})
    public RedirectView createRoute(@ModelAttribute RouteHelper newroute){
        if(service.createRoute(newroute) == false){
            String http_route = "http://localhost:8080/employee/routesAddFailed";
            return new RedirectView(http_route);
        }
        else{
            return new RedirectView("http://localhost:8080/employee/routes");
        }
    }
    
    
    @RequestMapping(value = "employee/deleteRoute/{ID}",  method = {RequestMethod.DELETE, RequestMethod.GET})
    public RedirectView deleteRoute(@PathVariable(value = "ID") Integer ID){
        service.repository.deleteById(ID);
        return new RedirectView("http://localhost:8080/employee/routes");
    }
}
