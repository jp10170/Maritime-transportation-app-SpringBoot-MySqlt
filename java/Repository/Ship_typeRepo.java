package Repository;

import com.example.Marina.Models.Ship_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface Ship_typeRepo extends JpaRepository<Ship_type, Integer>{
    
    public Ship_type findById(int id);
    
}
