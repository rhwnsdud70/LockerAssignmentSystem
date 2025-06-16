package yu22112077.LockerAssignmentSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yu22112077.LockerAssignmentSystem.model.dto.UserDTO;
import yu22112077.LockerAssignmentSystem.model.entity.User;
import yu22112077.LockerAssignmentSystem.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public boolean register(UserDTO dto) {
        if (userRepository.existsById(dto.getId())) return false;

        String role = "미납부자";
        boolean isAdmin = false;

        if (dto.getAdminCode() != null && !dto.getAdminCode().isBlank()) {
            if (dto.getAdminCode().equals("LAS")) {
                isAdmin = true;
            } else {
                throw new IllegalArgumentException("관리자 코드가 올바르지 않습니다.");
            }
        }

        User user = new User(dto.getId(), dto.getPassword(), dto.getName(), dto.getMajor(), dto.getStudentNumber(), role, isAdmin);
        userRepository.save(user);
        return true;
    }

    public User login(String id, String password) {
        return userRepository.findById(id).filter(u -> u.getPassword().equals(password)).orElse(null);
    }
}
