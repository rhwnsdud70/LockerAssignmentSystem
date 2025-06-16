package yu22112077.LockerAssignmentSystem.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String password;
    private String name;
    private String major;
    private String studentNumber;
    private String role;
    private boolean administrator;

    public User() {}

    public User(String id, String password, String name, String major, String studentNumber, String role, boolean administrator) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.major = major;
        this.studentNumber = studentNumber;
        this.role = role;
        this.administrator = administrator;
    }

    public String getId() {
        return id;
    }

    public String getStudentNumber() { return studentNumber; }

    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
}
