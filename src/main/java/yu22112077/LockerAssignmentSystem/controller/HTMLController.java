package yu22112077.LockerAssignmentSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import yu22112077.LockerAssignmentSystem.model.entity.Locker;
import yu22112077.LockerAssignmentSystem.model.entity.User;
import yu22112077.LockerAssignmentSystem.repository.LockerRepository;
import yu22112077.LockerAssignmentSystem.repository.UserRepository;

import java.security.Principal;

@Controller
public class HTMLController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LockerRepository lockerRepository;

    @GetMapping("/main")
    public String mainPage(Model model, Principal principal) {
        String userId = principal.getName();
        User user = userRepository.findById(userId).orElse(null);
        Locker locker = lockerRepository.findByAssignedTo(user).orElse(null);

        model.addAttribute("user", user);
        model.addAttribute("locker", locker);

        return "main";
    }

    @GetMapping("/locker")
    public String showLockerAdminPage() {
        return "locker";
    }

    @GetMapping("/locker-view")
    public String showLockerView() {
        return "locker-view";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        String userId = principal.getName();
        User user = userRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping({"/", "/index"})
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/edit-user")
    public String showEditUserPage() {
        return "edit-user";
    }

    @GetMapping("/allUser")
    public String showAllUserPage() {
        return "allUser";
    }

    @GetMapping("/upload-users")
    public String showUploadExcelPage() {
        return "upload-excel";
    }
}
