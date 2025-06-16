package yu22112077.LockerAssignmentSystem.model.dto;

import yu22112077.LockerAssignmentSystem.model.entity.Locker;
import yu22112077.LockerAssignmentSystem.model.entity.User;

public class UserResponseDTO {
    private String id;
    private String name;
    private String major;
    private String studentNumber;
    private String role;
    private boolean administrator;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.major = user.getMajor();
        this.studentNumber = user.getStudentNumber();
        this.role = user.getRole();
        this.administrator = user.isAdministrator();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getMajor() { return major; }
    public String getStudentNumber() { return studentNumber; }
    public String getRole() { return role; }
    public boolean isAdministrator() { return administrator; }
}