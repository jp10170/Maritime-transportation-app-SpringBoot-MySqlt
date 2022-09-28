package Repository;

import com.example.Marina.Models.Role;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface RoleRepo extends CrudRepository <Role, Integer> {
    ArrayList<Role> findAll();
    
    Role findById(int id);
}
