package Repository;

import com.example.Marina.Models.Journey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface JourneyRepo extends JpaRepository<Journey, Integer>{ 
    
    List<Journey> findAll();
    
    Journey findById(int journey_id);

    public void deleteById(int journey_id);
    
}
