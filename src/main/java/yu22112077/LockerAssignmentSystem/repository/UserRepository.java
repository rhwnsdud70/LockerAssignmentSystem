package yu22112077.LockerAssignmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yu22112077.LockerAssignmentSystem.model.entity.User;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(String id);
    @Query("SELECT u FROM User u WHERE u.administrator = false AND u.id NOT IN (SELECT l.assignedTo.id FROM Locker l WHERE l.assignedTo IS NOT NULL)")
    List<User> findAllUnassignedUsers();

}
