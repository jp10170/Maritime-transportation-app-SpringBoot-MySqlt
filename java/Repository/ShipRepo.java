package Repository;

import com.example.Marina.Models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface ShipRepo extends JpaRepository<Ship, Integer>{
    
    @Query("SELECT s FROM Ship s JOIN FETCH s.journeys WHERE s.id = (:id)") 
    public Ship findByIdAndFetchShipsEagerly(@Param("id") int id);
}
