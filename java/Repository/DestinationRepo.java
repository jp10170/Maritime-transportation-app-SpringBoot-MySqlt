package Repository;

import com.example.Marina.Models.Possible_destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface DestinationRepo extends JpaRepository<Possible_destination, Integer>{
  
}
