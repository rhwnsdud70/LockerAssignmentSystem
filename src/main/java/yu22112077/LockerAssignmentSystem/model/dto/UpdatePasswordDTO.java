package yu22112077.LockerAssignmentSystem.model.dto;

public class UpdatePasswordDTO {
    private String id;
    private String currentPw;
    private String newPw;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCurrentPw() { return currentPw; }
    public void setCurrentPw(String currentPw) { this.currentPw = currentPw; }

    public String getNewPw() { return newPw; }
    public void setNewPw(String newPw) { this.newPw = newPw; }
}