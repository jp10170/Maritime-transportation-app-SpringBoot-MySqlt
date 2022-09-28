package Service;

import Repository.RoleRepo;
import com.example.Marina.Models.Role;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Korisnik
 */
@Service
public class RoleService {
    
    @Autowired
    public RoleRepo repository;
    
    
    public RoleService(RoleRepo repository){
        this.repository = repository;
    }
    
    public ArrayList<Role> getRoles(){
        return repository.findAll();
    }
}
