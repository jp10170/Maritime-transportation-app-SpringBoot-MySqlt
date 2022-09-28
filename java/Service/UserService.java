package Service;

import Auth.MyUserDetailsService;
import Repository.JourneyRepo;
import Repository.UserRepo;
import com.example.Marina.Models.Journey;
import com.example.Marina.Models.Ticket;
import com.example.Marina.Models.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class UserService {
    @Autowired
    public UserRepo repository;
    @Autowired
    public JourneyRepo journeyRepo;
    public MyUserDetailsService myUserDetailsService;
    
    public UserService(UserRepo repository){
        this.repository = repository;
    }
    
    
    public List<User> findAll(int role_id){
        if(role_id == 1){
            ArrayList<User> users = (ArrayList<User>) repository.findAll();
            for(int i=0; i<users.size(); i++){
                if(users.get(i).getRole().getRole_id()==1){
                    users.remove(users.get(i));
                }
            }
            return users;
        }
        ArrayList<User> ausers = new ArrayList();
        if(role_id == 2){
            ArrayList<User> users = (ArrayList<User>) repository.findAll();

            for(int i=0; i<users.size(); i++){
                if((users.get(i).getRole().getRole_id()!=2) && (users.get(i).getRole().getRole_id()!=1)){
                    ausers.add(users.get(i));
                }
            }
            return ausers;
        }
        ArrayList<User> users = (ArrayList<User>) repository.findAll();
        return users;       
    }
    
    public User findbyUsername(String username){
        return repository.findByEmail(username);
    }
    
    public User findbyId(int id){
        return repository.findById(id);
    }
    
    public void deleteUser(int id){
        User user = repository.findByIdAndFetchUsersEagerly(id);
        if(user != null){
            List<Ticket> tickets = user.getTickets();
            for(int i=0; i<tickets.size(); i++){
                Journey journey = tickets.get(i).getJourney();
                if(tickets.get(i).getAccomodation_type().equals("Cabin")){
                    journey.setAvailable_cabins(journey.getAvailable_cabins()+1);
                }
                else if(tickets.get(i).getAccomodation_type().equals("Deck")){
                    journey.setAvailable_deck(journey.getAvailable_deck()+1);
                }
                else{
                    journey.setAvailable_seats(journey.getAvailable_seats()+1);
                }
                journey.setTotal_available(journey.getTotal_available()+1);
                journeyRepo.save(journey);
            }
            
        }
        repository.deleteById(id);
    }
    
    public User getUser(int id){
        return repository.findById(id);
    }
    
    public void saveUser(User user){
        repository.save(user);
    }
    
    public User getUserByEmail(String email){
       return repository.findByEmail(email);
    }
    
    public List<User> getByKeyword(String keyword, int role_id){
        if(role_id == 1){
            ArrayList<User> users = (ArrayList<User>) repository.findByKeyword(keyword);
            for(int i=0; i<users.size(); i++){
                if(users.get(i).getRole().getRole_id()==1){
                    users.remove(users.get(i));
                }
            }
            return users;
        }
        ArrayList<User> ausers = new ArrayList();
        if(role_id == 2){
            ArrayList<User> users = (ArrayList<User>) repository.findByKeyword(keyword);

            for(int i=0; i<users.size(); i++){
                if((users.get(i).getRole().getRole_id()!=2) && (users.get(i).getRole().getRole_id()!=1)){
                    ausers.add(users.get(i));
                }
            }
            return ausers;
        }
        if(role_id == 3){
            ArrayList<User> users = (ArrayList<User>) repository.findByKeyword(keyword);
            return users;
        }
        return repository.findByKeyword(keyword);
    }
    
    public boolean checkEmail(String email){
        ArrayList<User> users = repository.findAll();
        int br = 0;
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getEmail().compareTo(email) == 0 ){
                br++;
                if(br>=2){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean updateProfile(User user, User newProfile){
        if(checkEmail(newProfile.getEmail()) == true){
            return false;
        }
        try{
            user.setEmail(newProfile.getEmail());
            user.setName(newProfile.getName());
            repository.save(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean resetPassword(User user, String password){
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        if(passwordEncoder.matches(password, user.getPassword())){
            return false;
        }
        user.setPassword(encodedPassword);
        repository.save(user);
        return true;
    }   
}
