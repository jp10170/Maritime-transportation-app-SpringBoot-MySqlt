package Controller;

import Service.AppService;
import Service.RoleService;
import Service.TicketService;
import Service.UserService;
import com.example.Marina.Models.Role;
import com.example.Marina.Models.Ticket;
import com.example.Marina.Models.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Korisnik
 */
@Controller
public class UserController {
    
    @Autowired
    private AppService service;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TicketService ticketService;
    
    public UserController(AppService service){
        this.service = service;
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "Register/signup_form";
    }
    
    @GetMapping("/registerfailed")
    public String showRegistrationFormfailed(Model model) {
        model.addAttribute("user", new User());
        return "Register/registerFailed";
    }
     
    @PostMapping("/process_register")
    public RedirectView processRegister(@ModelAttribute User user, Model model){
        boolean bol = service.processRegistration(user);
        if(bol == true){
            return new RedirectView("http://localhost:8080/login");
        }
        else
            return new RedirectView("http://localhost:8080/registerfailed");   
    }
    
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }
    
    @GetMapping("user/myProfile")
    public String showMyProfile(Model model, Authentication authentication){
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "Myprofile/myprofile";
    }
    
    @GetMapping("user/updateProfile")
    public String showUpdateForm(Model model, Authentication authentication){
        
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("newProfile", new User());
        return "Myprofile/updateProfile";   
    }
    
    @GetMapping("user/updateProfileFailed")
    public String showUpdateFormFailed(Model model, Authentication authentication){
        
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("newProfile", new User());
        return "Myprofile/updateProfileFailed";   
    }
    
    @RequestMapping(value = "user/processUpdate",  method = {RequestMethod.PUT, RequestMethod.GET})
    public RedirectView proccesUpdate(@ModelAttribute User newProfile, Authentication authentication){
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        boolean bol = userService.updateProfile(user, newProfile);
        if(bol == true){
            return new RedirectView("http://localhost:8080/logout");
        }
        else
            return new RedirectView("http://localhost:8080/user/updateProfileFailed");
    }
    
    @RequestMapping(value = "user/deleteMyAcco",  method = {RequestMethod.GET, RequestMethod.DELETE})
    public RedirectView deleteAcco(Authentication authentication){
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        userService.deleteUser(user.getUser_id());
        return new RedirectView("http://localhost:8080/logout");
    }
    
    @GetMapping("user/resetPassword")
    public String showResetPasswordForm(Model model, Authentication authentication){
        model.addAttribute("newProfile", new User());
        return "Myprofile/resetPassword";   
    }
    
    @GetMapping("user/resetPasswordFailed")
    public String showResetPasswordFormFailed(Model model, Authentication authentication){
        model.addAttribute("newProfile", new User());
        return "Myprofile/resetPasswordFailed";   
    }
    
    @RequestMapping(value = "user/processResetPassword",  method = {RequestMethod.PUT, RequestMethod.GET})
    public RedirectView proccesResetPassword(@ModelAttribute User newProfile, Authentication authentication){
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        boolean bol = userService.resetPassword(user, newProfile.getPassword());
        if(bol == true){
            return new RedirectView("http://localhost:8080/user/myProfile");
        }
        else
            return new RedirectView("http://localhost:8080/user/resetPasswordFailed");
    }
    
    @RequestMapping(path = {"employee/manageUsers", "employee/manageUsers/search"}, method = {RequestMethod.GET})
    public String manageUsers(Authentication authentication, Model model, User user, String keyword){
        String email = authentication.getName();
        User currentUser = userService.getUserByEmail(email);
        if(keyword!=null){
            List<User> users = userService.getByKeyword(keyword, currentUser.getRole().getRole_id());
            model.addAttribute("users", users);
        }
        else {
            List<User> users = userService.findAll(currentUser.getRole().getRole_id());
            model.addAttribute("users", users);
        }
        return "manageUsers/showUsers";
    }
    
    @RequestMapping(value = "admin/deleteUser/{ID}",  method = {RequestMethod.GET, RequestMethod.DELETE})
    public RedirectView deleteUser(Authentication authentication, @PathVariable(value = "ID") Integer ID){
        userService.deleteUser(ID);
        return new RedirectView("http://localhost:8080/employee/manageUsers");
    }
    
    @RequestMapping(value = "admin/changeRole/{ID}", method = {RequestMethod.GET})
    public String changeRole(Authentication auth, Model model, @PathVariable(value = "ID") Integer ID ){
        User user = userService.getUser(ID);
        User cUser= userService.getUserByEmail(auth.getName());
        ArrayList<Role> roles = roleService.getRoles();
        if(cUser.getRole().getRole_name().equals("Admin")){
            roles.remove(1);
        }
        roles.remove(0);
        model.addAttribute("roles", roles);
        model.addAttribute("oldUser", user);
        model.addAttribute("newUser", new User());
        return "manageUsers/updateUserRole";
    }
    
    @RequestMapping(value = "admin/processUpdateRole/{ID}",  method = {RequestMethod.GET, RequestMethod.PUT})
    public RedirectView processUpdateRole(@ModelAttribute User newUser, @PathVariable(value = "ID") Integer ID){
        User user = userService.getUser(ID);
        user.setRole(newUser.getRole());
        userService.saveUser(user);
        return new RedirectView("http://localhost:8080/employee/manageUsers");
    }
    
    @RequestMapping(value = "employee/checkTickets/{ID}",  method = {RequestMethod.GET})
    public String checkTickets(Model model, @PathVariable(value = "ID") Integer ID){
        User user = userService.findbyId(ID);
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) ticketService.getMyTickets(user);
        model.addAttribute("tickets", tickets);
        model.addAttribute(user);
        return "manageUsers/userTickets";
    }
}
