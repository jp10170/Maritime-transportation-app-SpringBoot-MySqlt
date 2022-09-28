package Repository;

import com.example.Marina.Models.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Korisnik
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    
    public void save(SecurityProperties.User user);
    
    public ArrayList<User> findAll();
    
    public User findByEmail(String email);
    
    public User findById(int id);
    
    @Query("SELECT u FROM User u JOIN FETCH u.tickets WHERE u.id = (:id)") 
    public User findByIdAndFetchUsersEagerly(@Param("id") int id);
    
    @Query(value = "select * from user u where u.email like %:keyword%", nativeQuery = true)
    List<User> findByKeyword(@Param("keyword") String keyword);
}
