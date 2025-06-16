package yu22112077.LockerAssignmentSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yu22112077.LockerAssignmentSystem.model.dto.AdminUpdateUserDTO;
import yu22112077.LockerAssignmentSystem.model.dto.UserResponseDTO;
import yu22112077.LockerAssignmentSystem.model.entity.User;
import yu22112077.LockerAssignmentSystem.repository.UserRepository;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/unassigned")
    public List<UserResponseDTO> getUnassignedUsers() {
        return userRepository.findAllUnassignedUsers()
                .stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @GetMapping("/me")
    public UserResponseDTO getCurrentUser(@SessionAttribute("user") User user) {
        return new UserResponseDTO(user);
    }

    @GetMapping("")
    public List<UserResponseDTO> getAllUsers(
            @RequestParam(required = false) String major,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String studentNumber) {

        List<User> users = userRepository.findAll();

        if (major != null && !major.isBlank()) {
            users = users.stream()
                    .filter(u -> major.equals(u.getMajor()))
                    .toList();
        }

        if (role != null && !role.isBlank()) {
            users = users.stream()
                    .filter(u -> role.equals(u.getRole()))
                    .toList();
        }

        if (studentNumber != null && !studentNumber.isBlank()) {
            users = users.stream()
                    .filter(u -> studentNumber.equals(u.getStudentNumber()))
                    .toList();
        }

        List<String> majorOrder = List.of("컴퓨터공학전공", "정보통신공학전공", "소프트웨어융합전공");
        users = users.stream()
                .sorted(Comparator
                        .comparing((User u) -> majorOrder.indexOf(u.getMajor()))
                        .thenComparing(User::getName))
                .toList();

        return users.stream().map(UserResponseDTO::new).toList();
    }

}