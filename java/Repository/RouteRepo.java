package Repository;

import com.example.Marina.Models.Route;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {
    
    public ArrayList<Route> findAll();
    
    public Route findById(int id);
    
    @Query(value = "select * from route r where r.route_name like %:keyword%", nativeQuery = true)
    List<Route> findByKeyword(@Param("keyword") String keyword);
    
}
