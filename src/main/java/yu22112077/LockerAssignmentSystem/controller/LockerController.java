package yu22112077.LockerAssignmentSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu22112077.LockerAssignmentSystem.model.dto.LockerDTO;
import yu22112077.LockerAssignmentSystem.model.entity.Locker;
import yu22112077.LockerAssignmentSystem.model.entity.User;
import yu22112077.LockerAssignmentSystem.repository.LockerRepository;
import yu22112077.LockerAssignmentSystem.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    @Autowired
    private LockerRepository lockerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<LockerDTO> getLockersByRoom(@RequestParam(required = false) String room) {
        List<Locker> lockers = (room == null)
                ? lockerRepository.findAll()
                : lockerRepository.findByRoom(room);
        return lockers.stream().map(LockerDTO::new).toList();
    }

    @PostMapping("/assign-to")
    public ResponseEntity<String> assignLockerToUser(@RequestParam Long lockerId, @RequestParam String userId) {
        Locker locker = lockerRepository.findById(lockerId)
                .orElseThrow(() -> new RuntimeException("사물함 없음"));
        if (locker.isAssigned()) return ResponseEntity.badRequest().body("이미 배정됨");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        locker.setAssigned(true);
        locker.setAssignedTo(user);
        lockerRepository.save(locker);
        return ResponseEntity.ok("배정 완료");
    }

    @DeleteMapping("/unassign/{lockerId}")
    public ResponseEntity<String> unassignLocker(@PathVariable Long lockerId) {
        Locker locker = lockerRepository.findById(lockerId)
                .orElseThrow(() -> new RuntimeException("사물함 없음"));
        if (!locker.isAssigned()) return ResponseEntity.badRequest().body("이미 배정 해제됨");
        locker.setAssigned(false);
        locker.setAssignedTo(null);
        lockerRepository.save(locker);
        return ResponseEntity.ok("배정 취소 완료");
    }

    @GetMapping("/my")
    public ResponseEntity<LockerDTO> getMyLocker(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.status(401).build(); // 인증 정보 없음
        }

        String userId = principal.getName();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body(null); // 사용자 없음
        }

        Locker locker = lockerRepository.findByAssignedTo(user).orElse(null);
        if (locker == null) {
            return ResponseEntity.noContent().build(); // 배정된 사물함 없음
        }

        return ResponseEntity.ok(new LockerDTO(locker));
    }

}