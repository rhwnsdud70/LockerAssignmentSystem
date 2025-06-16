package yu22112077.LockerAssignmentSystem.model.dto;

import yu22112077.LockerAssignmentSystem.model.entity.Locker;
import yu22112077.LockerAssignmentSystem.model.entity.User;

public class LockerDTO {

    private Long id;
    private String code;
    private String room;
    private boolean assigned;
    private String assignedToName;

    public LockerDTO(Locker locker) {
        this.id = locker.getId();
        this.code = locker.getCode();
        this.room = locker.getRoom();
        this.assigned = locker.isAssigned();

        User user = locker.getAssignedTo();
        this.assignedToName = (user != null) ? user.getName() : null;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getRoom() { return room; }
    public boolean isAssigned() { return assigned; }
    public String getAssignedToName() { return assignedToName; }
}
