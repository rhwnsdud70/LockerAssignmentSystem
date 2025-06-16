package yu22112077.LockerAssignmentSystem.model.entity;

import jakarta.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String room;
    private boolean assigned;

    @OneToOne
    private User assignedTo;

    public Locker() {}

    public Locker(String code, String room) {
        this.code = code;
        this.room = room;
        this.assigned = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public User getAssignedTo() {
        return assignedTo;
    }
}
