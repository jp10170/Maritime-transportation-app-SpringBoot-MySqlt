package Service;

import Repository.RoleRepo;
import Repository.UserRepo;
import com.example.Marina.Models.Role;
import com.example.Marina.Models.User;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class AppService {
    
    @Autowired
    public RoleRepo repository;
    
    @Autowired
    public UserRepo userRepo;
    
    public AppService(RoleRepo repository){
        this.repository = repository;
    }
    
    public boolean processRegistration(User user){
        ArrayList<User> users = userRepo.findAll();
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getEmail().compareTo(user.getEmail()) == 0 ){
                return false;
            }
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role role = new Role();
        role.setRole_id(4);
        user.setRole(role);
        userRepo.save(user);
        return true;
    }
    
    
    public ArrayList<User> getUsers(){
       return userRepo.findAll();
    }
}
