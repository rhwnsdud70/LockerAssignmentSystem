package yu22112077.LockerAssignmentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yu22112077.LockerAssignmentSystem.model.entity.Locker;
import yu22112077.LockerAssignmentSystem.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface LockerRepository extends JpaRepository<Locker, Long> {
    List<Locker> findByRoom(String room);
    Optional<Locker> findByCode(String code);
    Optional<Locker> findByAssignedTo_Id(String userId);
    Optional<Locker> findByAssignedTo(User user);
}
