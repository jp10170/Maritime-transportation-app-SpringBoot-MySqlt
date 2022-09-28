package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Korisnik
 */
@Controller
public class AppController {
    
    @GetMapping("/index")
    public String ViewHomePage(){
        return "index";
    }
    
    
}
