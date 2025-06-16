package yu22112077.LockerAssignmentSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu22112077.LockerAssignmentSystem.model.dto.AdminUpdateUserDTO;
import yu22112077.LockerAssignmentSystem.model.entity.User;
import yu22112077.LockerAssignmentSystem.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody AdminUpdateUserDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setMajor(dto.getMajor());
        user.setRole(dto.getRole());
        user.setStudentNumber(dto.getStudentNumber());
        userRepository.save(user);

        return ResponseEntity.ok("수정 완료");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        return ResponseEntity.ok(user);
    }
}
