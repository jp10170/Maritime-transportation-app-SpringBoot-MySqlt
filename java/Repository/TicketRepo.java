package Repository;

import com.example.Marina.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer>{
     
    Ticket findById(int id);
}
