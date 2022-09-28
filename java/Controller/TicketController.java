
package Controller;

import Service.JourneyService;
import Service.TicketService;
import Service.UserService;
import com.example.Marina.Models.Journey;
import com.example.Marina.Models.Ticket;
import com.example.Marina.Models.TicketHelper;
import com.example.Marina.Models.User;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
public class TicketController {
    @Autowired
    private TicketService service;
    @Autowired
    private JourneyService journeyService;
    @Autowired
    private UserService userService;

    public TicketController(TicketService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "user/bookTicket/{ID}",method = {RequestMethod.GET})
    public String showBookTicket(Authentication authentication, Model model, @PathVariable(value = "ID") Integer ID){
        
        Journey journey = journeyService.findById(ID);
        TicketHelper ticketHelper = new TicketHelper();
        ticketHelper.setAccomodations_price(service.fillMap(journey));
        model.addAttribute("journey", journey);
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("ticketHelper", ticketHelper);
        return "Ticket/bookTicket";

    }
    
    @RequestMapping(value = "user/processBookTicket/{ID}",method = {RequestMethod.GET, RequestMethod.POST})
    public RedirectView bookTicket(Authentication authentication,@ModelAttribute Ticket ticket,
        @PathVariable(value = "ID") Integer ID){
        
        Journey journey = journeyService.findById(ID);
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        TicketHelper ticketHelper = new TicketHelper();
        ticketHelper.setAccomodations_price(service.fillMap(journey));
        if(service.bookTicket(user, ticket, journey, ticketHelper) == false){
            String http_route = "http://localhost:8080/user/processBookTicket/" + ID; 
            return new RedirectView(http_route);
        }
        else
            return new RedirectView("http://localhost:8080/journeys");
    }
    
    @RequestMapping(value = "user/myTickets", method = {RequestMethod.GET})
    public String myTickets(Authentication authentication, Model model){       
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        ArrayList<Ticket> myTickets = (ArrayList<Ticket>) service.getMyTickets(user);
        model.addAttribute("myTickets", myTickets);
        return "Ticket/myTickets";
    }
    
    @RequestMapping(value = "user/showTicket/{ID}",  method = {RequestMethod.GET})
    public String showTicket(Model model, @PathVariable(value = "ID") Integer ID){
        Ticket ticket = service.findById(ID);
        model.addAttribute("ticket", ticket);
        return "Ticket/showTicket";
    }
    
    @RequestMapping(value = "user/deleteTicket/{ID}",  method = {RequestMethod.DELETE, RequestMethod.GET})
    public RedirectView deleteTicket(@PathVariable(value = "ID") Integer ID){
        Ticket ticket = service.findById(ID);
        service.deleteTicket(ticket);
        return new RedirectView("http://localhost:8080/user/myTickets");
    }
    
    
    
    
}
