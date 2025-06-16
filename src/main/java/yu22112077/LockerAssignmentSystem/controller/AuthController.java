package yu22112077.LockerAssignmentSystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.util.List;
import yu22112077.LockerAssignmentSystem.model.dto.UpdateMajorDTO;
import yu22112077.LockerAssignmentSystem.model.dto.UpdatePasswordDTO;
import yu22112077.LockerAssignmentSystem.model.dto.UserDTO;
import yu22112077.LockerAssignmentSystem.model.dto.UserResponseDTO;
import yu22112077.LockerAssignmentSystem.model.entity.User;
import yu22112077.LockerAssignmentSystem.repository.UserRepository;
import yu22112077.LockerAssignmentSystem.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            boolean success = authService.register(dto);
            if (!success) return ResponseEntity.badRequest().body("이미 존재하는 ID입니다.");
            return ResponseEntity.ok("Registered");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String id, @RequestParam String pw, HttpServletRequest request) {
        User user = authService.login(id, pw);
        if (user != null) {
            request.getSession().setAttribute("user", user);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user.getId(), null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    new SecurityContextImpl(auth));

            return ResponseEntity.ok(new UserResponseDTO(user));
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 완료");
    }

    @PatchMapping("/update-major")
    public ResponseEntity<?> updateMajor(@RequestBody UpdateMajorDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        user.setMajor(dto.getMajor());
        userRepository.save(user);
        return ResponseEntity.ok("수정 완료");
    }

    @PatchMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        if (!user.getPassword().equals(dto.getCurrentPw())) {
            return ResponseEntity.badRequest().body("현재 비밀번호가 일치하지 않습니다.");
        }

        user.setPassword(dto.getNewPw());
        userRepository.save(user);
        return ResponseEntity.ok("비밀번호 변경 완료");
    }

}
